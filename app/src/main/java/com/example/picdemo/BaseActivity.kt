package com.example.picdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseActivity : AppCompatActivity(), CoroutineScope {

    //统一处理协程中异常的报错
    val handlerCoroutine = CoroutineExceptionHandler { _, e ->
        println("协程任务  父类已捕获")
    }


    private val topParentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = topParentJob + Dispatchers.Main + handlerCoroutine

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}