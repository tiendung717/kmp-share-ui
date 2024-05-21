package shared.di

import com.chipmango.kmp.core.di.dataStoreModule
import org.koin.core.context.startKoin
import shared.di.module.appModule
import shared.di.module.themeModule
import shared.di.module.viewModelModule

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                appModule,
                viewModelModule,
                themeModule,
                dataStoreModule
            )
        }
    }
}