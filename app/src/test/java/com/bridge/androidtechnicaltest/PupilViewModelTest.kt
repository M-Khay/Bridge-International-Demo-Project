package com.bridge.androidtechnicaltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList
import com.bridge.androidtechnicaltest.network.ApiResult
import com.bridge.androidtechnicaltest.network.Loading
import com.bridge.androidtechnicaltest.network.PupilRepository
import com.bridge.androidtechnicaltest.ui.PupilViewModel
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class PupilViewModelTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private var viewModel: PupilViewModel? = null

    @Mock
    var apiClient: PupilRepository? = null

    @Mock
    var observer: Observer<ApiResult<List<Pupil>>>? = null

    @Mock
    var lifecycleOwner: LifecycleOwner? = null
    var lifecycle: Lifecycle? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner!!)
        viewModel = PupilViewModel(apiClient!!)
        viewModel?._pupilsListState?.observeForever(observer!!)
    }


    @Test
    fun testObserverForApiResult() {
        // Mock API response
        runBlocking {
            Mockito.`when`(apiClient?.fetchPupils(1))
                    .thenReturn(PupilList((listOf(Pupil(1, 1L, "k", "d", "d", 0.00, 0.00))), 1))
            viewModel?.getPupilList(1)
            apiClient?.fetchPupils(1)

        }
        Mockito.verify(observer)?.onChanged(Loading(false))
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        apiClient = null
        viewModel = null
    }

}