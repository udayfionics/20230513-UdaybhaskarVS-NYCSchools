package udayfionics.nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import udayfionics.nycschools.model.School
import udayfionics.nycschools.model.remote.SchoolsService
import java.util.concurrent.TimeUnit

class SchoolsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var schoolsService: SchoolsService

    @InjectMocks
    var schoolsViewModel = SchoolsViewModel()

    private var testSingle: Single<List<School>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getSchoolsSuccess() {
        val school = School("12AX23", "My School Name", "12")
        val schoolList = arrayListOf(school)

        testSingle = Single.just(schoolList)

        Mockito.`when`(schoolsService.getSchools()).thenReturn(testSingle)

        schoolsViewModel.refresh()

        Assert.assertEquals(1, schoolsViewModel.schools.value?.size)
        Assert.assertEquals(null, schoolsViewModel.error.value)
        Assert.assertEquals(false, schoolsViewModel.loading.value)
    }

    @Test
    fun getSchoolsFail() {

        testSingle = Single.error(Throwable("myError"))

        Mockito.`when`(schoolsService.getSchools()).thenReturn(testSingle)

        schoolsViewModel.refresh()

        Assert.assertEquals(null, schoolsViewModel.schools.value?.size)
        Assert.assertEquals("myError", schoolsViewModel.error.value)
        Assert.assertEquals(false, schoolsViewModel.loading.value)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker({ it.run() }, false)
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}