package foo.bar.kotlin.mobile

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.native.concurrent.freeze

object Client {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun subscribe() {
        scope.launch {
            suspendCoroutine<String> { cont ->
                MockCallback.listen({ result: String ->
                    cont.resume(result)
                }.freeze())
            }
        }
    }
}

/**
 * This is mocking an iOS based callback interface where the callback occurs on a different thread
 */
private object MockCallback {
    fun listen(onResponse: (String) -> Unit) {
        dispatch_async(dispatch_get_main_queue()) {
            onResponse("hello")
        }
    }
}
