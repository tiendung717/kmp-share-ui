package shared.di.module

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import shared.viewmodel.DummyViewModel

actual val viewModelModule = module {
    viewModelOf<DummyViewModel>(::DummyViewModel)
}