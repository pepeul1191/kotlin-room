package pe.edu.ulima.dbaccess.ui.app.viewmodels

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pe.edu.ulima.dbaccess.configs.BackendClient
import pe.edu.ulima.dbaccess.configs.LocalDB
import pe.edu.ulima.dbaccess.models.beans.Pokemon
import pe.edu.ulima.dbaccess.models.beans.ProfileKey
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
                // db
                val userId = 1
                val database = LocalDB.getDatabase(activity as Context)
                val profileKeyDao = database.profileKeyDao()
                val profileKey: ProfileKey? = profileKeyDao.getProfileUserById(userId)
                Log.d("HOME_VIEW_MODEL", "0 ++++++++++++++++++++++++++++++")
                Log.d("HOME_VIEW_MODEL", profileKey.toString())
                if(profileKey == null){
                    Log.d("HOME_VIEW_MODEL", "1 +++++++++++++++++++++++++++++++ IF")
                    //profileKeyDao.setFirstLoad(ProfileKeyEntity(userId = userId, firstLoad = true))
                    val response = apiService.fetchAll("", "").execute()
                    val pokemonDao = database.pokemonDao()
                    if(response.isSuccessful){
                        println(response.body())
                        _pokemons.value = response.body()
                        pokemonDao.deleteAllPokemons()
                        pokemonDao.insertMany(_pokemons.value!!)
                        profileKeyDao.setFirstLoad(ProfileKey(userId = userId, firstLoad = true))
                    }
                }else{
                    val pokemonDao = database.pokemonDao()
                    _pokemons.value = pokemonDao.getPokemons()
                    Log.d("HOME_VIEW_MODEL", "2 +++++++++++++++++++++++++++++++ ELSE")
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