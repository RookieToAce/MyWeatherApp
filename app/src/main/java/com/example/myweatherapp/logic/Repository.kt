package com.example.myweatherapp.logic

import androidx.lifecycle.liveData
import com.example.myweatherapp.logic.model.Place
import com.example.myweatherapp.logic.network.MyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    /**
     * 为了能将异步获取的数据以响应式编程的方式传递给上一层，通常会返回一个LiveData对象
     * 它可以自动构建并返回一个LiveData对象，然后在它的代码块中提供一个挂起函数的上下文
     * 这样我们就可以在liveData()函数的代码块中调用任意的挂起函数了
     */
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = MyWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    //封装
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>> {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}