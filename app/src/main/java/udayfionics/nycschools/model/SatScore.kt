package udayfionics.nycschools.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "sat_score")
data class SatScore(
    @PrimaryKey
    @ColumnInfo("dbn")
    @SerializedName("dbn")
    val dbn: String = "",
    @ColumnInfo("school_name")
    @SerializedName("school_name")
    val schoolName: String? = null,

    @ColumnInfo("num_of_sat_test_takers")
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String? = null,
    @ColumnInfo("sat_critical_reading_avg_score")
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String? = null,
    @ColumnInfo("sat_writing_avg_score")
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String? = null,
    @ColumnInfo("sat_math_avg_score")
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String? = null
)

