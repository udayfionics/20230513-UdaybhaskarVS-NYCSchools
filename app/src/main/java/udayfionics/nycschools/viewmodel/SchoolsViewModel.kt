package udayfionics.nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import udayfionics.nycschools.di.DaggerApiComponent
import udayfionics.nycschools.model.School
import udayfionics.nycschools.model.remote.SchoolsService
import javax.inject.Inject

class SchoolsViewModel : ViewModel() {

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
        fetchSchools()
    }

    private fun fetchSchools() {
        loading.value = true
        disposable.add(
            schoolsService.getSchools()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<School>>() {
                    override fun onSuccess(schoolsList: List<School>) {
                        schools.value = schoolsList.sortedBy {
                            it.schoolName
                        }
                        // schools.value = schoolsList
                        loading.value = false
                        error.value = null
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        error.value = e.message
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}