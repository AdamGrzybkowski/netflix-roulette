package com.adamg.gimmemovie.dagger.modules

import android.app.Application
import android.content.Context
import com.adamg.gimmemovie.BuildConfig
import com.adamg.gimmemovie.dagger.scopes.PerApplication
import com.adamg.gimmemovie.remote.NetflixRouletteApi
import com.adamg.gimmemovie.repository.RouletteRepository
import com.adamg.gimmemovie.repository.RouletteRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    fun provideRouletteRepository(): RouletteRepository {
        return RouletteRepositoryImpl()
    }

    @Provides
    @PerApplication
    fun provideNetflixRouletteApi(retrofit: Retrofit): NetflixRouletteApi {
        return retrofit.create(NetflixRouletteApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @PerApplication
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    @Provides
    @PerApplication
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        moshiConverterFactory: MoshiConverterFactory,
                        rxJavaCallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(moshiConverterFactory)
                .client(okHttpClient)
                .build()
    }

}