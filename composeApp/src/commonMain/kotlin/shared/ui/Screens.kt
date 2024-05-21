package shared.ui

import androidx.navigation.NavType
import com.chipmango.kmp.core.navigation.destination.DestinationFactory

object QueryParams {
    const val AccountId = "accountId"
}
object Screens {
    private val factory by lazy {
        DestinationFactory("app", "solid")
    }

    val Home = factory.create(
        path = "home",
        screenName = "Screen Home",
        screenClass = "ScreenHome"
    )

    val Settings = factory.create(
        path = "settings",
        screenName = "Screen Settings",
        screenClass = "ScreenSettings",
        arguments = arrayOf(QueryParams.AccountId to NavType.LongType)
    )

}