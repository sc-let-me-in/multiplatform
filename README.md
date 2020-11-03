# `suspendCoroutine` crash
Crash occurring in `mobile/src/iosMain/kotlin/foo/bar/kotlin/mobile/Client.kt` when `subscribe` is called. 
`MockCallback` is used in the same file to emulate a similar callback based scenario as used with `NSUrlSession` or `NSURLSessionWebSocketTask`

```
Uncaught Kotlin exception: kotlinx.coroutines.CoroutinesInternalError: Fatal exception in coroutines machinery for DispatchedContinuation[WorkerCoroutineDispatcherImpl@1e66948, Continuation @ $subscribe$lambda-0COROUTINE$0]. Please read KDoc to 'handleFatalException' method and report this incident to maintainers
Uncaught Kotlin exception: kotlin.native.IncorrectDereferenceException: illegal attempt to access non-shared foo.bar.kotlin.mobile.MockCallback.$listen$lambda-0$FUNCTION_REFERENCE$1@10318c8 from other thread
    at 0   KotlinAppShared                     0x000000010b0c8dec kfun:kotlin.Error#<init>(kotlin.String?;kotlin.Throwable?){} + 124 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/Exceptions.kt:14:63)
    at 1   KotlinAppShared                     0x000000010b238f3c kfun:kotlinx.coroutines.CoroutinesInternalError#<init>(kotlin.String;kotlin.Throwable){} + 124 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/common/src/Exceptions.common.kt:28:77)
    at 2   KotlinAppShared                     0x000000010b287089 kfun:kotlinx.coroutines.DispatchedTask#handleFatalException(kotlin.Throwable?;kotlin.Throwable?){} + 953 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/common/src/internal/DispatchedTask.kt:93:22)
    at 3   KotlinAppShared                     0x000000010b286c67 kfun:kotlinx.coroutines.DispatchedTask#run(){} + 3543 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/common/src/internal/DispatchedTask.kt:64:13)
    at 4   KotlinAppShared                     0x000000010b234406 kfun:kotlinx.coroutines.EventLoopImplBase#processNextEvent(){}kotlin.Long + 838 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/common/src/EventLoop.common.kt:274:18)
    at 5   KotlinAppShared                     0x000000010b2a428f kfun:kotlinx.coroutines#runEventLoop(kotlinx.coroutines.EventLoop?;kotlin.Function0<kotlin.Boolean>){} + 911 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/native/src/Builders.kt:80:40)
    at 6   KotlinAppShared                     0x000000010b2acf27 kfun:kotlinx.coroutines.WorkerCoroutineDispatcherImpl.start$lambda-0#internal + 407 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/native/src/Workers.kt:49:17)
    at 7   KotlinAppShared                     0x000000010b2ad10e kfun:kotlinx.coroutines.WorkerCoroutineDispatcherImpl.$start$lambda-0$FUNCTION_REFERENCE$35.invoke#internal + 62 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/native/src/Workers.kt:47:24)
    at 8   KotlinAppShared                     0x000000010b2ad16e kfun:kotlinx.coroutines.WorkerCoroutineDispatcherImpl.$start$lambda-0$FUNCTION_REFERENCE$35.$<bridge-UNN>invoke(){}#internal + 62 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/native/src/Workers.kt:47:24)
    at 9   KotlinAppShared                     0x000000010b0ff3b7 WorkerLaunchpad + 183 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/native/concurrent/Internal.kt:69:54)
    at 10  KotlinAppShared                     0x000000010b1fad7f _ZN6Worker19processQueueElementEb + 3135
    at 11  KotlinAppShared                     0x000000010b1fa126 _ZN12_GLOBAL__N_113workerRoutineEPv + 54
    at 12  libsystem_pthread.dylib             0x00007fff522b7109 _pthread_start + 148
    at 13  libsystem_pthread.dylib             0x00007fff522b2b8b thread_start + 15
Caused by: kotlin.native.concurrent.InvalidMutabilityException: mutation attempt of frozen foo.bar.kotlin.mobile.Client.$subscribe$lambda-0COROUTINE$0@543688
     at 0   KotlinAppShared                     0x000000010b0cfedd kfun:kotlin.Throwable#<init>(kotlin.String?){} + 93 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/Throwable.kt:23:37)   at 0   KotlinAppShared                     0x000000010b0cfedd kfun:kotlin.Throwable#<init>(kotlin.String?){} + 93 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/Throwable.kt:23:37)

    at 1   KotlinAppShared                     0x000000010b0c8efb kfun:kotlin.Exception#<init>(kotlin.String?){} + 91 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/Exceptions.kt:23:44)    at 1   KotlinAppShared                     0x000000010b0c8efb kfun:kotlin.Exception#<init>(kotlin.String?){} + 91 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/Exceptions.kt:23:44)

    at 2   KotlinAppShared                     0x000000010b0c90bb kfun:kotlin.RuntimeException#<init>(kotlin.String?){} + 91 (/Users/teamcity/build    at 2   KotlinAppShared                     0x000000010b0c90bb kfun:kotlin.RuntimeException#<init>(kotlin.String?){} + 91 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/Exceptions.kt:34:44)
Agent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/Exceptions.kt:34:44)
    at 3   KotlinAppShared                     0x000000010b0fb73b kfun:kotlin.native.IncorrectDereferenceException#<init>(kotlin.String){} + 91 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/native/Runtime.kt:30:36)
    at 3   KotlinAppShared                     0x000000010b0fdf5b kfun:kotlin.native.concurrent.InvalidMutabilityException#<init>(kotlin.String){} + 91 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/native/concurrent/Freezing.kt:22:60)
    at 4   KotlinAppShared                     0x000000010b0ffacd ThrowIllegalObjectSharingException + 621 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/native/concurrent/Internal.kt:98:11)    at 4   KotlinAppShared                     0x000000010b0ff812 ThrowInvalidMutabilityException + 690 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/native/concurrent/Internal.kt:92:11)

    at 5   KotlinAppShared                     0x000000010b1e3802 _ZN12_GLOBAL__N_128throwIllegalSharingExceptionEP9ObjHeader + 34
    at 6   KotlinAppShared                     0x000000010b2129ac _ZNK16KRefSharedHolder3refEv + 124
    at 5   KotlinAppShared                     0x000000010b1f60bc MutationCheck + 108
    at 7   KotlinAppShared                     0x000000010b0762ea _ZL39Kotlin_Interop_unwrapKotlinObjectHolderP11objc_object + 42
    at 6   KotlinAppShared                     0x000000010b0f1726 kfun:kotlin.coroutines.native.internal.ContinuationImpl.<set-intercepted>#internal + 102 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/coroutines/ContinuationImpl.kt:98:13)    at 8   KotlinAppShared                     0x000000010b073a8a _4b6f746c696e4170703a6d6f62696c65_knbridge4 + 138 (/Users/williamreed/dev/multiplatform/mobile/src/iosMain/kotlin/foo/bar/kotlin/mobile/Client.kt:32:51)

    at 9   libdispatch.dylib                   0x000000010bc15f11 _dispatch_call_block_and_release + 12
    at 7   KotlinAppShared                     0x000000010b0f1d64 kfun:kotlin.coroutines.native.internal.ContinuationImpl#releaseIntercepted(){} + 452 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/coroutines/ContinuationImpl.kt:110:9)
    at 10  libdispatch.dylib                   0x000000010bc16e8e _dispatch_client_callout + 8
    at 11  libdispatch.dylib                   0x000000010bc24d97 _dispatch_main_queue_callback_4CF + 1149
    at 8   KotlinAppShared                     0x000000010b0f0746 kfun:kotlin.coroutines.native.internal.BaseContinuationImpl#resumeWith(kotlin.Result<kotlin.Any?>){} + 1254 (/Users/teamcity/buildAgent/work/cae0e6559deed4c4/runtime/src/main/kotlin/kotlin/coroutines/ContinuationImpl.kt:36:17)    at 12  CoreFoundation                      0x00007fff23da1869 __CFRUNLOOP_IS_SERVICING_THE_MAIN_DISPATCH_QUEUE__ + 9

    at 13  CoreFoundation                      0x00007fff23d9c3b9 __CFRunLoopRun + 2041    at 9   KotlinAppShared                     0x000000010b286982 kfun:kotlinx.coroutines.DispatchedTask#run(){} + 2802 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/common/src/internal/DispatchedTask.kt:39:50)

    at 14  CoreFoundation                      0x00007fff23d9b8a4 CFRunLoopRunSpecific + 404
    at 10  KotlinAppShared                     0x000000010b234406 kfun:kotlinx.coroutines.EventLoopImplBase#processNextEvent(){}kotlin.Long + 838 (/opt/buildAgent/work/44ec6e850d5c63f0/kotlinx-coroutines-core/common/src/EventLoop.common.kt:274:18)
    at 15  GraphicsServices                    0x00007fff38c39bbe GSEventRunModal + 139
    at 16  UIKitCore                           0x00007fff49325968 UIApplicationMain + 1605
    ... and 9 more common stack frames skipped
    at 17  KotlinApp                           0x000000010adfc0db main + 75 (/Users/williamreed/dev/multiplatform/apple/KotlinApp/AppDelegate.swift:5:7)
    at 18  libdyld.dylib                       0x00007fff520ce1fd start + 1
(lldb) 
```

