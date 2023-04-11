package edu.ucne.worktracker.ui.navigation

sealed class Rutas(val ruta: String){
    object Home : Rutas( "rutaHome")
    object RegistroObra : Rutas("RutaObra")
    object  RegistroMaterial : Rutas("RutaMaterial")

    object  MaterialesList: Rutas("RutaListaMateriales")
    object  CardDialogListR: Rutas("RutaDialogoLista")
}