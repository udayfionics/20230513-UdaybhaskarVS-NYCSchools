package udayfionics.nycschools.model.remote

import io.reactivex.Single
import udayfionics.nycschools.di.DaggerApiComponent
import udayfionics.nycschools.model.SatScore
import udayfionics.nycschools.model.School
import javax.inject.Inject

class SchoolsService {
    @Inject
    lateinit var schoolsApi: SchoolsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getSchools(): Single<List<School>> {
        return schoolsApi.getSchools()
    }

    fun getSatScores(): Single<List<SatScore>> {
        return schoolsApi.getSatScores()
    }
}