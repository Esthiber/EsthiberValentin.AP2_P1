package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list

interface ListHuacalesUiEvent {
    data object Load: ListHuacalesUiEvent
    data object CreateNew: ListHuacalesUiEvent

    data class Delete(val id: Int): ListHuacalesUiEvent
    data class Edit(val id: Int): ListHuacalesUiEvent
    data class ShowMessage(val message: String?): ListHuacalesUiEvent
}