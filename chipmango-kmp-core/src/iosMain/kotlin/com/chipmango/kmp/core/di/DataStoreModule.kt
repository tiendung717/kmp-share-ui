package com.chipmango.kmp.core.di

import com.chipmango.kmp.core.datastore.DataStoreFactory
import com.chipmango.kmp.core.datastore.DataStorePreference
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val dataStoreModule = module {
    single { DataStoreFactory() }
    singleOf(::DataStorePreference)
}