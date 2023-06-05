package pe.edu.ulima.dbaccess.daos

import androidx.room.Dao
import androidx.room.Insert
import pe.edu.ulima.dbaccess.models.beans.Pokemon

@Dao
interface PokemonDao {
    @Insert
    fun insertMany(pokemon: List<Pokemon>)
}