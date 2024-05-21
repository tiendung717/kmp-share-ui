package com.chipmango.kmp.core.navigation.destination

import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import com.chipmango.kmp.core.navigation.deeplink.DeepLinkAction

class DestinationFactory(private val scheme: String, private val host: String) {

    private fun constructDestinationLink(path: String, argumentKeys: List<String>): String {
        val uriBuilder = StringBuilder()
        uriBuilder.append("$scheme://$host")
        uriBuilder.append(path)

        argumentKeys.forEachIndexed { index, key ->
            uriBuilder.append(if (index == 0) "?" else "&")
            uriBuilder.append("$key={$key}")
        }

        return uriBuilder.toString()
    }

    fun create(
        path: String,
        screenName: String,
        screenClass: String,
        vararg arguments: Pair<String, NavType<*>>
    ): Destination {
        val destinationUri = constructDestinationLink(
            path = path,
            argumentKeys = arguments.map { it.first }
        )

        return Destination(
            route = destinationUri,
            screenName = screenName,
            screenClass = screenClass,
            arguments = arguments.map { DestinationArgument(it.first, it.second) },
            deepLinks = listOf(
                NavDeepLink.Builder()
                    .setUriPattern(destinationUri)
                    .setAction(DeepLinkAction.DEEP_LINK_ACTION)
                    .build()
            )
        )
    }
}