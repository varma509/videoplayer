package samplevideoplayer.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import samplevideoplayer.model.Video
import samplevideoplayer.samplevideoplayer.databinding.VideoViewBinding

class VideoAdapter(private val listener: VideoItemListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface VideoItemListener {
        fun onClickedVideo(video: Video)
    }

    private val items = ArrayList<Video>()

    fun setItems(items: ArrayList<Video>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: VideoViewBinding = VideoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(items[position])
}

class CharacterViewHolder(private val itemBinding: VideoViewBinding, private val listener: VideoAdapter.VideoItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var video: Video

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Video) {
        this.video = item
        itemBinding.videoTitle.text = item.title
        Glide.with(itemBinding.root)
            .load(item.thumb)
            .into(itemBinding.videoThumbnail)
    }

    override fun onClick(v: View?) {
        listener.onClickedVideo(video)
    }
}

