package edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales

data class ValidacionesHuacales(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombreCliente(nombre: String): ValidacionesHuacales {
    if (nombre.isBlank()) {
        return ValidacionesHuacales(
            isValid = false,
            error = "El nombre del cliente no puede estar vacio."
        )
    }
    if (nombre.length < 3) {
        return ValidacionesHuacales(
            isValid = false,
            error = "El nombre del cliente debe tener al menos 3 caracteres."
        )
    }
    if (nombre.length > 50) {
        return ValidacionesHuacales(
            isValid = false,
            error = "El nombre del cliente no puede exceder los 50 caracteres."
        )
    }
    if (!nombre.all { it.isLetter() || it.isWhitespace() }) {
        return ValidacionesHuacales(
            isValid = false,
            error = "El nombre del cliente solo puede contener letras y espacios."
        )
    }
    return ValidacionesHuacales(isValid = true)
}

fun validateCantidad(cantidadStr: String): ValidacionesHuacales {
    if (cantidadStr.isBlank()) {
        return ValidacionesHuacales(
            isValid = false,
            error = "La cantidad no puede estar vacia."
        )
    }
    val cantidad = cantidadStr.toIntOrNull()
    if (cantidad == null || cantidad <= 0) {
        return ValidacionesHuacales(
            isValid = false,
            error = "La cantidad debe ser un numero entero positivo."
        )
    }
    return ValidacionesHuacales(isValid = true)
}

fun validatePrecio(precioStr: String): ValidacionesHuacales {
    if (precioStr.isBlank()) {
        return ValidacionesHuacales(
            isValid = false,
            error = "El precio no puede estar vacio."
        )
    }
    val precio = precioStr.toDoubleOrNull()
    if (precio == null || precio <= 0.0) {
        return ValidacionesHuacales(
            isValid = false,
            error = "El precio debe ser un numero positivo."
        )
    }
    return ValidacionesHuacales(isValid = true)
}