package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list

import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales

data class ListHuacalesUiState(
    val isLoading: Boolean = false,
    val huacales: List<Huacales> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)
