/*
    Copyright 2018 Gaurav Kumar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.github.mvpbasearchitecture.utils;

import android.util.Log;

import com.github.mvpbasearchitecture.BuildConfig;

/**
 * An App logger to only log in Debug mode
 *
 * Created by gk
 */

public class AppLogger {

    private static boolean isDebugMode = true;

    public static void init(){
        if (!BuildConfig.DEBUG){
            isDebugMode = false;
        }
    }

    public static void d(String tag, String msg){
        if(isDebugMode)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg){
        if(isDebugMode)
            Log.e(tag, msg);
    }

    public static void w(String tag, String msg){
        if(isDebugMode)
            Log.w(tag, msg);
    }

    public static void i(String tag, String msg){
        if(isDebugMode)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg){
        if(isDebugMode)
            Log.v(tag, msg);
    }

}
