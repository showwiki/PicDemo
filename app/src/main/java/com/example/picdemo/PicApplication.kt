package com.example.picdemo

import android.app.Application
import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PicApplication : Application(){

    companion object {
        val testChannel = Channel<Int>()
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        GlobalScope.launch {
            delay(5000)
            testChannel.send(2000)
        }
    }

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {

        }
    }
}