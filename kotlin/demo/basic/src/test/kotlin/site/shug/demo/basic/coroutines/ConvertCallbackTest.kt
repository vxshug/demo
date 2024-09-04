package site.shug.demo.basic.coroutines

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.Test

interface Callback {
    fun onSuccess(result: String)
    fun onFailure(t: Throwable)
}

fun operatorFunc(callback: Callback) {
    thread {
        Thread.sleep(1000)
        callback.onSuccess("success")
    }
}

// 可取消的协程函数
suspend fun operatorFuncAsCancellableSuspend(): String = suspendCancellableCoroutine { cont ->
    operatorFunc(object : Callback {
        override fun onSuccess(result: String) {
            // 成功时恢复协程
            cont.resume(result) { case, value, context ->
                // 取消时的回掉
                println("Cancel")
            }
        }
        override fun onFailure(t: Throwable) {
            cont.resumeWithException(t) // 失败时抛出异常
        }
    })
}

// 不可取消的协程函数
suspend fun operatorFuncAsSuspend(): String = suspendCoroutine { cont ->
    operatorFunc(object : Callback {
        override fun onSuccess(result: String) {
            cont.resume(result) // 成功时恢复协程
        }

        override fun onFailure(t: Throwable) {
            cont.resumeWithException(t) // 失败时抛出异常
        }
    })
}

class ConvertCallbackTest {
    @Test
    fun testSuspendCancellableCoroutine() = runBlocking {
        try {
            withTimeout(100) {
                operatorFuncAsCancellableSuspend()
            }
        } catch (e: TimeoutCancellationException) {
            // 捕获超时异常
        }

        val result = operatorFuncAsCancellableSuspend()
        println(result)
        delay(1000)
    }

    @Test
    fun testSuspendCoroutine() = runBlocking {
        try {
            withTimeout(100) {
                operatorFuncAsSuspend()
            }
        } catch (e: TimeoutCancellationException) {
            // 捕获超时异常
        }

        val result = operatorFuncAsSuspend()
        println(result)
        delay(1000)
    }
}