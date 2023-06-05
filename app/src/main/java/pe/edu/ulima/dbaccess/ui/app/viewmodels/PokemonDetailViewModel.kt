package pe.edu.ulima.dbaccess.ui.app.viewmodels

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.dbaccess.configs.LocalDB
import pe.edu.ulima.dbaccess.models.beans.Pokemon
import pe.edu.ulima.dbaccess.models.beans.ProfileKey
import kotlin.concurrent.thread

class PokemonDetailViewModel: ViewModel() {
    private val _id = MutableLiveData<Int>(0)
    var id: LiveData<Int> = _id
    fun updateId(it: Int){
        _id.postValue(it)
    }

    private val _title = MutableLiveData<String>("")
    var title: LiveData<String> = _title
    fun updateTitle(it: String){
        _title.postValue(it)
    }

    private val _name = MutableLiveData<String>("")
    var name: LiveData<String> = _name
    fun updateName(it: String){
        _name.postValue(it)
    }

    private val _imageUrl = MutableLiveData<String>("")
    var imageUrl: LiveData<String> = _imageUrl
    fun updateImageUrl(it: String){
        _imageUrl.postValue(it)
    }

    private val _number = MutableLiveData<Int>(0)
    var number: LiveData<Int> = _number
    fun updateNumber(it: Int){
        _number.postValue(it)
    }

    private val _weight = MutableLiveData<Float>(0f)
    var weight: LiveData<Float> = _weight
    fun updateWeight(it: Float){
        _weight.postValue(it)
    }

    private val _height = MutableLiveData<Float>(0f)
    var height: LiveData<Float> = _height
    fun updateHeight(it: Float){
        _height.postValue(it)
    }

    fun getPokemon(id: Int, context: Context){
        thread {
            val database = LocalDB.getDatabase(context)
            val pokemonDao = database.pokemonDao()
            val pokemon: Pokemon? = pokemonDao.getPokemonById(id)
            this.updateTitle("Editar Pokemon")
            this.updateWeight(pokemon!!.weight)
            this.updateHeight(pokemon!!.height)
            this.updateImageUrl(pokemon!!.imageUrl)
            this.updateNumber(pokemon!!.number)
            this.updateName(pokemon!!.name)
        }
    }

    fun unsetPokemon(){
        this.updateTitle("Crear Pokemon")
        this.updateWeight(0f)
        this.updateHeight(0f)
        this.updateImageUrl("")
        this.updateNumber(0)
        this.updateName("")
    }
}