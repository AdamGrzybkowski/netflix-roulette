package com.adamg.gimmemovie.view.roulette

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.adamg.gimmemovie.R
import com.adamg.gimmemovie.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_roulette.*
import javax.inject.Inject

class RouletteActivity: BaseActivity() {

    @Inject
    lateinit var viewModelFactory: RouletteViewModelFactory
    private lateinit var viewModel: RouletteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(RouletteViewModel::class.java)
    }
}
