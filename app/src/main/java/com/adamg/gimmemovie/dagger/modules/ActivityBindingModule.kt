package com.adamg.gimmemovie.dagger.modules

import com.adamg.gimmemovie.dagger.scopes.PerActivity
import com.adamg.gimmemovie.view.roulette.RouletteActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(RouletteActivityModule::class)])
    abstract fun bindMainActivity(): RouletteActivity

}