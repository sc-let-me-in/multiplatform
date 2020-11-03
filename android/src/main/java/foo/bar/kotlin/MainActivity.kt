package foo.bar.kotlin

import androidx.appcompat.app.AppCompatActivity
import foo.bar.kotlin.common.Hello
import foo.bar.kotlin.mobile.HelloService
import foo.bar.kotlin.mobile.platform

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onResume() {
        super.onResume()
        HelloService.sayHi(Hello(platform()))
    }
}
