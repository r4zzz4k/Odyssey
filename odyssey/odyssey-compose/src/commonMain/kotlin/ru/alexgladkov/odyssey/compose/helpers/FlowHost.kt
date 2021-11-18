package ru.alexgladkov.odyssey.compose.helpers

import androidx.compose.runtime.*
import ru.alexgladkov.odyssey.compose.extensions.launchAsState
import ru.alexgladkov.odyssey.compose.extensions.observeAsState
import ru.alexgladkov.odyssey.core.NavigationEntry
import ru.alexgladkov.odyssey.core.controllers.FlowRootController
import ru.alexgladkov.odyssey.core.destination.DestinationScreen

/**
 * Flow host
 * Use this for switch between screens inside flow root controller
 *
 * @param screenBundle - params, rootcontroller, etc
 */
@Composable
fun FlowHost(screenBundle: ScreenBundle) {
    val flowRootController = screenBundle.rootController as FlowRootController
    val navigation = flowRootController.backStackObserver.observeAsState(flowRootController.backStack.last())

    val params = (navigation.value?.destination as? DestinationScreen)?.params
    navigation.value?.let { entry ->
        val render = screenBundle.screenMap[entry.destination.destinationName()]
        render?.invoke(
            screenBundle.copy(params = if (flowRootController.backStack.size == 1) screenBundle.params else params)
        )
    }
}

/**
 * Tab host
 * Use this for switch between screens inside tab for multistack root controller
 *
 * @param navigationEntry - out navigation describes tab info (rc, params, etc)
 * @param screenBundle - params, rootcontroller, etc
 */
@Composable
fun TabHost(navigationEntry: NavigationEntry?, screenBundle: ScreenBundle) {
    val flowRootController = screenBundle.rootController as FlowRootController
    val navigation = flowRootController.backStackObserver
        .launchAsState(key = navigationEntry, initial = flowRootController.backStack.last())

    val params = (navigation?.destination as? DestinationScreen)?.params
    navigation?.let { entry ->
        val render = screenBundle.screenMap[entry.destination.destinationName()]
        render?.invoke(
            screenBundle.copy(params = if (flowRootController.backStack.size == 1) screenBundle.params else params)
        )
    }
}