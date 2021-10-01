package ru.alexgladkov.common.compose.screens.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.alexgladkov.common.compose.NavigationTree
import ru.alexgladkov.common.compose.screens.chain.ChainScreen
import ru.alexgladkov.odyssey.compose.RootHost
import ru.alexgladkov.odyssey.compose.extensions.destination
import ru.alexgladkov.odyssey.core.RootController

@Composable
fun MainFlow(parentRootController: RootController) {
    RootHost(startScreen = NavigationTree.Container.Chain, rootController = parentRootController) {
        destination(screen = NavigationTree.Container.Chain) {
            ChainScreen(
                number = (params as? Int) ?: 0, flowName = "Main Flow",
                rootController = rootController
            )
        }
    }
}