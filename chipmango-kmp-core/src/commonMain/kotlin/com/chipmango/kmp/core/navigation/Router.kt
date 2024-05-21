package com.chipmango.kmp.core.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.chipmango.kmp.core.navigation.destination.Destination

internal fun Destination.withArguments(arguments: Map<String, Any?> = emptyMap()): String {
    val uriBuilder = StringBuilder()
    uriBuilder.append(route)

    arguments.forEach { (key, value) ->
        uriBuilder.append(if (uriBuilder.contains("?")) "&" else "?")
        uriBuilder.append("$key=$value")
    }

    return uriBuilder.toString()
}

fun NavController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    arguments: Map<String, Any?> = emptyMap(),
) {
    val destinationArgs = mutableMapOf<String, Any?>()
    arguments.forEach { (key, value) ->
        destinationArgs[key] = value
    }

    navigate(
        route = destination.withArguments(destinationArgs),
        navOptions = navOptions,
        navigatorExtras = navigatorExtras,
    )
}

fun NavBackStackEntry.getRequiredLong(key: String): Long {
    val value = arguments?.getLong(key)
    return value ?: throw IllegalArgumentException("Parameter $key not found")
}

fun NavBackStackEntry.getRequiredInt(key: String): Int {
    val value = arguments?.getInt(key)
    return value ?: throw IllegalArgumentException("Parameter $key not found")
}

fun NavBackStackEntry.getRequiredString(key: String): String {
    val value = arguments?.getString(key)
    return value ?: throw IllegalArgumentException("Parameter $key not found")
}

fun NavBackStackEntry.getRequiredBoolean(key: String): Boolean {
    val value = arguments?.getBoolean(key)
    return value ?: throw IllegalArgumentException("Parameter $key not found")
}

fun NavBackStackEntry.getRequiredFloat(key: String): Float {
    val value = arguments?.getFloat(key)
    return value ?: throw IllegalArgumentException("Parameter $key not found")
}

fun NavBackStackEntry.getRequiredDouble(key: String): Double {
    val value = arguments?.getDouble(key)
    return value ?: throw IllegalArgumentException("Parameter $key not found")
}