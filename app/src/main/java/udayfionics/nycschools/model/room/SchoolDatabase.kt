package udayfionics.nycschools.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import udayfionics.nycschools.model.SatScore
import udayfionics.nycschools.model.School

@Database(entities = [School::class, SatScore::class], version = 1)
abstract class SchoolDatabase : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun satScoreDao(): SatScoreDao

    companion object {
        @Volatile
        private var instance: SchoolDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SchoolDatabase::class.java,
            "schooldb"
        ).build()
    }
}