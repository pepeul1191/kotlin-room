package pe.edu.ulima.dbaccess.configs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.ulima.dbaccess.daos.PokemonDao
import pe.edu.ulima.dbaccess.models.beans.Pokemon

@Database(
    entities = [ Pokemon::class, ], version = 1)
abstract class LocalDB : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        private var INSTANCE: LocalDB? = null

        fun getDatabase(context: Context): LocalDB {
            if (INSTANCE == null) {
                synchronized(LocalDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDB::class.java,
                        "local_db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}