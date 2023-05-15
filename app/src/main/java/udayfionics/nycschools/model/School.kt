package udayfionics.nycschools.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class School(
    @PrimaryKey
    @ColumnInfo("dbn")
    @SerializedName("dbn")
    val dbn: String = "",
    @ColumnInfo("school_name")
    @SerializedName("school_name")
    val schoolName: String? = null,
    @ColumnInfo("total_students")
    @SerializedName("total_students")
    val totalStudents: String? = null,
    @ColumnInfo("language_classes")
    @SerializedName("language_classes")
    val languageClasses: String? = null,
    @ColumnInfo("start_time")
    @SerializedName("start_time")
    val startTime: String? = null,
    @ColumnInfo("end_time")
    @SerializedName("end_time")
    val endTime: String? = null,

    @ColumnInfo("overview_paragraph")
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = null,
    @ColumnInfo("attendance_rate")
    @SerializedName("attendance_rate")
    val attendanceRate: String? = null,
    @ColumnInfo("graduation_rate")
    @SerializedName("graduation_rate")
    val graduationRate: String? = null,
    @ColumnInfo("phone_number")
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @ColumnInfo("fax_number")
    @SerializedName("fax_number")
    val faxNumber: String? = null,
    @ColumnInfo("school_email")
    @SerializedName("school_email")
    val schoolEmail: String? = null,
    @ColumnInfo("website")
    @SerializedName("website")
    val website: String? = null,

    @ColumnInfo("primary_address_line_1")
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String? = null,
    @ColumnInfo("city")
    @SerializedName("city")
    val city: String? = null,
    @ColumnInfo("state_code")
    @SerializedName("state_code")
    val stateCode: String? = null,
    @ColumnInfo("zip")
    @SerializedName("zip")
    val zip: String? = null,
    @ColumnInfo("latitude")
    @SerializedName("latitude")
    val latitude: String? = null,
    @ColumnInfo("longitude")
    @SerializedName("longitude")
    val longitude: String? = null,
)

