package edu.ucne.worktracker.ui.navigation

sealed class Rutas(var ruta: String){
    object Home: Rutas( "rutaHome")
    object OcupacionR: Rutas("RutaOcupacion")
    object  PersonaR: Rutas("RutaPersona")
}