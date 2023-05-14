package udayfionics.nycschools.model

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("dbn")
    val dbn: String? = null,
    @SerializedName("school_name")
    val schoolName: String? = null,
    @SerializedName("total_students")
    val totalStudents: String? = null,
    @SerializedName("language_classes")
    val languageClasses: String? = null,
    @SerializedName("start_time")
    val startTime: String? = null,
    @SerializedName("end_time")
    val endTime: String? = null,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = null,
    @SerializedName("attendance_rate")
    val attendanceRate: String? = null,
    @SerializedName("graduation_rate")
    val graduationRate: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("fax_number")
    val faxNumber: String? = null,
    @SerializedName("school_email")
    val schoolEmail: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("state_code")
    val stateCode: String? = null,
    @SerializedName("zip")
    val zip: String? = null,
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
)

