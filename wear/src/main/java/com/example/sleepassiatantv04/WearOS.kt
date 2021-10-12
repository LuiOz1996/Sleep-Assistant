@file:Suppress("DEPRECATION")

package com.example.sleepassiatantv04

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.support.wearable.view.WatchViewStub
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.Wearable
import java.util.concurrent.ExecutionException


class WearOS : WearableActivity(), SensorEventListener{

    private var mTextView :TextView? = null
    private var btnStart: ImageButton? = null
    private var btnPause: ImageButton? = null
    private var mSensorManager: SensorManager? = null
    private var mHeartRateSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stub = findViewById<View>(R.id.watch_view_stub) as WatchViewStub
        stub.setOnLayoutInflatedListener { stub ->
            mTextView = stub.findViewById<View>(R.id.heartRateText) as TextView
            btnStart = stub.findViewById<View>(R.id.btnStart) as ImageButton
            btnPause = stub.findViewById<View>(R.id.btnPause) as ImageButton
            btnStart!!.setOnClickListener {
                btnStart!!.visibility = ImageButton.GONE
                btnPause!!.visibility = ImageButton.VISIBLE
                mTextView!!.text = "Please wait..."
                startMeasure()
            }
            btnPause!!.setOnClickListener {
                btnPause!!.visibility = ImageButton.GONE
                btnStart!!.visibility = ImageButton.VISIBLE
                mTextView!!.text = "--"
                stopMeasure()
            }
        }
        setAmbientEnabled()
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mHeartRateSensor = mSensorManager!!.getDefaultSensor(Sensor.TYPE_HEART_RATE)
    }


    private fun setMessage(){
        val onClickMessage = mTextView!!.text.toString()
        mTextView!!.setText(onClickMessage)
        //Use the same path//
        val datapath = "/my_path"
        SendMessage(datapath, onClickMessage).start()
    }

    private fun startMeasure() {
        val sensorRegistered = mSensorManager!!.registerListener(
            this,
            mHeartRateSensor,
            SensorManager.SENSOR_DELAY_FASTEST
        )
        Log.d("Sensor Status:", " Sensor registered: " + if (sensorRegistered) "yes" else "no")
    }

    private fun stopMeasure() {
        mSensorManager!!.unregisterListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent) {
        val mHeartRateFloat = event.values[0]
        val mHeartRate = Math.round(mHeartRateFloat)
        mTextView!!.text = Integer.toString(mHeartRate)
        setMessage()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }

    inner class Receiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

//Display the following when a new message is received//
            val onMessageReceived = ""
            mTextView!!.text = onMessageReceived
        }
    }

    internal inner class SendMessage     //Constructor for sending information to the Data Layer//
        (var path: String, var message: String) : Thread() {
        override fun run() {

//Retrieve the connected devices//
            val nodeListTask = Wearable.getNodeClient(
                applicationContext
            ).connectedNodes
            try {

//Block on a task and get the result synchronously//
                val nodes = Tasks.await(nodeListTask)
                for (node in nodes) {

//Send the message///
                    val sendMessageTask = Wearable.getMessageClient(this@WearOS)
                        .sendMessage(node.id, path, message.toByteArray())
                    try {
                        val result = Tasks.await(sendMessageTask)

//Handle the errors//
                    } catch (exception: ExecutionException) {

//TO DO//
                    } catch (exception: InterruptedException) {

//TO DO//
                    }
                }
            } catch (exception: ExecutionException) {

//TO DO//
            } catch (exception: InterruptedException) {

//TO DO//
            }
        }
    }
}
