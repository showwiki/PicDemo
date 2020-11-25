package com.example.picdemo;


import android.content.Intent;
import android.util.Log;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class testActivity {
    private final String  tag = "testActivity";

    @Rule
    public ActivityTestRule<TestActivity> mActivityRule = new ActivityTestRule<>(TestActivity.class);

    @Test
    public void Test_hello() {
        TestActivity mainActy = mActivityRule.getActivity();
        Intent intent = new Intent(mainActy, TestActivity.class);
        mainActy.startActivity(intent);
    }

    @Test
    public void Test_multi_mission() {

        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String startStr = format.format(new Date());

        for(int i = 0; i < 10_0000; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(index == 0) {
                        Log.e(tag,"开始时间" + startStr);
                    }
                    Log.e(tag,"Thread :" + Thread.currentThread().getName() + "  ");

                    if(index == 10_0000 - 1) {
                        Log.e(tag,"结束时间" +  format.format(new Date()));
                    }
                }
            }).start();
        }


    }
}
