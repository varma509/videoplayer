package samplevideoplayer.model


import com.google.gson.annotations.SerializedName

data class VideoModel(
    @SerializedName("categories")
    var categories: List<Category>
)