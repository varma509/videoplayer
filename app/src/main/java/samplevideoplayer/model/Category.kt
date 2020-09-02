package samplevideoplayer.model


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name")
    var name: String,
    @SerializedName("videos")
    var videos: List<Video>
)