package com.adamg.netflixroulette.dagger.modules

import android.app.Application
import android.content.Context
import com.adamg.netflixroulette.BuildConfig
import com.adamg.netflixroulette.dagger.scopes.PerApplication
import com.adamg.netflixroulette.remote.NetflixRouletteApi
import com.adamg.netflixroulette.remote.ShowRemoteDataSource
import com.adamg.netflixroulette.remote.ShowRemoteDataSourceImpl
import com.adamg.netflixroulette.remote.mappers.ShowMapper
import com.adamg.netflixroulette.repository.ShowRepository
import com.adamg.netflixroulette.repository.ShowRepositoryImpl
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
    fun provideShowRemoteDataSource(netflixRouletteApi: NetflixRouletteApi, showMapper: ShowMapper): ShowRemoteDataSource {
        return ShowRemoteDataSourceImpl(netflixRouletteApi, showMapper)
    }

    @Provides
    @PerApplication
    fun provideShowRepository(showRemoteDataSource: ShowRemoteDataSource): ShowRepository {
        return ShowRepositoryImpl(showRemoteDataSource)
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