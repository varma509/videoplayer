package samplevideoplayer.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import samplevideoplayer.model.Video
import samplevideoplayer.samplevideoplayer.R
import samplevideoplayer.samplevideoplayer.databinding.ActivityMainBinding
import samplevideoplayer.util.Resource

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),VideoAdapter.VideoItemListener {
    private val viewModel: VideoViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding
    var adapterl: VideoAdapter? = null
    var all_videos: ArrayList<Video>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        all_videos= ArrayList<Video>()
        setupRecyclerView()
        setupObservers()
        viewModel.getVideoList()

    }

    private fun setupObservers() {
        viewModel.videoModel.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if(it.data!=null){

                       for(category  in it.data.categories){
                            all_videos?.addAll(category.videos)
                        }
                        all_videos?.let { it1 -> adapterl?.setItems(it1) }
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {
        adapterl = VideoAdapter(this)
        binding.videoList.layoutManager = LinearLayoutManager(this)
        binding.videoList.adapter = adapterl
    }


    companion object {
        const val TAG = "TAG"
    }

    override fun onClickedVideo(video: Video) {
        val b = Bundle()
        b.putSerializable("videoData",video)
        val i = Intent(this, Player::class.java)
        i.putExtras(b)
        startActivity(i)
    }
}