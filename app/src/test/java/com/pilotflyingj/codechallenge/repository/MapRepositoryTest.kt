package com.pilotflyingj.codechallenge.repository

import androidx.lifecycle.MutableLiveData
import com.pilotflyingj.codechallenge.di.NetworkModule
import com.pilotflyingj.codechallenge.repository.models.Site
import com.pilotflyingj.codechallenge.viewmodel.MapsViewModel
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.io.File
import java.io.FileReader
import java.net.HttpURLConnection

class MapRepositoryTest {

    private val mockWebServer = MockWebServer()
    private val retro = NetworkModule.provideRetrofitInstance()
    private val service = NetworkModule.provideLocationService(retro)
    private val repo = MapRepository(service)

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        mockWebServer.start()
    }

    //check that response is what it should be based on using a static json file
    @Test
    fun `fetch locations and check for response code 200`() = runBlocking {
        val expectedResponseString = File("C:\\Users\\staylow\\AndroidStudioProjects\\androidcodechallenge-master\\" +
                "androidcodechallenge-master\\app\\src\\main\\res\\success_response.json")
            .inputStream().readBytes().toString(Charsets.UTF_8)
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(expectedResponseString)
        mockWebServer.enqueue(mockResponse)
        val actualResponse = repo.requestLocations()
        assertEquals(mockResponse.getBody(), actualResponse.body())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}