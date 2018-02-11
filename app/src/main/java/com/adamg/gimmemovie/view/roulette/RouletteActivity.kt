package com.adamg.gimmemovie.view.roulette

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.widget.ScrollView
import com.adamg.gimmemovie.R
import com.adamg.gimmemovie.util.extensions.hide
import com.adamg.gimmemovie.util.extensions.hideGroup
import com.adamg.gimmemovie.util.extensions.show
import com.adamg.gimmemovie.util.extensions.showGroup
import com.adamg.gimmemovie.view.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_roulette.*
import kotlinx.android.synthetic.main.roulette_content.*
import kotlinx.android.synthetic.main.roulette_result_card.*
import kotlinx.android.synthetic.main.roulette_selector_card.*
import org.jetbrains.anko.dip
import javax.inject.Inject


class RouletteActivity: BaseActivity() {

    @Inject
    lateinit var viewModelFactory: RouletteViewModelFactory
    private lateinit var viewModel: RouletteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)
        setSupportActionBar(toolbar)
        setupImdbRatingSpinner()

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(RouletteViewModel::class.java)

        //mocked loading content behavior
        buttonSpinRoulette.setOnClickListener {
            progressBarResult.show()
            textViewInitResult.hide()
            groupResult.hideGroup()
            Handler().postDelayed({
                progressBarResult.hide()
                groupResult.showGroup()
                scrollView.fullScroll(ScrollView.FOCUS_DOWN)
            }, 2000)
        }

        //temporary to see the UI
        Picasso.with(this)
                .load("https://img.reelgood.com/content/movie/ee6a5582-6f7a-4c21-a907-6df3c1713329/poster-780.jpg")
                .into(imageViewPoster)
    }

    private fun setupImdbRatingSpinner() {
        spinnerImdbRating.setItems(resources.getStringArray(R.array.imdb_ratings).toList())
        spinnerImdbRating.setBackgroundResource(R.drawable.spinner_background)
        spinnerImdbRating.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        spinnerImdbRating.setPadding(dip(12), dip(4), dip(12), dip(4))
    }
}
