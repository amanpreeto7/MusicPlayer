package com.o7services.musicplayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author: 017
 * @Date: 09/09/23
 * @Time: 5:19 pm
 */
class MusicViewModel  : ViewModel(){
    var musicContentList : MutableLiveData<ArrayList<MusicContent>> = MutableLiveData(arrayListOf<MusicContent>())
}