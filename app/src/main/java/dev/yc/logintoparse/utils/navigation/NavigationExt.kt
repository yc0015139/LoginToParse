package dev.yc.logintoparse.utils.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Safe way to navigate to target direction that avoid IllegalArgumentException with duplicate click
 *
 * @param directions The NavDirection from action on navigate xml file
 */
fun NavController.navigateSafe(directions: NavDirections) {
    currentDestination?.let {
        val action = it.getAction(directions.actionId) ?: return
        if (it.id != action.destinationId) {
            navigate(directions)
        }
    }
}