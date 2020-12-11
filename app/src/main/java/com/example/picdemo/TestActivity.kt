package com.example.picdemo

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.text.SimpleDateFormat
import java.util.*

class TestActivity : BaseActivity() {

    private val globalScope = GlobalScope.launch { }
    private val mainScope = MainScope()
    private val lScope = lifecycleScope

    class BaseViewModel : ViewModel() {

        fun test() {
            viewModelScope.launch {
                yield()
            }

        }
    }


    val tag = "TestActivity"
    val channel = Channel<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        launch(Dispatchers.IO){
            Log.e(tag, "开始")
            Log.e(tag, SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(Date()))
            delay(3000)
            channel.send(50)
        }

        launch(Dispatchers.Main){
            val result = channel.receive()
            Log.e(tag, "等待接收数据")
            Log.e(tag, SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(Date()))
            Log.e(tag, "获得的结果是$result")
        }

        launch (Dispatchers.IO) {
            val receive = PicApplication.testChannel.receive()
            Log.e(tag, "等待接收application中的数据")
            Log.e(tag, SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(Date()))
            Log.e(tag, "获得application中的结果是$receive")
        }


        launch {
//            val job1 = async {compute(1)}.await()
//            val job2 = async {compute(2)}.await()

//            val job1 = async {compute(1)}
//            val job2 = async {compute(2)}
//            println("协程job1 ${job1.toString()}")
//            println("协程job2 ${job2.toString()}")

//            val result2 = job1.await()
//            val result3 = job2.await()
//
//            mergerResult(result2, result3)

//            mergerResult(job2.await(), job3.await())

        }

    }


    private suspend fun compute(i: Int): Int {
        if (i == 3) throw RuntimeException("Erro")
        println("协程任务${i} 开始")
        delay(10) // 方便查看并发的时候的情况
        println("协程任务 内容值${i}")
        println("协程任务${i} 结束")

        return i
    }

    private suspend fun mergerResult(a: Int, b: Int) {
        println("协程合并 $a + $b")
    }


}


//suspend  fun <T> awaitCallback(): T = suspendCancellableCoroutine { continuation ->
//
//    val callback = object : Callback { // Implementation of some callback interface
//        override fun onCompleted(value: T) {
//            // Resume coroutine with a value provided by the callback
//            continuation.resume(value)
//        }
//        override fun onApiError(cause: Throwable) {
//            // Resume coroutine with an exception provided by the callback
//            continuation.resumeWithException(cause)
//        }
//    }
//    // Register callback with an API
//    api.register(callback)
//    // Remove callback on cancellation
//    continuation.invokeOnCancellation { api.unregister(callback) }
//    // At this point the coroutine is suspended by suspendCancellableCoroutine until callback fires
//}
