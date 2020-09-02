package samplevideoplayer.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import samplevideoplayer.model.VideoModel
import samplevideoplayer.repository.VideoRepository
import samplevideoplayer.util.Resource

class VideoViewModel  @ViewModelInject constructor(val repo: VideoRepository):ViewModel() {
    var videoModel = MutableLiveData<Resource<VideoModel>>()
    fun getVideoList() {
        viewModelScope.launch {
            val resp = repo.videoList()
            videoModel.postValue(resp)
        }
    }


}