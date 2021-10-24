package com.fachri.biwtaqwa.menus.videokajian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fachri.biwtaqwa.R
import com.fachri.biwtaqwa.databinding.ActivityDetailVideoKajianBinding
import com.fachri.biwtaqwa.menus.videokajian.model.VideoKajianModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailVideoKajianActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIDEO_KAJIAN = "extra_video_kajian"
    }

    // Fitur binding adalah fitur yang memudahkan dalam pemanggilan id dalam layout
    private lateinit var binding: ActivityDetailVideoKajianBinding // cara pertama

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVideoKajianBinding.inflate(layoutInflater) // cara kedua
        val view = binding.root // cara ketiga
        setContentView(view)

        // intent adalah fitur untuk berpindah dari activity ke activity lain
        val video = intent.getParcelableExtra<VideoKajianModel>(EXTRA_VIDEO_KAJIAN) as VideoKajianModel

        initView(video)
    }

    private fun initView(video: VideoKajianModel) {
        val youtubePlayerView: YouTubePlayerView =
            // findViewById berfungsi memanggil id tanpa fitur binding
            findViewById(R.id.player_video)
        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object :
        AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(video.urlVideo, 0f)
            }
        })

        binding.tvChannel.text = video.channel
        binding.tvTitle.text = video.title
        binding.tvSpeaker.text = video.speaker
        binding.tvSummary.text = video.summary

        binding.ivShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Kajian dengan judul \"${video.title}\" dibawakan oleh \"${video.speaker}\" dan dari channel \"${video.channel}\" \n\n\n Link Video : https://www.youtube.com/watch?v=${video.urlVideo}"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }
}