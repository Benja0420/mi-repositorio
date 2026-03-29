package com.example.actividad3m4

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlayPause = findViewById<Button>(R.id.btnPlayPause)
        val seekBarVolume = findViewById<SeekBar>(R.id.seekBarVolume)

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)?.apply {
            isLooping = true
            setVolume(0.5f, 0.5f) // Iniciamos al 50% de volumen
            start()
        }

        btnPlayPause.setOnClickListener {
            mediaPlayer?.let { mp ->
                if (mp.isPlaying) {
                    mp.pause()
                    btnPlayPause.text = "Reproducir"
                } else {
                    mp.start()
                    btnPlayPause.text = "Pausar"
                }
            }
        }

        seekBarVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volumeLevel = progress / 100f
                mediaPlayer?.setVolume(volumeLevel, volumeLevel)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.let {
            it.stop()
            it.release()
        }
        mediaPlayer = null
    }
}