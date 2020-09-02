package samplevideoplayer.repository


import samplevideoplayer.data.remote.Api
import samplevideoplayer.data.remote.BaseDataSource
import javax.inject.Inject

class VideoRepository @Inject constructor(
        private val service: Api
): BaseDataSource() {

    //apiCall
    suspend fun videoList() = getResult {service.getVideosList()}

}