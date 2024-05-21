package shared.di.module

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import shared.viewmodel.DummyViewModel

actual val viewModelModule = module {
    singleOf<DummyViewModel>(::DummyViewModel)
}