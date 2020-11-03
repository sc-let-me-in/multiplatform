@file:Suppress("EXPERIMENTAL_API_USAGE")

package foo.bar.kotlin.backend

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.http.cio.websocket.pingPeriod
import io.ktor.http.cio.websocket.timeout
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.serialization.json
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.error
import io.ktor.websocket.WebSockets
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.time.Duration

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

@ExperimentalCoroutinesApi
internal fun Application.main() {
    val config = environment.config

    install(DefaultHeaders)
    if (!config.property("production").getString().toBoolean()) {
        install(CallLogging)
    }
    install(StatusPages) {
//        exception<ServiceUnavailable> { _ ->
//            call.respond(HttpStatusCode.ServiceUnavailable)
//        }
//        exception<BadRequest> { _ ->
//            call.respond(HttpStatusCode.BadRequest)
//        }
//        exception<Unauthorized> { _ ->
//            call.respond(HttpStatusCode.Unauthorized)
//        }
//        exception<NotFound> { _ ->
//            call.respond(HttpStatusCode.NotFound)
//        }
//        exception<SecretInvalidError> { _ ->
//            call.respond(HttpStatusCode.Forbidden)
//        }
        exception<Throwable> { cause ->
            environment.log.error(cause)
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(60) // Disabled (null) by default
        timeout = Duration.ofSeconds(15)
    }


    install(ContentNegotiation) {
        json()
    }

//    install(CORS) {
//        anyHost()
//        header(HttpHeaders.Authorization)
//        allowCredentials = true
//        listOf(HttpMethod.Put, HttpMethod.Delete, HttpMethod.Options).forEach { method(it) }
//    }

    install(Routing) {
        get("/") {
            call.respondText("hello")
        }
    }
}
