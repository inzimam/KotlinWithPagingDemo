package com.inzy.kotlindemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable

class FeedListViewModel : ViewModel() {

    private val networkService = NetworkCall.getService()
    var feedList: LiveData<PagedList<Feed>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val feedDataSourceFactory: FeedDataSourceFactory

    init {
        feedDataSourceFactory = FeedDataSourceFactory(compositeDisposable, networkService)
        val config =
            PagedList.Config.Builder().setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .build()
        feedList = LivePagedListBuilder<Int, Feed>(feedDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<FeedDataSource,
            State>(feedDataSourceFactory.feedDataSourceLiveData, FeedDataSource::state)

    fun retry() {
        feedDataSourceFactory.feedDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return feedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}