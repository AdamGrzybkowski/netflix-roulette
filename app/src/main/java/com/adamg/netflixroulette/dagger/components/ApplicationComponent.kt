package com.adamg.netflixroulette.dagger.components

import android.app.Application
import com.adamg.netflixroulette.GimmeMovieApplication
import com.adamg.netflixroulette.dagger.modules.ActivityBindingModule
import com.adamg.netflixroulette.dagger.modules.ApplicationModule
import com.adamg.netflixroulette.dagger.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (ActivityBindingModule::class),
    (ApplicationModule::class)
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: GimmeMovieApplication)
}