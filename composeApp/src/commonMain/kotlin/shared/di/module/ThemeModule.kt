package shared.di.module

import com.chipmango.kmp.core.theme.colors.ThemeColor
import org.koin.dsl.module
import shared.theme.darkColors
import shared.theme.lightColors

val themeModule = module {
    single {
        ThemeColor(
            light = lightColors,
            dark = darkColors
        )
    }
}