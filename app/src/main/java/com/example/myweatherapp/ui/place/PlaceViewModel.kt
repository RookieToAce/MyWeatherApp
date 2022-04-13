package com.example.myweatherapp.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.logic.Repository
import com.example.myweatherapp.logic.model.Place

class PlaceViewModel : ViewModel() {

    //定义搜索地址时输入的文字为一个liveData对象，监听当这个对象发生变化时，从仓库层中去查找，
    // 如果没有该地址数据，则发送网络请求进行数据请求。如果有，则从本地记录中去搜寻。
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    //只将query传入searchLiveData的value，因为searchLiveData是一个livedata，
    // 当他的内容发生变化时，会监听，然后做出反应
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

}