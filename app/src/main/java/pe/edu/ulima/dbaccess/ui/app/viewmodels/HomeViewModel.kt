package pe.edu.ulima.dbaccess.ui.app.viewmodels

import android.app.Activity
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pe.edu.ulima.dbaccess.configs.BackendClient
import pe.edu.ulima.dbaccess.models.beans.Pokemon
import pe.edu.ulima.dbaccess.services.PokemonService
import kotlin.concurrent.thread

class HomeViewModel: ViewModel() {
    private val _pokemons = mutableStateOf<List<Pokemon>?>(
        listOf()
    )
    val pokemons get() = _pokemons.value
    fun setPokemons(activity: Activity) {
        val apiService = BackendClient.buildService(PokemonService::class.java)
        thread {
            try {
                val response = apiService.fetchAll("", "").execute()
                if(response.isSuccessful){
                    println(response.body())
                    _pokemons.value = response.body()
                }else{
                    activity.runOnUiThread{
                        Toast.makeText(
                            activity,
                            "Error HTTP: No se pudo traer el pokemon",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
                activity.runOnUiThread{
                    Toast.makeText(
                        activity,
                        "Error HTTP: No se pudo traer el pokemon",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}