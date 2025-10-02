package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.ucne.esthibervalentin_ap2_p1.ui.theme.EsthiberValentinAP2_P1Theme

@Composable
fun ListHuacalesScreen(
    viewModel: ListHuacalesViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListHuacalesScreenBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ListHuacalesScreenBody(
    state: ListHuacalesUiState,
    onEvent: (ListHuacalesUiEvent) -> Unit
) {

}

@Composable
@Preview
fun PreviewListHuacalesScreenBody() {
    EsthiberValentinAP2_P1Theme {
        ListHuacalesScreenBody(
            state = ListHuacalesUiState(),
            onEvent = {}
        )
    }
}