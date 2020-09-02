package samplevideoplayer.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import samplevideoplayer.model.Video
import samplevideoplayer.samplevideoplayer.R

class Player : AppCompatActivity() {
    var spiiner: ProgressBar? = null
    var fullScreenOp: ImageView? = null
    var frameLayout: FrameLayout? = null
    var videoPlayer: VideoView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        spiiner = findViewById(R.id.progressBar)
        fullScreenOp = findViewById(R.id.fullScreenOp)
        frameLayout = findViewById(R.id.frameLayout)
        val i = intent
        val data = i.extras
        val v = data!!.getSerializable("videoData") as Video?
        supportActionBar!!.setTitle(v!!.title)
        val title = findViewById<TextView>(R.id.videoTitle)
        val desc = findViewById<TextView>(R.id.videoDesc)
        videoPlayer = findViewById(R.id.videoView)
        title.text = v.title
        desc.text = v.description
        val videoUrl = Uri.parse(v.sources?.get(0))
        videoPlayer?.setVideoURI(videoUrl)
        val mc = MediaController(this)
        videoPlayer?.setMediaController(mc)
        videoPlayer?.setOnPreparedListener(OnPreparedListener {
            videoPlayer?.start()
            spiiner?.setVisibility(View.GONE)
        })
        fullScreenOp?.setOnClickListener(View.OnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            supportActionBar!!.hide()
            fullScreenOp?.setVisibility(View.GONE)
            frameLayout?.setLayoutParams(ConstraintLayout.LayoutParams(WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)))
            videoPlayer?.setLayoutParams(FrameLayout.LayoutParams(WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)))
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        fullScreenOp!!.visibility = View.VISIBLE
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar!!.show()
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val heightValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 220f, resources.displayMetrics).toInt()
        frameLayout!!.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightValue))
        videoPlayer!!.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightValue))
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            super.onBackPressed()
        }
    }

    companion object {
        const val TAG = "TAG"
    }
}