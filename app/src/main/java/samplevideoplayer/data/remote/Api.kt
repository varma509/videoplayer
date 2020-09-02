package samplevideoplayer.data.remote

import retrofit2.Response
import retrofit2.http.GET
import samplevideoplayer.model.VideoModel

interface Api {

    @GET("bikashthapa01/myvideos-android-app/master/data.json")
    suspend fun getVideosList(): Response<VideoModel>

}