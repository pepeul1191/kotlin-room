package pe.edu.ulima.dbaccess.models.beans

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.dbaccess.models.beans.Pokemon

@Dao
interface PokemonDao {
    @Insert
    fun insertMany(pokemon: List<Pokemon>)

    @Query("DELETE FROM pokemons")
    fun deleteAllPokemons()

    @Query("SELECT * FROM pokemons")
    fun getPokemons(): List<Pokemon>
}