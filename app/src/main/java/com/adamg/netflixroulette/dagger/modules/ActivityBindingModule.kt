package com.adamg.netflixroulette.dagger.modules

import com.adamg.netflixroulette.dagger.scopes.PerActivity
import com.adamg.netflixroulette.view.roulette.RouletteActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(RouletteActivityModule::class)])
    abstract fun bindMainActivity(): RouletteActivity

}