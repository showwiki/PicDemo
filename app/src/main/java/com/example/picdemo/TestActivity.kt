package com.example.picdemo

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class TestActivity : BaseActivity() {

    private val globalScope = GlobalScope.launch {  }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val format = SimpleDateFormat("yy-MM-dd HH:mm:ss")

        val str =  format.format(Date())
        launch {
            for (i in 0..10_0000) {

                if(i == 0) {
                    Log.e(tag, "开始时间" + str)
                }
                if(i == 10_0000 - 1) {
                    Log.e(tag, "结束时间" + format.format(Date()))
                }
                val job = launch {

                    Log.e(tag, "$i  Thread : " + Thread.currentThread().name + "  ")
                }

            }
        }


        lifecycleScope.launch {

        }



        val job =  launch( start = CoroutineStart.ATOMIC) {
            delay(1000)
            print("dlsdfl")
        }
        job.cancel()


        launch {
            compute(1)

            val job = async(Dispatchers.IO) { compute(2) }

            job.await()
        }

        mainScope.launch {

        }
        lifecycleScope.launchWhenStarted {

        }

        lifecycleScope.launch {
            withContext(Dispatchers.Main) {

            }
        }

//        withContext(Dispatchers.Main) {
//
//        }


//        async {  }.await()

//        GlobalScope.launch {
//
//        }
//
//        GlobalScope.async {
//
//        }
//
//
//        lifecycleScope.launch {
//
//        }
//
//
//        for (i in 1..5) {
//            //如果是网络任务需要切换调度器launch(Dispatchers.IO)
//            launch  {
//                compute(i)
//
//            }
//        }


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
        if(i == 3) throw RuntimeException("Erro")
        println("协程任务${i} 开始")
        delay(10) // 方便查看并发的时候的情况
        println("协程任务 内容值${i}")
        println("协程任务${i} 结束")

        return i
    }

    private suspend fun mergerResult(a : Int, b : Int) {
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
