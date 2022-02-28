package com.app.testassignment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.app.testassignment.adapter.BannerAdapter
import com.app.testassignment.adapter.ProductAdapter
import com.app.testassignment.databinding.ActivityMainBinding
import com.app.testassignment.model.BannerModel
import com.app.testassignment.model.ProductModel
import com.app.testassignment.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private var activityMainBinding: ActivityMainBinding? = null
    private var loadBar: ProgressBar? = null
    var mainViewModel: MainViewModel? = null
    private var mBannerAdapter: BannerAdapter? = null
    var index = 1
    private var mProductAdapter: ProductAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding?.bannerRecycle?.let {
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(it)
            it.setHasFixedSize(true)
            mBannerAdapter = BannerAdapter()
            it.adapter = mBannerAdapter
        }
        loadBar = activityMainBinding!!.loadBar
        activityMainBinding?.dataRecycle?.let {
            it.layoutManager = GridLayoutManager(
                this,
                2
            )
            it.setHasFixedSize(true)
            mProductAdapter = ProductAdapter()
            it.adapter = mProductAdapter
            it.addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(it.layoutManager as GridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    index += 1
                    println("index issss$index")
                    getAllProduct(index)
                }
            })
        }
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getAllBanner()
        getAllProduct(index)
    }

    private val run = Runnable {
        mBannerAdapter?.itemCount?.minus(1)?.let {
            val visiblePos =
                (activityMainBinding?.bannerRecycle?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            val moveToPos = if (visiblePos < it) {
                visiblePos + 1
            } else {
                0
            }
            activityMainBinding?.bannerRecycle?.scrollToPosition(moveToPos)

        }
        scroll()
    }

    private fun scroll() {
        Handler(Looper.getMainLooper()).postDelayed(run, 2500)
    }

    private fun getAllBanner() {
        mainViewModel!!.allBanner.observe(this,
            { mBannerModel ->
                mBannerAdapter?.setBannerList(mBannerModel as ArrayList<BannerModel>)
                loadBar?.visibility = View.GONE
                Handler(Looper.getMainLooper()).postDelayed(run, 2500)
            })
    }

    private fun getAllProduct(index: Int) {
        mainViewModel!!.allProduct(index).observe(this,
            { mProductModel ->
                mProductAdapter?.setProductList(mProductModel as ArrayList<ProductModel>)
                loadBar?.visibility = View.GONE
            })
    }
}