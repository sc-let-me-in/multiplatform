package foo.bar.kotlin.mobile

import foo.bar.kotlin.common.Hello

object HelloService {
    fun sayHi(hello: Hello) {
        println("hi: ${hello.name}")
    }

    fun tryHello() = kotlin.runCatching {
        sayHi(Hello("foo"))
    }
}
