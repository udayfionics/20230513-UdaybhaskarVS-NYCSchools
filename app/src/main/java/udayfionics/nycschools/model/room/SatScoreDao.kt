package udayfionics.nycschools.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import udayfionics.nycschools.model.SatScore

@Dao
interface SatScoreDao {
    @Insert
    suspend fun insertAll(vararg satScore: SatScore): List<Long>

    @Query("SELECT * FROM sat_score")
    suspend fun getAllSatScores(): List<SatScore>

    @Query("SELECT * FROM sat_score WHERE dbn = :dbn")
    suspend fun getSatScore(dbn: String): SatScore

    @Query("DELETE FROM sat_score")
    suspend fun deleteAllSatScores()
}