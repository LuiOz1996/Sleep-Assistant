package com.example.sleepassiatantv04

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService


//Extend WearableListenerService//
class MessageService : WearableListenerService() {
    var info = String()
    override fun onMessageReceived(messageEvent: MessageEvent) {

        if (messageEvent.getPath().equals("/my_path")) {


            val message: String = String(messageEvent.getData())
            val messageIntent = Intent()
            messageIntent.action = Intent.ACTION_SEND
            messageIntent.putExtra("message", message)
            info = messageIntent.putExtra("message", message).toString()

            LocalBroadcastManager.getInstance(this).sendBroadcast(messageIntent)
        } else {
            super.onMessageReceived(messageEvent)
        }
    }

    @JvmName("getInfo1")
    fun getInfo () :String{
        return info
    }

}

