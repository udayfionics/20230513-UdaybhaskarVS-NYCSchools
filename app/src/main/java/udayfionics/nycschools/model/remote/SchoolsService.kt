package udayfionics.nycschools.model.remote

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import udayfionics.nycschools.model.School

class SchoolsService {

    private val BASE_URL = "https://data.cityofnewyork.us"

    private val schoolsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SchoolsApi::class.java)

    fun getSchools(): Single<List<School>> {
        return schoolsApi.getSchools()
    }
}