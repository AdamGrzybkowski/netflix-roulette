package com.adamg.netflixroulette.view.roulette

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ScrollView
import com.adamg.netflixroulette.R
import com.adamg.netflixroulette.repository.model.RouletteFilter
import com.adamg.netflixroulette.repository.model.Show
import com.adamg.netflixroulette.repository.model.ShowKind
import com.adamg.netflixroulette.util.extensions.hide
import com.adamg.netflixroulette.util.extensions.hideGroup
import com.adamg.netflixroulette.util.extensions.show
import com.adamg.netflixroulette.util.extensions.showGroup
import com.adamg.netflixroulette.view.base.BaseActivity
import com.adamg.netflixroulette.view.base.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_roulette.*
import kotlinx.android.synthetic.main.roulette_content.*
import kotlinx.android.synthetic.main.roulette_result_card.*
import kotlinx.android.synthetic.main.roulette_selector_card.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.design.snackbar
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
        setupListeners()

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(RouletteViewModel::class.java)

        viewModel.getViewState().observe(this, Observer { viewState ->
            when (viewState) {
                is Result.Loading -> handleLoading()
                is Result.Failure -> handleError(viewState.message)
                is Result.Success -> handleSuccess(viewState.data)
            }
        })

    }

    private fun setupListeners() {
        buttonSpinRoulette.setOnClickListener { viewModel.getRandomShow(getRouletteFilter()) }
    }

    private fun handleLoading() {
        progressBarResult.show()
        buttonSpinRoulette.text = getString(R.string.roulette_button_spinning)
        textViewInitResult.hide()
        buttonWatch.hide()
        groupResult.hideGroup()
    }

    private fun handleError(errorMessage: String?) {
        progressBarResult.hide()
        buttonSpinRoulette.text = getString(R.string.roulette_button_spin_again)
        textViewInitResult.text = getString(R.string.roulette_init_result_text)
        snackbar(constraintLayoutRouletteContainer, errorMessage ?: getString(R.string.unknown_error))
    }

    private fun handleSuccess(show: Show) {
        progressBarResult.hide()
        buttonSpinRoulette.text = getString(R.string.roulette_button_spin_again)
        displayShow(show)
        scrollView.fullScroll(ScrollView.FOCUS_DOWN)
    }

    private fun getRouletteFilter(): RouletteFilter {
        return RouletteFilter(
                minimumScore = getMinimumScore(),
                showKind = getShowKind()
        )
    }

    private fun getShowKind(): ShowKind {
        return if (checkBoxMovies.isChecked && !checkBoxTvShows.isChecked) {
            ShowKind.MOVIE
        } else if (!checkBoxMovies.isChecked && checkBoxTvShows.isChecked) {
            ShowKind.TV_SHOW
        } else {
            ShowKind.ANY_SHOW
        }
    }

    private fun getMinimumScore(): Int {
        val score = spinnerImdbRating.getItems<String>()[spinnerImdbRating.selectedIndex]
        val replace = score.replace(">", "")
        return if (replace.toIntOrNull() != null) {
            replace.toInt()
        } else {
            0
        }
    }

    private fun displayShow(show: Show) {
        Picasso.with(this).load(show.posterUrl).into(imageViewPoster)
        textViewDescription.text = show.overview
        textViewImdbRating.text = getString(R.string.roulette_result_imdb_rating, show.imdbRating)
        textViewTitle.text = show.title
        textViewReleaseYear.text = show.releaseYear.toString()
        groupResult.showGroup()
        buttonWatch.show()

        if (show.seasonCount != null) {
            textViewSeasonsCount.text = getString(R.string.roulette_result_seasons, show.seasonCount)
            textViewSeasonsCount.show()
        } else {
            textViewSeasonsCount.text = ""
            textViewSeasonsCount.hide()
        }
        if (show.netflixUrl != null) {
            buttonWatch.setOnClickListener {
                browse(show.netflixUrl)
            }
        } else {
            buttonWatch.hide()
        }
    }

    private fun setupImdbRatingSpinner() {
        spinnerImdbRating.setItems(resources.getStringArray(R.array.imdb_ratings).toList())
        spinnerImdbRating.setBackgroundResource(R.drawable.spinner_background)
        spinnerImdbRating.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        spinnerImdbRating.setPadding(dip(12), dip(4), dip(12), dip(4))
    }
}
