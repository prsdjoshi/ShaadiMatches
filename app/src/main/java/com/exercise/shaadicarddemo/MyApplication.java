/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exercise.shaadicarddemo;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.exercise.shaadicarddemo.model.webservice.ApiFactory;
import com.exercise.shaadicarddemo.model.webservice.ApiService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class MyApplication extends MultiDexApplication {

  private ApiService apiService;
  private Scheduler scheduler;

  private static MyApplication get(Context context) {
    return (MyApplication) context.getApplicationContext();
  }

  public static MyApplication create(Context context) {
    return MyApplication.get(context);
  }

  public ApiService getApiService() {
    if (apiService == null) {
      apiService = ApiFactory.create();
    }

    return apiService;
  }

  public Scheduler subscribeScheduler() {
    if (scheduler == null) {
      scheduler = Schedulers.io();
    }

    return scheduler;
  }
}
