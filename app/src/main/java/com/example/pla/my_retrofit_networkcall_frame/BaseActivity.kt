package com.example.pla.my_retrofit_networkcall_frame

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.pla.my_retrofit_networkcall_frame.network.ServiceProvider
import com.example.pla.my_retrofit_networkcall_frame.network.Services

abstract class BaseActivity : AppCompatActivity() {

    val service by lazy {
        ServiceProvider.getService(Services::class.java)
    }

    /**
     * For registerReceiver {
     */

    private val filter = IntentFilter()


    private val networkChangeReceiver by lazy {
        NetworkChangeReceiver(this::hideNoInternet, this::showNoInternet)
    }

    /**
     * }
     */

    abstract val requiresNetworkConnectivity: Boolean


    override fun onStart() {
        super.onStart()

        /**
         * Register Receiver
         */
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()

        /**
         * Unregister Receiver
         */
        unregisterReceiver(networkChangeReceiver)
    }


    private fun hideNoInternet() {
        if (requiresNetworkConnectivity) {
            Toast.makeText(this, "Internet Connected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNoInternet() {
        if (requiresNetworkConnectivity) {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
        }
    }
}


