package com.kunal.stockscanner.di.modules

import com.kunal.stockscanner.data.scans.RemoteDataSource
import com.kunal.stockscanner.data.scans.RemoteDataSourceImpl
import com.kunal.stockscanner.di.components.ViewComponent
import dagger.Module
import dagger.Provides

/**
 * Created by kunal on 2019-07-26.
 */
@Module(subcomponents = [ViewComponent::class])
class DataModule {

    @Provides
    fun getRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl):RemoteDataSource = remoteDataSourceImpl
}