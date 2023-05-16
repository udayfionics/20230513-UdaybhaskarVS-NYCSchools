package udayfionics.nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import udayfionics.nycschools.di.DaggerApiComponent
import udayfionics.nycschools.model.SatScore
import udayfionics.nycschools.model.School
import udayfionics.nycschools.model.remote.SchoolsService
import udayfionics.nycschools.model.room.SchoolDatabase
import javax.inject.Inject

class SchoolsViewModel : BaseViewModel() {

    @Inject
    lateinit var schoolsService: SchoolsService
    private var schoolDatabase: SchoolDatabase? = null
    private val disposable: CompositeDisposable = CompositeDisposable()

    var schools = MutableLiveData<List<School>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String?>()
    var toast = MutableLiveData<String?>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh() {
        fetchFromRemote()
    }

    fun loadData(schoolDatabase: SchoolDatabase) {
        this.schoolDatabase = schoolDatabase
        fetchFromDatabase()
    }

    private fun fetchFromDatabase() {
        loading.value = true
        schoolDatabase?.let {
            launch {
                val schoolsList = it.schoolDao().getAllSchools()
                if (schoolsList.isNotEmpty()) {
                    updateSchoolsListUI(schoolsList)
                    //toast.value = "Schools retrieved from database"
                } else {
                    fetchFromRemote()
                }
            }
        }
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            schoolsService.getSchools()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<School>>() {
                    override fun onSuccess(schoolsList: List<School>) {
                        updateSchoolsListUI(schoolsList)
                        storeSchoolsLocally(schoolsList)
                        //toast.value = "Schools retrieved from remote"
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        error.value = e.message
                    }
                })
        )
        disposable.add(
            schoolsService.getSatScores()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<SatScore>>() {
                    override fun onSuccess(satScoreList: List<SatScore>) {
                        storeSatScoresLocally(satScoreList)
                        //toast.value = "SatScores retrieved from remote"
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        error.value = e.message
                    }
                })
        )
    }

    private fun updateSchoolsListUI(schoolsList: List<School>) {
        schools.value = schoolsList.sortedBy {
            it.schoolName
        }
        // schools.value = schoolsList
        loading.value = false
        error.value = null
    }

    private fun storeSchoolsLocally(schoolList: List<School>) {
        schoolDatabase?.let {
            launch {
                val dao = it.schoolDao()
                dao.deleteAllSchools()
                val result = dao.insertAll(*schoolList.toTypedArray())
                //toast.value = "${result.size} stored"
            }
        }
    }

    private fun storeSatScoresLocally(satScoreList: List<SatScore>) {
        schoolDatabase?.let {
            launch {
                val satScoreDao = it.satScoreDao()
                satScoreDao.deleteAllSatScores()
                satScoreDao.insertAll(*satScoreList.toTypedArray())
                //val satScoreResult = satScoreDao.insertAll(*satScoreList.toTypedArray())
                //toast.value = "${satScoreResult.size} stored"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}