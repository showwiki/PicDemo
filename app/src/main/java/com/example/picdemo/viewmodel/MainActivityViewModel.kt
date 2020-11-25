package com.example.picdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.picdemo.bean.PicListItem
import com.example.picdemo.bean.RestResult
import com.example.picdemo.network.RestAdapterFactory
import com.example.picdemo.network.RetroFitFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import utils.awaitEx
import utils.isNotNullOrEmpty

class MainActivityViewModel : BaseViewModel() {

    var restResultLiveData: MutableLiveData<MutableList<PicListItem>> = MutableLiveData(mutableListOf())

    var resultList: MutableList<PicListItem> = mutableListOf()


    var keywordLiveData: MutableLiveData<String> = MutableLiveData()

    var isLoading  : MutableLiveData<Boolean> = MutableLiveData() //是否加载中

    var isreFresh  : MutableLiveData<Boolean> = MutableLiveData() //是否下拉刷新

    var isLoadMoreEnd  : MutableLiveData<Boolean> = MutableLiveData() //加载更多结束

    var isLoadMoreComplete : MutableLiveData<Boolean> = MutableLiveData() //加载更多完成

    var pageNum: Int = 0
    private val perPageSize: Int = 30
    var keyWord: String = "北京"

    fun getData() {
//        val execute = RetroFitFactory.homeService.getPic2(keyWord, pageNum, perPageSize).execute()
//        Log.e("测试协程", execute.toString())


        launch {
//            val job = async { RestAdapterFactory.getHomeService().getPic(keyWord, pageNum, perPageSize) }
            val job = async { RetroFitFactory.homeService.getPic(keyWord, pageNum, perPageSize) }

            job.awaitEx()?.let {

                if (it.list.isNotNullOrEmpty()) {
                    when {
                        pageNum == 0 -> {
                            resultList.clear()
                            resultList.addAll(it.list)
                            restResultLiveData.postValue(resultList)
                        }
                        else -> {
                            resultList.addAll(it.list)
                            restResultLiveData.value = resultList
                            restResultLiveData.postValue(resultList)
                        }
                    }
                    isLoadMoreComplete.postValue(true)

                    if (it.list!!.size < perPageSize) {
                        isLoadMoreEnd.postValue(true)
                    }

                }

            }
        }
    }


    fun setSearchKey(key: String) {
        this.keyWord = key
        pageNum = 0
        getData()
    }


    /**
     * 上拉加载
     */
    fun loadMoreData() {
        isreFresh.value = false
        pageNum += 1
        getData()
    }

}