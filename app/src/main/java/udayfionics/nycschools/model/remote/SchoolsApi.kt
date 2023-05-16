package udayfionics.nycschools.model.remote

import io.reactivex.Single
import retrofit2.http.GET
import udayfionics.nycschools.model.SatScore
import udayfionics.nycschools.model.School

interface SchoolsApi {

    @GET("resource/s3k6-pzi2.json")
    fun getSchools(): Single<List<School>>

    @GET("resource/f9bf-2cp4.json")
    fun getSatScores(): Single<List<SatScore>>
}