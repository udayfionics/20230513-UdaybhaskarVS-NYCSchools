package udayfionics.nycschools.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import udayfionics.nycschools.di.DaggerApiComponent
import udayfionics.nycschools.model.School
import udayfionics.nycschools.model.remote.SchoolsService
import udayfionics.nycschools.model.room.SchoolDatabase
import javax.inject.Inject

class SchoolsViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var schoolsService: SchoolsService
    private val disposable: CompositeDisposable = CompositeDisposable()

    var schools = MutableLiveData<List<School>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String?>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh() {
        fetchFromRemote()
    }

    fun loadData() {
        fetchFromDatabase()
    }

    private fun fetchFromDatabase() {
        loading.value = true
        launch {
            val schoolsList = SchoolDatabase(getApplication()).schoolDao().getAllSchools()
            if (schoolsList.isNotEmpty()) {
                updateSchoolsListUI(schoolsList)
                Toast.makeText(
                    getApplication(),
                    "Schools retrieved from database",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                fetchFromRemote()
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
                        Toast.makeText(
                            getApplication(),
                            "Schools retrieved from remote",
                            Toast.LENGTH_SHORT
                        )
                            .show()
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
        launch {
            val dao = SchoolDatabase(getApplication()).schoolDao()
            dao.deleteAllSchools()
            val result = dao.insertAll(*schoolList.toTypedArray())
            Toast.makeText(getApplication(), "${result.size} stored", Toast.LENGTH_SHORT).show()
            /*var i = 0
            while (i < schoolList.size) {
                schoolList[i].dbn = result[i].toString()
                ++i
            }*/
            // updateSchoolsListUI(schoolList)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}