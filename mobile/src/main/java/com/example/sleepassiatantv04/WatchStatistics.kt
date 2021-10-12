package com.example.sleepassiatantv04

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_connect_smart_watch.button_go_back_cw
import kotlinx.android.synthetic.main.activity_watch_statistic.*

class WatchStatistics : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_statistic)


        /*val info = intent.extras
        val pulse: Int = info!!.get("pulse") as Int
        val data :String = info.get("data") as String
        val time :String = info.get("time") as String */

        button_go_back_cw.setOnClickListener{
            val Intent_go_back_menu = Intent(this,Menu::class.java)
            startActivity(Intent_go_back_menu)
        }

        button_give_rusult.setOnClickListener {
            heart_pulse.text = "68"
            heart_data.text = "16.05.2021"
            heart_time.text = "2:30"
        }

    }
}