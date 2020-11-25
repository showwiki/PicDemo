package com.example.picdemo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel : ViewModel(), CoroutineScope{
    //统一处理协程中异常的报错
    val handlerCoroutine = CoroutineExceptionHandler { _, e ->
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw, true))
        val str = sw.toString()
        Log.e("协程", str)
    }

    private val baseViewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = baseViewModelJob + Dispatchers.Main + handlerCoroutine + Dispatchers.IO

    override fun onCleared() {
        cancel()
        super.onCleared()
    }


}