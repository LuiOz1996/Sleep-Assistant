package com.example.sleepassiatantv04

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import kotlinx.android.synthetic.main.activity_talk_with_wear.*
import java.util.concurrent.ExecutionException


class TalkWithWear : AppCompatActivity() {
    protected var myHandler: Handler? = null
    var receivedMessageNumber = 1
    var sentMessageNumber = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talk_with_wear)

        //Create a message handler//
        myHandler = Handler { msg ->
            val stuff = msg.data
            messageText(stuff.getString("messageText"))
            true
        }

        //Register to receive local broadcasts, which we'll be creating in the next step//

        val messageFilter = IntentFilter(Intent.ACTION_SEND)
        val messageReceiver: Receiver = Receiver()
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, messageFilter)


        button_go_back_cw.setOnClickListener{
            val Intent_go_back_menu = Intent(this,Menu::class.java)
            startActivity(Intent_go_back_menu)
        }
    }

///////////////////////////
    fun messageText(newinfo: String?) {
        if (newinfo!!.compareTo("") != 0) {
            heardRate!!.append("""
    
    $newinfo
    """.trimIndent())
        }
    }


    //////////////////////////
    inner class Receiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val message = intent.getStringExtra("message")
            heardRate!!.text = message
        }
    }

    fun talkClick(v: View?) {
        val message = "Sending message.... "
        heardRate!!.text = message


        NewThread("/my_path", message).start()
    }


    ////////////////////////////////
    internal inner class NewThread
        (var path: String, var message: String) : Thread() {
        override fun run() {

            val wearableList: Task<List<Node>> = Wearable.getNodeClient(applicationContext).getConnectedNodes()
            try {
                val nodes: List<Node> = Tasks.await(wearableList)
                for (node in nodes) {
                    val sendMessageTask: Task<Int> =  //Send the message//
                        Wearable.getMessageClient(this@TalkWithWear).sendMessage(node.getId(),
                            path, message.toByteArray())
                    try {


                        val result: Int = Tasks.await(sendMessageTask)
                        sendmessage("" + sentMessageNumber++)

                        //if the Task fails, then…..//
                    } catch (exception: ExecutionException) {

                        //TO DO: Handle the exception//
                    } catch (exception: InterruptedException) {

                        //TO DO: Handle the exception//
                    }
                }
            } catch (exception: ExecutionException) {

                //TO DO: Handle the exception//
            } catch (exception: InterruptedException) {

                //TO DO: Handle the exception//
            }
        }
    }

    fun sendmessage(messageText: String?) {
        val bundle = Bundle()
        bundle.putString("messageText", messageText)
        val msg = myHandler!!.obtainMessage()
        msg.data = bundle
        myHandler!!.sendMessage(msg)
    }
}