package com.inzy.kotlindemo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable

class FeedDataSourceFactory(
    private val compositeDisposable: CompositeDisposable, private val networkCall: NetworkCall
) : DataSource.Factory<Int, Feed>() {

    val feedDataSourceLiveData = MutableLiveData<FeedDataSource>()


    override fun create(): DataSource<Int, Feed> {
        val feedDataSource = FeedDataSource(networkCall, compositeDisposable);
        feedDataSourceLiveData.postValue(feedDataSource)
        return feedDataSource
    }
}