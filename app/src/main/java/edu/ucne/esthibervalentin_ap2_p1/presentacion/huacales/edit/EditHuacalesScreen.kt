package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.esthibervalentin_ap2_p1.ui.theme.EsthiberValentinAP2_P1Theme

@Composable
fun EditHuacalesScreen(
    viewModel: EditHuacalesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    EditHuacalesBody(
        state, viewModel::onEvent
    )
}

@Composable
fun EditHuacalesBody(
    state: EditHuacalesUiState,
    onEvent: (EditHuacalesUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = state.nombreCliente,
            onValueChange = { onEvent(EditHuacalesUiEvent.NombreClienteChanged(it)) },
            label = { Text("Cliente: ") },
            isError = state.nombreClienteError != null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_Cliente")
        )
        if (state.nombreClienteError != null) {
            Text(
                text = state.nombreClienteError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))


        OutlinedTextField(
            value = if (state.cantidad == 0) "" else state.cantidad.toString(),
            onValueChange = { onEvent(EditHuacalesUiEvent.CantidadChanged(it)) },
            label = { Text("Cantidad: ") },
            isError = state.cantidadError != null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_Cantidad")
        )
        if (state.cantidadError != null) {
            Text(
                text = state.cantidadError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = if (state.precio == 0.0) "" else state.precio.toString(),
            onValueChange = { onEvent(EditHuacalesUiEvent.PrecioChanged(it)) },
            label = { Text("Precio: ") },
            isError = state.precioError != null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_Precio")
        )
        if (state.precioError != null) {
            Text(
                text = state.precioError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.fecha,
            onValueChange = { onEvent(EditHuacalesUiEvent.FechaChanged(it)) },
            label = { Text("Fecha: ") },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_Fecha")
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onEvent(EditHuacalesUiEvent.Save) },
            enabled = !state.isSaving,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("btn_guardar")
        ) {
            Text("Guardar")
        }
    }
}

@Composable
@Preview
fun EditHuacalesBodyPreview() {
    EsthiberValentinAP2_P1Theme {

        EditHuacalesBody(
            state = EditHuacalesUiState(),
            onEvent = {}
        )
    }
}