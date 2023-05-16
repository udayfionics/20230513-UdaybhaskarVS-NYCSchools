package udayfionics.nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import udayfionics.nycschools.model.SatScore
import udayfionics.nycschools.model.School
import udayfionics.nycschools.model.room.SchoolDatabase

class SchoolDetailsViewModel : BaseViewModel() {

    private var schoolDatabase: SchoolDatabase? = null

    var school = MutableLiveData<School>()
    var satScore = MutableLiveData<SatScore>()

    fun loadData(dbn: String, schoolDatabase: SchoolDatabase) {
        this.schoolDatabase = schoolDatabase
        fetchFromDatabase(dbn)
    }

    private fun fetchFromDatabase(dbn: String) {
        schoolDatabase?.let {
            launch {
                val schoolInfo = it.schoolDao().getSchool(dbn)
                school.value = schoolInfo
                val satScoreInfo = it.satScoreDao().getSatScore(dbn)
                satScore.value = satScoreInfo
            }
        }
    }
}