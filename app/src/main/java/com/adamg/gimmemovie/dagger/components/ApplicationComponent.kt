package com.adamg.gimmemovie.dagger.components

import android.app.Application
import com.adamg.gimmemovie.GimmeMovieApplication
import com.adamg.gimmemovie.dagger.modules.ActivityBindingModule
import com.adamg.gimmemovie.dagger.modules.ApplicationModule
import com.adamg.gimmemovie.dagger.scopes.PerApplication
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