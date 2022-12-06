package com.example.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{
            speak()
        }

    }

    private fun speak(){
        var message: String = findViewById<EditText>(R.id.etMessage).text.toString()
        if(message.isEmpty()){
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_introduzca_texto)
            message = getString(R.string.tts_simpaticon)
        }
        Log.i("message text field", message)
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")

    }

    override fun onInit(status: Int) {
       if(status == TextToSpeech.SUCCESS){
           findViewById<TextView>(R.id.tvStatus).text = getString(R.string.sst_active)
           tts!!.language = Locale("ES")
       }else{
           findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_no_active)
       }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}