package com.example.sleepassiatantv04

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_talk_with_wear.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutionException

@Suppress("DEPRECATION")
class Player() : AppCompatActivity() {

    private lateinit var MP : MediaPlayer
    private var total_time : Int = 0
    var next = 0
    private var isPlay = false

    protected var myHandler: Handler? = null
    var receivedMessageNumber = 1
    var sentMessageNumber = 1
    var arryOfHeardRate = arrayListOf<Int>(75,76,78,79,80)

    fun getIsPlay():Boolean{
        return isPlay
    }

    fun getinfoflag():Int{
        val info = intent.extras
        val flag :Int = info!!.get("flag") as Int
        return flag
    }

    fun getinfoisplaylist():Int{
        val info = intent.extras
        val isplaylist :Int = info!!.get("isplaylist") as Int
        return isplaylist
    }

    fun getDuration(buffer : String):Int{
      var duration : Int = 0
        if(buffer == "Felix & LeeKnow"){
            duration = 1630400
        }
        if(buffer == "Felix Chrissy"){
            duration = 620322
        }
        if(buffer == "Felix G’day mate"){
            duration = 661266
        }
        if(buffer == "Felix Good on ya!"){
            duration = 664341
        }
        if(buffer == "TXT ASMR"){
            duration = 1882216
        }
        if(buffer == "Felix Bikkie & Choccy"){
            duration = 714459
        }
        if(buffer == "Charlie & Chocolate Factory"){
            duration = 105066
        }
        if(buffer == "Peter Pan"){
            duration = 84763
        }
        if(buffer == "Home Along"){
            duration = 81453
        }
        if(buffer == "Betkhoven Moonlight Sonata"){
            duration = 331860
        }
        if(buffer == "Edvard Grig Final Per Gyunt"){
            duration = 192583
        }
        if(buffer == "Hans Zimmer Hero"){
            duration = 289463
        }
        if(buffer == "Japanese sakura"){
            duration = 570790
        }
        if(buffer == "Lee Felix"){
            duration = 72377
        }
        if(buffer == "Min Yoon Gi"){
            duration = 106525
        }
        if(buffer == "Kim Tea Huyng"){
            duration = 106182
        }
        if(buffer == "Kim Nam Joon"){
            duration = 163771
        }
        if(buffer == "Park Chi Min"){
            duration = 44557
        }
        if(buffer == "Jeon Jongguk"){
            duration = 105907
        }
        return duration
    }

    @SuppressLint("SetTextI18n")
    fun playNextforButton(v: View){

        val buffer = nameTrack.text.toString()
        val isplaylist = getinfoisplaylist()


        if (buffer == "Felix & LeeKnow" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixchrissy)
            nameTrack.text = "Felix Chrissy"
        }
        if (buffer == "Felix Chrissy" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgdaymate)
            nameTrack.text = "Felix G’day mate"
        }
        if (buffer == "Felix G’day mate" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgoodonya)
            nameTrack.text = "Felix Good on ya!"
        }
        if (buffer == "Felix Good on ya!" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.txtasmr)
            nameTrack.text = "TXT ASMR"
        }
        if (buffer == "TXT ASMR" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixbikkiechoccy)
            nameTrack.text = "Felix Bikkie & Choccy"
        }
        if (buffer == "Felix Bikkie & Choccy" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixandleeknow)
            nameTrack.text = "Felix & LeeKnow"
        }
        if (buffer == "Charlie & Chocolate Factory" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.peter_pan)
            nameTrack.text = "Peter Pan"
        }
        if (buffer == "Peter Pan" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.homealong)
            nameTrack.text = "Home Along"
        }
        if (buffer == "Home Along" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.charlie_and_chocolate_factory)
            nameTrack.text = "Charlie & Chocolate Factory"
        }
        if (buffer == "Betkhoven Moonlight Sonata" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.edvardgrigfinalpergyunt)
            nameTrack.text = "Edvard Grig Final Per Gyunt"
        }
        if (buffer == "Edvard Grig Final Per Gyunt" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.hanszimmerhero)
            nameTrack.text = "Hans Zimmer Hero"
        }
        if (buffer == "Hans Zimmer Hero" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.japanesesakura)
            nameTrack.text = "Japanese sakura"
        }
        if (buffer == "Japanese sakura" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.betkhovenmoonlightsonata)
            nameTrack.text = "Betkhoven Moonlight Sonata"
        }
        if (buffer == "Felix & LeeKnow" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixchrissy)
            nameTrack.text = "Felix Chrissy"
        }
        if (buffer == "Felix Chrissy" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgdaymate)
            nameTrack.text = "Felix G’day mate"
        }
        if (buffer == "Felix G’day mate" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgoodonya)
            nameTrack.text = "Felix Good on ya!"
        }
        if (buffer == "Felix Good on ya!" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.txtasmr)
            nameTrack.text = "TXT ASMR"
        }
        if (buffer == "TXT ASMR" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixbikkiechoccy)
            nameTrack.text = "Felix Bikkie & Choccy"
        }
        if (buffer == "Felix Bikkie & Choccy"&& isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.charlie_and_chocolate_factory)
            nameTrack.text = "Charlie & Chocolate Factory"
        }
        if (buffer == "Charlie & Chocolate Factory"&& isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.peter_pan)
            nameTrack.text ="Peter Pan"
        }
        if (buffer == "Peter Pan" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.homealong)
            nameTrack.text = "Home Along"
        }
        if (buffer == "Home Along" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.betkhovenmoonlightsonata)
            nameTrack.text = "Betkhoven Moonlight Sonata"
        }
        if (buffer == "Betkhoven Moonlight Sonata" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.edvardgrigfinalpergyunt)
            nameTrack.text = "Edvard Grig Final Per Gyunt"
        }
        if (buffer == "Edvard Grig Final Per Gyunt" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.hanszimmerhero)
            nameTrack.text = "Hans Zimmer Hero"
        }
        if (buffer == "Hans Zimmer Hero" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.japanesesakura)
            nameTrack.text = "Japanese sakura"
        }
        if (buffer == "Japanese sakura" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixandleeknow)
            nameTrack.text = "Felix & LeeKnow"
        }
        if(buffer == "Lee Felix" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this,R.raw.min_yoon_gi)
            nameTrack.text = "Min Yoon Gi"
        }
        if(buffer == "Min Yoon Gi" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this,R.raw.kim_tea_huyng)
            nameTrack.text = "Kim Tea Huyng"
        }
        if(buffer == "Kim Tea Huyng" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this,R.raw.kim_nam_joon)
            nameTrack.text = "Kim Nam Joon"
        }
        if(buffer == "Kim Nam Joon" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this,R.raw.park_chi_min)
            nameTrack.text = "Park Ji Min"
        }
        if(buffer == "Park Ji Min" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this,R.raw.jeon_jongguk)
            nameTrack.text = "Jeon Jongguk"
        }
        if(buffer == "Jeon Jongguk" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this,R.raw.lee_felix)
            nameTrack.text = "Lee Felix"
        }

        total_time = MP.duration
        MP.start()

        positionBar.max = total_time
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,progress: Int,fromUser: Boolean) {
                    if(fromUser){
                        MP.seekTo(progress)
                    }
                    //loonyPlay(positionBar)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            }
        )

    }

    @SuppressLint("SetTextI18n")
    fun playLastforButton(v : View){

        val buffer = nameTrack.text.toString()
        val isplaylist = getinfoisplaylist()


        if (buffer == "Felix Bikkie & Choccy" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.txtasmr)
            nameTrack.text = "TXT ASMR"
        }
        if (buffer == "TXT ASMR" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgoodonya)
            nameTrack.text = "Felix Good on ya!"
        }
        if (buffer == "Felix Good on ya!" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgdaymate)
            nameTrack.text = "Felix G’day mate"
        }
        if (buffer == "Felix G’day mate" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixchrissy)
            nameTrack.text = "Felix Chrissy"
        }
        if (buffer == "Felix Chrissy" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixandleeknow)
            nameTrack.text = "Felix & LeeKnow"
        }
        if (buffer == "Felix & LeeKnow" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixbikkiechoccy)
            nameTrack.text = "Felix Bikkie & Choccy"
        }
        if (buffer == "Home Along" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.peter_pan)
            nameTrack.text = "Peter Pan"
        }
        if (buffer == "Peter Pan" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.charlie_and_chocolate_factory)
            nameTrack.text = "Charlie & Chocolate Factory"
        }
        if (buffer == "Charlie & Chocolate Factory" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.homealong)
            nameTrack.text = "Home Along"
        }
        if (buffer == "Japanese sakura" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.hanszimmerhero)
            nameTrack.text = "Hans Zimmer Hero"
        }
        if (buffer == "Hans Zimmer Hero" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.edvardgrigfinalpergyunt)
            nameTrack.text = "Edvard Grig Final Per Gyunt"
        }
        if (buffer == "Edvard Grig Final Per Gyunt" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.betkhovenmoonlightsonata)
            nameTrack.text = "Betkhoven Moonlight Sonata"
        }
        if (buffer == "Betkhoven Moonlight Sonata" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.japanesesakura)
            nameTrack.text = "Japanese sakura"
        }
        if (buffer == "Japanese sakura" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.hanszimmerhero)
            nameTrack.text = "Hans Zimmer Hero"
        }
        if (buffer == "Hans Zimmer Hero" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.edvardgrigfinalpergyunt)
            nameTrack.text = "Edvard Grig Final Per Gyunt"
        }
        if (buffer == "Edvard Grig Final Per Gyunt" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.betkhovenmoonlightsonata)
            nameTrack.text = "Betkhoven Moonlight Sonata"
        }
        if (buffer == "Betkhoven Moonlight Sonata" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.homealong)
            nameTrack.text = "Home Along"
        }
        if (buffer == "Home Along" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.peter_pan)
            nameTrack.text = "Peter Pan"
        }
        if (buffer == "Peter Pan" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.charlie_and_chocolate_factory)
            nameTrack.text = "Charlie & Chocolate Factory"
        }
        if (buffer == "Charlie & Chocolate Factory" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixbikkiechoccy)
            nameTrack.text = "Felix Bikkie & Choccy"
        }
        if (buffer == "Felix Bikkie & Choccy" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.txtasmr)
            nameTrack.text = "TXT ASMR"
        }
        if (buffer == "TXT ASMR" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgoodonya)
            nameTrack.text = "Felix Good on ya!"
        }
        if (buffer == "Felix Good on ya!" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgdaymate)
            nameTrack.text = "Felix G’day mate"
        }
        if (buffer == "Felix G’day mate" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixchrissy)
            nameTrack.text = "Felix Chrissy"
        }
        if (buffer == "Felix Chrissy" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixandleeknow)
            nameTrack.text = "Felix & LeeKnow"
        }
        if (buffer == "Felix & LeeKnow" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.japanesesakura)
            nameTrack.text = "Japanese sakura"
        }
        if (buffer == "Jeon Jongguk" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.park_chi_min)
            nameTrack.text = "Park Ji Min"
        }
        if (buffer == "Park Ji Min" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.kim_nam_joon)
            nameTrack.text = "Kim Nam Joon"
        }
        if (buffer == "Kim Nam Joon" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.kim_tea_huyng)
            nameTrack.text = "Kim Tea Huyng"
        }
        if (buffer == "Kim Tea Huyng" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.min_yoon_gi)
            nameTrack.text = "Min Yoon Gi"
        }
        if (buffer == "Min Yoon Gi" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.lee_felix)
            nameTrack.text = "Lee Felix"
        }
        if (buffer == "Lee Felix" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.jeon_jongguk)
            nameTrack.text = "Jeon Jongguk"
        }

        total_time = MP.duration
        MP.start()

        positionBar.max = total_time
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,progress: Int,fromUser: Boolean) {
                    if(fromUser){
                        MP.seekTo(progress)
                    }
                    //loonyPlay(positionBar)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            }
        )
    }

    @SuppressLint("SetTextI18n")
    fun loonyPlay(v : View){
        val buffer = nameTrack.text.toString()
        val isplaylist = getinfoisplaylist()
        val isPlay = getIsPlay()

        total_time = getDuration(buffer)

        if (positionBar.progress == total_time && buffer == "Felix & LeeKnow" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixchrissy)
            nameTrack.text = "Felix Chrissy"
        }
        if (positionBar.progress == total_time && buffer == "Felix Chrissy" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgdaymate)
            nameTrack.text = "Felix G’day mate"
        }
        if (positionBar.progress == total_time && buffer == "Felix G’day mate" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgoodonya)
            nameTrack.text = "Felix Good on ya!"
        }
        if (positionBar.progress == total_time && buffer == "Felix Good on ya!" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.txtasmr)
            nameTrack.text = "TXT ASMR"
        }
        if (positionBar.progress == total_time && buffer == "TXT ASMR" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixbikkiechoccy)
            nameTrack.text = "Felix Bikkie & Choccy"
        }
        if (positionBar.progress == total_time && buffer == "Felix Bikkie & Choccy" && isplaylist == 1){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixandleeknow)
            nameTrack.text = "Felix & LeeKnow"
        }
        if (positionBar.progress == total_time && buffer == "Charlie & Chocolate Factory" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.peter_pan)
            nameTrack.text = "Peter Pan"
        }
        if (positionBar.progress == total_time && buffer == "Peter Pan" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.homealong)
            nameTrack.text = "Home Along"
        }
        if (positionBar.progress == total_time && buffer == "Home Along" && isplaylist == 2){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.charlie_and_chocolate_factory)
            nameTrack.text = "Charlie & Chocolate Factory"
        }
        if (positionBar.progress == total_time && buffer == "Betkhoven Moonlight Sonata" && isplaylist == 3 && isPlay){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.edvardgrigfinalpergyunt)
            nameTrack.text = "Edvard Grig Final Per Gyunt"
        }
        if (positionBar.progress == total_time && buffer == "Edvard Grig Final Per Gyunt" && isplaylist == 3 && isPlay){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.hanszimmerhero)
            nameTrack.text = "Hans Zimmer Hero"
        }
        if (positionBar.progress == total_time && buffer == "Hans Zimmer Hero" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.japanesesakura)
            nameTrack.text = "Japanese sakura"
        }
        if (positionBar.progress == total_time && buffer == "Japanese sakura" && isplaylist == 3){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.betkhovenmoonlightsonata)
            nameTrack.text = "Betkhoven Moonlight Sonata"
        }
        if (positionBar.progress == total_time && buffer == "Felix & LeeKnow" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixchrissy)
            nameTrack.text = "Felix Chrissy"
        }
        if (positionBar.progress == total_time && buffer == "Felix Chrissy" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgdaymate)
            nameTrack.text = "Felix G’day mate"
        }
        if (positionBar.progress == total_time && buffer == "Felix G’day mate" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixgoodonya)
            nameTrack.text = "Felix Good on ya!"
        }
        if (positionBar.progress == total_time && buffer == "Felix Good on ya!" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.txtasmr)
            nameTrack.text = "TXT ASMR"
        }
        if (positionBar.progress == total_time && buffer == "TXT ASMR" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixbikkiechoccy)
            nameTrack.text = "Felix Bikkie & Choccy"
        }
        if (positionBar.progress == total_time && buffer == "Felix Bikkie & Choccy"&& isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.charlie_and_chocolate_factory)
            nameTrack.text = "Charlie & Chocolate Factory"
        }
        if (positionBar.progress == total_time && buffer == "Charlie & Chocolate Factory"&& isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.peter_pan)
            nameTrack.text ="Peter Pan"
        }
        if (positionBar.progress == total_time && buffer == "Peter Pan" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.homealong)
            nameTrack.text = "Home Along"
        }
        if (positionBar.progress == total_time && buffer == "Home Along" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.betkhovenmoonlightsonata)
            nameTrack.text = "Betkhoven Moonlight Sonata"
        }
        if (positionBar.progress == total_time && buffer == "Betkhoven Moonlight Sonata" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.edvardgrigfinalpergyunt)
            nameTrack.text = "Edvard Grig Final Per Gyunt"
        }
        if (positionBar.progress == total_time && buffer == "Edvard Grig Final Per Gyunt" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.hanszimmerhero)
            nameTrack.text = "Hans Zimmer Hero"
        }
        if (positionBar.progress == total_time && buffer == "Hans Zimmer Hero" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.japanesesakura)
            nameTrack.text = "Japanese sakura"
        }
        if (positionBar.progress == total_time && buffer == "Japanese sakura" && isplaylist == 4){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.felixandleeknow)
            nameTrack.text = "Felix & LeeKnow"
        }
        if (positionBar.progress == total_time && buffer == "Lee Felix" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.min_yoon_gi)
            nameTrack.text = "Min Yoon Gi"
        }
        if (positionBar.progress == total_time && buffer == "Min Yoon Gi" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.kim_tea_huyng)
            nameTrack.text = "Kim Tea Huyng"
        }
        if (positionBar.progress == total_time && buffer == "Kim Tea Huyng" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.kim_nam_joon)
            nameTrack.text = "Kim Nam Joon"
        }
        if (positionBar.progress == total_time && buffer == "Kim Nam Joon" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.park_chi_min)
            nameTrack.text = "Park Ji Min"
        }
        if (positionBar.progress == total_time && buffer == "Park Ji Min" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.jeon_jongguk)
            nameTrack.text = "Jeon Jongguk"
        }
        if (positionBar.progress == total_time && buffer == "Jeon Jongguk" && isplaylist == 5){
            MP.release()
            MP = MediaPlayer.create(this, R.raw.lee_felix)
            nameTrack.text = "Lee Felix"
        }

        positionBar.max = getDuration(buffer)
        total_time = MP.duration
        MP.start()
    }

    fun playTrack(v:View){

        if (MP.isPlaying){
            //Stop
            MP.pause()
            ButtonPlayTrack.setBackgroundResource(R.drawable.icon_play)
            isPlay = false
        }
        else{
            //Start
            MP.start()
            ButtonPlayTrack.setBackgroundResource(R.drawable.icon_pause)
            isPlay = true
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)



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

        val flag = getinfoflag()

        if (flag == 0) {
            nameTrack.text = "Felix & LeeKnow"
            MP = MediaPlayer.create(this, R.raw.felixandleeknow)
        }
        if (flag == 1) {
            nameTrack.text = "Felix Chrissy"
            MP = MediaPlayer.create(this, R.raw.felixchrissy)
        }
        if (flag == 2) {
            nameTrack.text = "Felix G’day mate"
            MP = MediaPlayer.create(this, R.raw.felixgdaymate)
        }
        if (flag == 3) {
            nameTrack.text = "Felix Good on ya!"
            MP = MediaPlayer.create(this, R.raw.felixgoodonya)
        }
        if (flag == 4) {
            nameTrack.text = "TXT ASMR"
            MP = MediaPlayer.create(this, R.raw.txtasmr)
        }
        if (flag == 5) {
            nameTrack.text = "Felix Bikkie & Choccy"
            MP = MediaPlayer.create(this, R.raw.felixbikkiechoccy)
        }
        if (flag == 100){
            nameTrack.text = "Charlie & Chocolate Factory"
            MP = MediaPlayer.create(this, R.raw.charlie_and_chocolate_factory)
        }
        if (flag == 101){
            nameTrack.text = "Peter Pan"
            MP = MediaPlayer.create(this, R.raw.peter_pan)
        }
        if (flag == 102){
            nameTrack.text = "Home Along"
            MP = MediaPlayer.create(this, R.raw.homealong)
        }
        if (flag == 1000){
            nameTrack.text = "Betkhoven Moonlight Sonata"
            MP = MediaPlayer.create(this, R.raw.betkhovenmoonlightsonata)
        }
        if (flag == 1001){
            nameTrack.text = "Edvard Grig Final Per Gyunt"
            MP = MediaPlayer.create(this, R.raw.edvardgrigfinalpergyunt)
        }
        if (flag == 1002){
            nameTrack.text = "Hans Zimmer Hero"
            MP = MediaPlayer.create(this, R.raw.hanszimmerhero)
        }
        if (flag == 1003){
            nameTrack.text = "Japanese sakura"
            MP = MediaPlayer.create(this, R.raw.japanesesakura)
        }
        if(flag == 50){
            nameTrack.text = "Lee Felix"
            MP = MediaPlayer.create(this,R.raw.lee_felix)
        }
        if(flag == 51){
            nameTrack.text = "Min Yoon Gi"
            MP = MediaPlayer.create(this,R.raw.min_yoon_gi)
        }
        if(flag == 52){
            nameTrack.text = "Kim Tea Huyng"
            MP = MediaPlayer.create(this,R.raw.kim_tea_huyng)
        }
        if(flag == 53){
            nameTrack.text = "Kim Nam Joon"
            MP = MediaPlayer.create(this,R.raw.kim_nam_joon)
        }
        if(flag == 54){
            nameTrack.text = "Park Ji Min" 
            MP = MediaPlayer.create(this,R.raw.park_chi_min)
        }
        if(flag == 55){
            nameTrack.text = "Jeon Jongguk"
            MP = MediaPlayer.create(this,R.raw.jeon_jongguk)
        }

        MP.start()

        ButtonPlayTrack.setBackgroundResource(R.drawable.icon_pause)
        MP.setVolume(0.5f,0.5f)
        total_time = MP.duration


        // Volume Bar

        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,progress: Int,fromUser: Boolean) {
                    if(fromUser){
                        val volumeNumber = progress/100.0f
                        MP.setVolume(volumeNumber,volumeNumber)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )

        // Position Bar
        positionBar.max = total_time
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,progress: Int,fromUser: Boolean) {
                    if(fromUser){
                        MP.seekTo(progress)
                    }
                    //HeardControl()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            }
        )

        // Thread
        Thread(Runnable {
            while(MP != null){
                try{
                    val msg = Message()
                    msg.what = MP.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e :InterruptedException){
                }
            }
        }).start()



        ButtonPlayTrack.setOnClickListener(this::playTrack)
        ButtonNextTrack.setOnClickListener(this::playNextforButton)
        ButtonLastTrack.setOnClickListener(this::playLastforButton)
        fucking_shit.text = total_time.toString()

    }

    override fun onPause() {
      super.onPause()
      positionBar.max = total_time
      positionBar.setOnSeekBarChangeListener(
          object : SeekBar.OnSeekBarChangeListener{
              override fun onProgressChanged(seekBar: SeekBar?,progress: Int,fromUser: Boolean) {
                  if (fromUser) {
                      MP.seekTo(progress)
                  }
                  miniHeardControl()
              }
              override fun onStartTrackingTouch(seekBar: SeekBar?) {

              }

              override fun onStopTrackingTouch(seekBar: SeekBar?) {

              }

          }
      )
   }


    override fun onResume() {
        super.onResume()
        if (!MP.isPlaying){
            MP.start()
            ButtonPlayTrack.setBackgroundResource(R.drawable.icon_pause)
        }
    }

    fun HeardControl(){
     positionBar.max = total_time
     val intent = Intent(this,WatchStatistics::class.java)

     val heardTrack = myHeard.text.toString().toInt()
     arryOfHeardRate.add(heardTrack)
     val buffer = arryOfHeardRate.size
     val comparison = arryOfHeardRate[buffer] * 0.9
     if (isPlay) {
         //comparison > arryOfHeardRate[buffer - 5] || heardTrack > 60
         if ( heardTrack > 80) {
             loonyPlay(positionBar)
         }
         else{
             MP.pause()
             val current = LocalDateTime.now()
             val formOfData = DateTimeFormatter.ofPattern("dd.MM.yyyy.")
             val formOftime = DateTimeFormatter.ofPattern("HH:mm:ss")
             val data: String =  current.format(formOfData)
             val time: String = current.format(formOftime)
             intent.putExtra("pulse",arryOfHeardRate[buffer])
             intent.putExtra("data",data)
             intent.putExtra("time",time)
         }
     }
     else{MP.pause()}
 }

    fun miniHeardControl(){
     val heardTrack = myHeard.text.toString().toInt()
     if (isPlay) {
         if (heardTrack > 60) {
             loonyPlay(positionBar)
         }
         else{
             MP.pause()
         }
     }
     else{MP.pause()}
    }



    @SuppressLint("HandlerLeak")
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
           val currentPosition =msg.what

            // Update position
            positionBar.progress = currentPosition

            // Update lables
            val elapsedTime = createTimeLable(currentPosition)
            elapsedTimeLable.text = elapsedTime

            val remainingTime = createTimeLable(total_time - currentPosition)
            remainingTimeLable.text = "-$remainingTime"

        }
    }

    fun createTimeLable (time :Int) :String{
        var timelable = ""
        val min = time / 1000 / 60
        val sec  = time / 1000 % 60

        timelable = "$min:"
        if(sec < 10) timelable += "0"
        timelable +=  sec

        return timelable
    }

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
            myHeard!!.text = message
        }
    }

    internal inner class NewThread
        (var path: String, var message: String) : Thread() {
        override fun run() {

            val wearableList: Task<List<Node>> = Wearable.getNodeClient(applicationContext).getConnectedNodes()
            try {
                val nodes: List<Node> = Tasks.await(wearableList)
                for (node in nodes) {
                    val sendMessageTask: Task<Int> =  //Send the message//
                        Wearable.getMessageClient(this@Player).sendMessage(node.getId(),
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
