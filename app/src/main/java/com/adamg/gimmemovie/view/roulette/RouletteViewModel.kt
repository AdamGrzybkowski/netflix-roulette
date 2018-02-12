package com.adamg.gimmemovie.view.roulette

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.adamg.gimmemovie.repository.ShowRepository
import com.adamg.gimmemovie.repository.model.RouletteFilter
import com.adamg.gimmemovie.repository.model.Show
import com.adamg.gimmemovie.view.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class RouletteViewModel(private val showRepository: ShowRepository): BaseViewModel() {

    private val viewSate: MutableLiveData<Show> = MutableLiveData()

    fun getRandomShow(rouletteFilter: RouletteFilter) {
        showRepository.getRandomShow(rouletteFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { show ->
                            viewSate.postValue(show)
                        }
                )
    }

    fun getViewState(): LiveData<Show> = viewSate

}