package samplevideoplayer.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Video: Serializable {
    @SerializedName("description")
    var description: String?=null

    @SerializedName("sources")
    var sources: List<String>?=null

    @SerializedName("subtitle")
    var subtitle: String?=null

    @SerializedName("thumb")
    var thumb: String?=null

    @SerializedName("title")
    var title: String?=null
}