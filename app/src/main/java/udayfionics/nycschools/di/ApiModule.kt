package udayfionics.nycschools.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import udayfionics.nycschools.model.remote.SchoolsApi
import udayfionics.nycschools.model.remote.SchoolsService

@Module
class ApiModule {

    private val BASE_URL = "https://data.cityofnewyork.us"

    @Provides
    fun providesSchoolsApi(): SchoolsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SchoolsApi::class.java)
    }

    @Provides
    fun providesSchoolsService(): SchoolsService {
        return SchoolsService()
    }
}

