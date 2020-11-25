package com.example.picdemo

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val tag = "ExampleInstrumentedTest"
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.picdemo", appContext.packageName)
    }

    @Test
    fun executeMulti() {
        val format = SimpleDateFormat("yy-MM-dd HH:mm:ss")
        val str =  format.format(Date())
        Log.e(tag, "开始时间" + str)
        for (i in 0..10_0000) {
            GlobalScope.launch {
                Log.e(tag, "$i  Thread : " + Thread.currentThread().name + "  ")
            }
        }
        Log.e(tag, "结束时间" + format.format(Date()))
    }
}