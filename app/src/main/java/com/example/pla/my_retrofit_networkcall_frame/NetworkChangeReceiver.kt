package com.example.pla.my_retrofit_networkcall_frame

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.pla.my_retrofit_networkcall_frame.extensions.isOnline

class NetworkChangeReceiver(val noInternet: () -> Unit, val internet: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (isOnline(context)) {
            noInternet()
        } else {
            internet()
        }
    }
}