package com.adamg.netflixroulette.view.roulette

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.adamg.netflixroulette.repository.ShowRepository
import com.adamg.netflixroulette.repository.model.RouletteFilter
import com.adamg.netflixroulette.repository.model.Show
import com.adamg.netflixroulette.view.base.BaseViewModel
import com.adamg.netflixroulette.view.base.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class RouletteViewModel(private val showRepository: ShowRepository): BaseViewModel() {

    private val viewSate: MutableLiveData<Result<Show>> = MutableLiveData()

    fun getRandomShow(rouletteFilter: RouletteFilter) {
        showRepository.getRandomShow(rouletteFilter)
                .toObservable()
                .map { Result.success(it) }
                .onErrorReturn { Result.failure(it.message) }
                .startWith(Result.loading())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { viewSate.postValue(it) }
                )
    }

    fun getViewState(): LiveData<Result<Show>> = viewSate

}