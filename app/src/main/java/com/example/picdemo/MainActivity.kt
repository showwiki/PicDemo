package com.example.picdemo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picdemo.adapter.MainActivityAdapter
import com.example.picdemo.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import utils.VerticalDividerItemDecoration
import utils.getViewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainActivityViewModel by lazy { getViewModel(this, MainActivityViewModel::class.java) }

    private var adapter = MainActivityAdapter()

    private lateinit var ct : MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ct = this
        initRecycleView()
        subscribeData()
        initData()
        initListener()

        val job = launch {
            println("test")
        }

    }

    private fun initListener() {
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val keyWord = s.toString().trim()
                viewModel.setSearchKey(keyWord)
            }
        })
    }

    private fun initRecycleView() {

        val gridLayoutManager = GridLayoutManager(ct, 3)

        picRcv.layoutManager = gridLayoutManager
        picRcv.adapter = adapter
        adapter.setNewData(viewModel.resultList)

        adapter.setOnLoadMoreListener({
            viewModel.loadMoreData()
        }, picRcv)
        picRcv.addItemDecoration(VerticalDividerItemDecoration(ct, 8))


    }

    private fun initData() {
        viewModel.getData()
    }

    private fun subscribeData() {
        //加载更多完成
        viewModel.isLoadMoreComplete.observe(ct, Observer {
            adapter.loadMoreComplete()
            adapter.setEnableLoadMore(true)
        })

        //加载更多结束
        viewModel.isLoadMoreEnd.observe(ct, Observer {
            adapter.setEnableLoadMore(true)
            if (it) {
                adapter.loadMoreEnd()
            }
        })

        viewModel.restResultLiveData.observe(ct, Observer {
            adapter.notifyDataSetChanged()
        })
    }

}