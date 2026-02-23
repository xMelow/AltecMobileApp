package com.example.altecprint

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.altecprint.data.DataSource
import com.example.altecprint.ui.screens.BasProgramScreen
import com.example.altecprint.ui.screens.LabelScreen
import com.example.altecprint.ui.screens.PrintLabelScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings

enum class AltecScreen() {
    Labels,
    PrintLabel,
    BasProgram,
    Settings
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    SETTINGS("Settings", Icons.Default.Settings),
    LABEL("label", Icons.Default.Home),
    BAS("BasProgram", Icons.Default.MailOutline),
}

@Composable
fun AltecApp(
    viewModel: LabelViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AltecScreen.valueOf(
        backStackEntry?.destination?.route ?: AltecScreen.Labels.name
    )
    val currentDestination = when (currentScreen) {
        AltecScreen.Labels, AltecScreen.PrintLabel -> AppDestinations.LABEL
        AltecScreen.BasProgram -> AppDestinations.BAS
        AltecScreen.Settings -> AppDestinations.SETTINGS
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = { Icon(it.icon, contentDescription = it.label) },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = {
                        when (it) {
                            AppDestinations.LABEL -> navController.navigate(AltecScreen.Labels.name)
                            AppDestinations.BAS -> navController.navigate(AltecScreen.BasProgram.name)
                            AppDestinations.SETTINGS -> navController.navigate(AltecScreen.Settings.name)
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                AltecAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = AltecScreen.Labels.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = AltecScreen.Labels.name) {
                    LabelScreen(
                        labels = DataSource.labels,
                        onLabelClick = {
                            viewModel.setLabel(it)
                            navController.navigate(AltecScreen.PrintLabel.name)
                        }
                    )
                }
                composable(route = AltecScreen.PrintLabel.name) {
                    PrintLabelScreen(
                        amount = uiState.labelAmount,
                        variableData = uiState.variableData,
                        onLabelAmountChange = { viewModel.updateLabelAmount(it) },
                        onPrintButtonClicked = { viewModel.printLabel(uiState.selectedLabel, uiState.labelAmount) },
                        onVariableChange = { key, value -> viewModel.updateLabelVariable(key, value) }
                    )
                }
                composable(route = AltecScreen.BasProgram.name) {
                    BasProgramScreen(
                        onSendButtonClicked = { viewModel.sendBasData(it) }
                    )
                }
                composable(route = AltecScreen.Settings.name) {
                    Text("Settings coming soon")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AltecAppBar(
    currentScreen: AltecScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.name) }, // change to string resource
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}