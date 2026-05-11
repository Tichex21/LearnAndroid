package com.sachinshah.practical

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class Observ : LifecycleEventObserver {

    override fun onStateChanged(
        source: LifecycleOwner,
        event: Lifecycle.Event
    ) {

        when(event.name){
            Lifecycle.Event.ON_CREATE.name -> Log.e("TAG", "ON_CREATE: ")
            Lifecycle.Event.ON_START.name -> Log.e("TAG", "ON_START: ")
            Lifecycle.Event.ON_RESUME.name -> Log.e("TAG", "ON_RESUME: ")
            Lifecycle.Event.ON_PAUSE.name -> Log.e("TAG", "ON_PAUSE: ")
            Lifecycle.Event.ON_STOP.name -> Log.e("TAG", "ON_STOP: ")
            Lifecycle.Event.ON_DESTROY.name -> Log.e("TAG", "ON_DESTROY: ")

        }

    }
}