package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.edit.EditHuacalesScreen
import edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.edit.EditHuacalesUiEvent
import edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.edit.EditHuacalesViewModel
import edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list.ListHuacalesScreen
import edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list.ListHuacalesUiEvent
import edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list.ListHuacalesViewModel
import edu.ucne.esthibervalentin_ap2_p1.ui.theme.EsthiberValentinAP2_P1Theme

@Composable
fun HuacalesScreen(
    onDrawer: () -> Unit = {},
    editViewModel: EditHuacalesViewModel = hiltViewModel(),
    listViewModel: ListHuacalesViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {
    HuacalesScreenBody(
        onDrawer = onDrawer,
        editViewModel = editViewModel,
        listViewModel = listViewModel,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HuacalesScreenBody(
    onDrawer: () -> Unit = {},
    editViewModel: EditHuacalesViewModel,
    listViewModel: ListHuacalesViewModel,
    navController: NavController
) {
    var showEdit by remember { mutableStateOf(false) }
    var huacalIdToEdit by remember { mutableStateOf<Int?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    val listState by listViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(listState.message) {
        listState.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            listViewModel.onEvent(ListHuacalesUiEvent.ShowMessage(null))
        }
    }

    Scaffold(
        floatingActionButton = {
            if (!showEdit) {
                FloatingActionButton(
                    onClick = {
                        editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                        editViewModel.onEvent(EditHuacalesUiEvent.Load(null))
                        huacalIdToEdit = null
                        showEdit = true
                    },
                    modifier = Modifier.testTag("fab_create")
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Crear nuevo huacal"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (showEdit) {
                EditHuacalesScreen(
                    viewModel = editViewModel,
                    onCancel = {
                        showEdit = false
                        huacalIdToEdit = null
                        editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                    },
                    onSaveSuccess = {
                        showEdit = false
                        huacalIdToEdit = null
                        listViewModel.onEvent(ListHuacalesUiEvent.Load)
                        editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                    }
                )
            } else {
                ListHuacalesScreen(
                    viewModel = listViewModel,
                    onEditHuacal = { huacalId ->
                        editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                        editViewModel.onEvent(EditHuacalesUiEvent.Load(huacalId))
                        huacalIdToEdit = huacalId
                        showEdit = true
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun HuacalesScreenPreview() {
    EsthiberValentinAP2_P1Theme {
        HuacalesScreenBody(
            onDrawer = {},
            editViewModel = hiltViewModel(),
            listViewModel = hiltViewModel(),
            navController = rememberNavController()
        )
    }
}
