package samplevideoplayer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
import samplevideoplayer.model.Video
import samplevideoplayer.samplevideoplayer.R

class VideoAdapterl(private val context: Context, private val allVideos: List<Video>) : RecyclerView.Adapter<VideoAdapterl.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.video_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = allVideos[position].title
      //  Picasso.get().load(allVideos[position].imageUrl).into(holder.videoImage)
        holder.vv.setOnClickListener { v ->
            val b = Bundle()
            b.putSerializable("videoData", allVideos[position])
            val i = Intent(context, Player::class.java)
            i.putExtras(b)
            v.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return allVideos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoImage: ImageView
        var title: TextView
        var vv: View

        init {
            videoImage = itemView.findViewById(R.id.videoThumbnail)
            title = itemView.findViewById(R.id.videoTitle)
            vv = itemView
        }
    }
}