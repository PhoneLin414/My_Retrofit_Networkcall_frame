package com.example.pla.my_retrofit_networkcall_frame

import android.os.Bundle
import android.widget.Toast
import com.example.pla.my_retrofit_networkcall_frame.config.Config
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override val requiresNetworkConnectivity: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Button OnClick
         */

        btnSync.setOnClickListener {

            service.getNowPlayingMovieList(Config.API_KEY,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    Toast.makeText(this,"DoOnNext",Toast.LENGTH_SHORT).show()
                }
                .subscribe({
                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                    Toast.makeText(this,it.results.size.toString(),Toast.LENGTH_SHORT).show()
                },{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                })

        }

    }
}
