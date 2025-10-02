package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales
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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            androidx.compose.material3.CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).testTag("loading")
            )
        }
        androidx.compose.foundation.lazy.LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .testTag("Lista_Huacales")
        ) {
            items(state.huacales) { huacal ->
                HuacalesCard(
                    huacales = huacal,
                    onEdit = { onEvent(ListHuacalesUiEvent.Edit(huacal.IdEntrada)) },
                    onDelete = { onEvent(ListHuacalesUiEvent.Delete(huacal.IdEntrada)) }
                )
            }
        }
    }
}

@Composable
fun HuacalesCard(
    huacales: Huacales, onEdit: () -> Unit, onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .testTag("card_${huacales.IdEntrada}")
            .clickable { onEdit() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(huacales.NombreCliente,style = MaterialTheme.typography.titleMedium)
                Text("Cliente: ${huacales.NombreCliente}")
            }
            TextButton(
                onClick = onEdit,
                modifier = Modifier.testTag("Editbutton_${huacales.IdEntrada}")
            ){
                Text("Editar")
            }
            TextButton(
                onClick = onDelete,
                modifier = Modifier.testTag("delete_button_${huacales.IdEntrada}")
            ) {
                Text("Eliminar")
            }
        }
    }
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