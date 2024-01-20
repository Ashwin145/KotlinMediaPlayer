package com.example.mediaplayers

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar


class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer : MediaPlayer
    var totalTime : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar?.hide()
        val btnPlayer = findViewById<ImageView>(R.id.play)
        val btnPause = findViewById<ImageView>(R.id.pause)
        val btnStop = findViewById<ImageView>(R.id.stop)
        val seekBarMusic = findViewById<SeekBar>(R.id.seekBar)

        mediaPlayer = MediaPlayer.create(this,R.raw.music)
        mediaPlayer = MediaPlayer.create(this,R.raw.lover)



        mediaPlayer.setVolume(1f,1f)
        totalTime = mediaPlayer.duration



        btnPlayer.setOnClickListener{
            mediaPlayer.start()
        }
        btnPause.setOnClickListener{
            mediaPlayer.start()
        }
        btnStop.setOnClickListener{
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()


        }

        //when user changes the time stamp of music,reflect that change
        seekBarMusic.max = totalTime
        seekBarMusic.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int,fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })
        // change the seekbar position based on the music
        val handler = Handler()
        handler.postDelayed(object:Runnable{
            override fun run(){
                try{
                    seekBarMusic.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this,1000)

                }catch (exception:java.lang.Exception){
                    seekBarMusic.progress = 0
                }
            }

        },0)

    }

}