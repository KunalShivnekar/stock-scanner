package com.kunal.stockscanner.view.scans

import com.kunal.stockscanner.base.BaseUnitTest
import com.kunal.stockscanner.data.base.DataSource.GetItemsCallback
import com.kunal.stockscanner.data.base.Response
import com.kunal.stockscanner.data.scans.RemoteDataSource
import com.kunal.stockscanner.view.scans.model.Scan
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.*

/**
 * Created by kunal on 2019-07-28.
 */
class ScansPresenterTest : BaseUnitTest(){

    @MockK
    private lateinit var scansPresenter: ScansPresenter

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    @MockK
    private lateinit var view: ScansContract.View

    override fun setup(){
        super.setup()
        MockKAnnotations.init(this)
        scansPresenter = ScansPresenter(remoteDataSource)
        scansPresenter.attachView(view, lifecycleOwner)

        every { view.showLoader() } just Runs
        every { view.hideLoader() } just Runs
        every { view.setData(any()) } just Runs
        every { view.showError(any()) } just Runs
    }

    @Test
    fun `on Start - remoteDataSource success `() {
        val callbackSlot = slot<GetItemsCallback<Scan>>()
        val mockData  =ArrayList<Scan>()
        every { remoteDataSource.getItems(capture(callbackSlot)) } answers {
            callbackSlot.captured.onSuccess(mockData)
        }

        scansPresenter.onStart()

        verify(exactly = 1) { view.showLoader() }
        verify(exactly = 1) { remoteDataSource.getItems(eq(callbackSlot.captured)) }
        verify { view.setData(mockData) }
        verify { view.hideLoader() }
    }

    @Test
    fun `on Start - remoteDataSource failure `() {
        val callbackSlot = slot<GetItemsCallback<Scan>>()
        val mockData  = Response("Error")
        every { remoteDataSource.getItems(capture(callbackSlot)) } answers {
            callbackSlot.captured.onFailure(mockData)
        }

        scansPresenter.onStart()

        verify(exactly = 1) { view.showLoader() }
        verify(exactly = 1) { remoteDataSource.getItems(eq(callbackSlot.captured)) }
        verify { view.showError(mockData.description) }
        verify { view.hideLoader() }
    }
}