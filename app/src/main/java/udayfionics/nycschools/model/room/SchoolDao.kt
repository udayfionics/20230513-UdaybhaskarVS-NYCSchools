package udayfionics.nycschools.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import udayfionics.nycschools.model.School

@Dao
interface SchoolDao {
    @Insert
    suspend fun insertAll(vararg schools: School): List<Long>

    @Query("SELECT * FROM school")
    suspend fun getAllSchools(): List<School>

    @Query("SELECT * FROM school WHERE dbn = :dbn")
    suspend fun getSchool(dbn: String): School

    @Query("DELETE FROM school")
    suspend fun deleteAllSchools()
}