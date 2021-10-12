package com.example.sleepassiatantv04

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.playlistv02.CommonOnClickListener
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.*
import kotlinx.android.synthetic.main.activity_connect_smart_watch.*
import java.util.*

class ConnectSmartWatch: AppCompatActivity(), DataClient.OnDataChangedListener,
    MessageClient.OnMessageReceivedListener,
    CapabilityClient.OnCapabilityChangedListener, CommonOnClickListener {
    var activityContext: Context? = null
    private val wearableAppCheckPayload = "AppOpenWearable"
    private val wearableAppCheckPayloadReturnACK = "AppOpenWearableACK"
    private var wearableDeviceConnected: Boolean = false

    private var currentAckFromWearForAppOpenCheck: String? = null
    private val APP_OPEN_WEARABLE_PAYLOAD_PATH = "/APP_OPEN_WEARABLE_PAYLOAD"
    private val TAG_GET_NODES: String = "getnodes1"



    // bluetooth adapter
    lateinit var bluetoothAdapter :BluetoothAdapter

    private val REQUEST_CODE_ENABLE_BT = 1


    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_smart_watch)

        activityContext = this
        wearableDeviceConnected = false

        // init bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        // check is bluetooth available or not
        if(bluetoothAdapter == null){
            status_of_bluetoth.text = "Bluetooth is not available"
        }else{
            status_of_bluetoth.text = "Bluetooth is available"
        }

        // turn on bluetooth
        button_turn_on_bt.setOnClickListener {
            if(bluetoothAdapter.isEnabled){
                // already enabled
                Toast.makeText(this,"Already on",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent,REQUEST_CODE_ENABLE_BT)
            }
        }
        // turn off bluetooth
        button_turn_off_bt.setOnClickListener {
            if(!bluetoothAdapter.isEnabled){
                // already enabled
                Toast.makeText(this,"Already off",Toast.LENGTH_LONG).show()
            }else{
                bluetoothAdapter.disable()
                Toast.makeText(this,"Bluetooth turned off",Toast.LENGTH_LONG).show()
            }
        }


        button_go_back_cw.setOnClickListener{
            val Intent_go_back_menu = Intent(this,Menu::class.java)
            startActivity(Intent_go_back_menu)
        }

        button_connect_smart_watch.setOnClickListener {

            val BD = BluetoothDevice.getBluetoothDevice()
            val adapter = Adapter(this,BD,this)
            val recycle = findViewById<RecyclerView>(R.id.recycle_bluetooth)
            recycle.adapter = adapter

            if (!wearableDeviceConnected) {
                val tempAct: Activity = activityContext as ConnectSmartWatch
                AsyncTask.execute {
                    try {
                        val getNodesResBool = getNodes(tempAct.applicationContext)

                        //UI thread
                        tempAct.runOnUiThread {
                            if (getNodesResBool!![0]) {
                                //if message Acknowlegement Received
                                if (getNodesResBool[1]) {
                                    Toast.makeText(
                                        activityContext,
                                        "Wearable device paired and app is open. Tap on the paired watch",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        activityContext,
                                        "Wearable device paired and app is open. Tap on the paired watch",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    activityContext,
                                    "No wearable device paired. Pair a wearable device to your phone using the Wear OS app and try again.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

    }

    ////////////////////////
    private fun getNodes(context: Context): BooleanArray? {
        val nodeResults = HashSet<String>()
        val resBool = BooleanArray(2)
        resBool[0] = false //nodePresent
        resBool[1] = false //wearableReturnAckReceived
        val nodeListTask =
            Wearable.getNodeClient(context).connectedNodes
        try {
            // Block on a task and get the result synchronously (because this is on a background thread).
            val nodes =
                Tasks.await(
                    nodeListTask
                )
            Log.e(TAG_GET_NODES, "Task fetched nodes")
            for (node in nodes) {
                Log.e(TAG_GET_NODES, "inside loop")
                nodeResults.add(node.id)
                try {
                    val nodeId = node.id
                    // Set the data of the message to be the bytes of the Uri.
                    val payload: ByteArray = wearableAppCheckPayload.toByteArray()
                    // Send the rpc
                    // Instantiates clients without member variables, as clients are inexpensive to
                    // create. (They are cached and shared between GoogleApi instances.)
                    val sendMessageTask =
                        Wearable.getMessageClient(context)
                            .sendMessage(nodeId, APP_OPEN_WEARABLE_PAYLOAD_PATH, payload)
                    try {
                        // Block on a task and get the result synchronously (because this is on a background thread).
                        val result = Tasks.await(sendMessageTask)
                        Log.d(TAG_GET_NODES, "send message result : $result")
                        resBool[0] = true
                        //Wait for 1000 ms/1 sec for the acknowledgement message
                        //Wait 1
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(100)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 1")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 2
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(150)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 2")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 3
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(200)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 3")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 4
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(250)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 4")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 5
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(350)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 5")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        resBool[1] = false
                        Log.d(
                            TAG_GET_NODES,
                            "ACK thread timeout, no message received from the wearable "
                        )
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }
                } catch (e1: Exception) {
                    Log.d(TAG_GET_NODES, "send message exception")
                    e1.printStackTrace()
                }
            } //end of for loop
        } catch (exception: Exception) {
            Log.e(TAG_GET_NODES, "Task failed: $exception")
            exception.printStackTrace()
        }
        return resBool
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CODE_ENABLE_BT ->
                if(requestCode == Activity.RESULT_OK){
                    Toast.makeText(this,"Bluetooth is on",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Cloud not on bluetooth",Toast.LENGTH_LONG).show()
                }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(position: Int) {
        if (position == 0) {
            Toast.makeText(activityContext, "This device is not paired",Toast.LENGTH_LONG).show()
        }
        if (position == 1) {
            Toast.makeText(activityContext, "This device is not paired",Toast.LENGTH_LONG).show()
        }
        if (position == 2) {
            val intent = Intent(this,TalkWithWear::class.java)
            startActivity(intent)
        }
        if (position == 3) {
            Toast.makeText(activityContext, "This device is not paired",Toast.LENGTH_LONG).show()
        }
        if (position == 4){
            Toast.makeText(activityContext, "This device is not paired",Toast.LENGTH_LONG).show()
        }
    }

    override fun onDataChanged(p0: DataEventBuffer) {
        TODO("Not yet implemented")
    }


    override fun onCapabilityChanged(p0: CapabilityInfo) {
        TODO("Not yet implemented")
    }

    override fun onMessageReceived(p0: MessageEvent) {
        TODO("Not yet implemented")
    }
}