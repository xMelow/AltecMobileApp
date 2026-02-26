package com.example.altecprint

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.text.style.TextAlign
import com.example.altecprint.ui.screens.AddLabelScreen
import com.example.altecprint.ui.screens.EditLabelScreen
import com.example.altecprint.ui.screens.PrinterConnectScreen
import com.example.altecprint.ui.screens.PrinterSettingsScreen
import com.example.altecprint.ui.viewmodel.LabelViewModel

enum class AltecScreen() {
    Labels,
    PrintLabel,
    EditLabel,
    AddLabel,
    BasProgram,
    Printer,
    PrinterSettings,
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    PRINTER("Printer", Icons.Default.Settings),
    LABEL("label", Icons.Default.Home),
    BAS("Bas Program", Icons.Default.MailOutline),
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
        AltecScreen.Printer -> AppDestinations.PRINTER
        else -> AppDestinations.LABEL
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
                            AppDestinations.PRINTER -> navController.navigate(AltecScreen.PrinterSettings.name)
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
                        labels = uiState.labels,
                        onAddButtonClick = { navController.navigate(AltecScreen.AddLabel.name) },
                        onLabelClick = {
                            viewModel.setLabel(it)
                            navController.navigate(AltecScreen.PrintLabel.name)
                        }
                    )
                }
                composable(route = AltecScreen.PrintLabel.name) {
                    println(uiState.variableData)
                    PrintLabelScreen(
                        amount = uiState.labelAmount,
                        variableData = uiState.variableData,
                        onLabelAmountChange = { viewModel.updateLabelAmount(it) },
                        onPrintButtonClicked = { viewModel.printLabel(uiState.selectedLabel, uiState.labelAmount) },
                        onVariableChange = { key, value -> viewModel.updateLabelVariable(key, value) },
                        onEditButtonClicked = {
                            navController.navigate(AltecScreen.EditLabel.name)
                        }
                    )
                }
                composable(route = AltecScreen.BasProgram.name) {
                    BasProgramScreen(
                        onSendButtonClicked = { viewModel.sendBasData(it) },
                        onExitButtonClicked = {
                            viewModel.exitBasProgram()
                            navController.navigate(AltecScreen.Labels.name)
                        }
                    )
                }
                composable(route = AltecScreen.Printer.name) {
                    PrinterConnectScreen(
                        printerPort = uiState.printerPort,
                        printerIpOrHost = uiState.printerIpOrHostname,
                        onConnectClick = { ipOrHost, port -> viewModel.connectToPrinter(ipOrHost, port)},
                    )
                }
                composable(route = AltecScreen.EditLabel.name) {
                    EditLabelScreen(
                        label = uiState.selectedLabel,
                        onSaveButtonClick = {
                            viewModel.saveLabelTspl(it)
                            navController.navigate(AltecScreen.PrintLabel.name)
                        },
                    )
                }
                composable(route = AltecScreen.AddLabel.name) {
                    AddLabelScreen(
                        onAddButtonClicked = { name, tspl -> viewModel.addLabel(name, tspl) },
                        onCancelButtonClicked = { navController.navigate(AltecScreen.Labels.name) }
                    )
                }
                composable(route = AltecScreen.PrinterSettings.name) {
                    println(uiState.printerSettings)
                    PrinterSettingsScreen(
                        printerSettings = uiState.printerSettings,
                        onSettingChanged = { },
                        onConnectButtonClicked = { },
                        onSaveButtonClicked = { },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AltecAppBar(
    currentScreen: AltecScreen,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = currentScreen.name, // change to string resource
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            ) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier,
    )
}