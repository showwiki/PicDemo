package com.example.picdemo

import android.app.Application
import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PicApplication : Application(){

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        GlobalScope.launch {

        }
    }

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {

        }
    }
}