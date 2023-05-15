package udayfionics.nycschools.di

import dagger.Component
import udayfionics.nycschools.model.remote.SchoolsService
import udayfionics.nycschools.viewmodel.SchoolsViewModel

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(schoolsService: SchoolsService)

    fun inject(schoolsViewModel: SchoolsViewModel)
}