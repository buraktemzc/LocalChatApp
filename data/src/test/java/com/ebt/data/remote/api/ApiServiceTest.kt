package com.ebt.data.remote.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    private lateinit var service: ApiService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var jsonFileName: String

    @Before
    fun setUp() {
        jsonFileName = "messages_response.json"
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun `Get messages from API and received data not null`() {
        runBlocking {
            enqueueMockResponse(jsonFileName)
            val responseBody = service.getMessages().body()
            mockWebServer.takeRequest()
            assertThat(responseBody).isNotNull()
        }
    }

    @Test
    fun `Get messages from API and request path is equal as expected`() {
        runBlocking {
            enqueueMockResponse(jsonFileName)
            val responseBody = service.getMessages().body()
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/files/chatdata-example.json")
        }
    }

    @Test
    fun `Get messages from API and received response has correct data size`() {
        runBlocking {
            enqueueMockResponse(jsonFileName)
            val responseBody = service.getMessages().body()
            val messageList = responseBody!!.messages
            assertThat(messageList?.size ?: 0).isEqualTo(4)
        }
    }

    @Test
    fun `Get messages from API and received response has correct content`() {
        runBlocking {
            enqueueMockResponse(jsonFileName)
            val responseBody = service.getMessages().body()
            val messageList = responseBody!!.messages
            val message = messageList?.get(0)
            assertThat(message?.id).isEqualTo("ASD_123")
            assertThat(message?.text).isEqualTo("Did you receive the e-mail I sent you this morning?")
            assertThat(message?.timestamp).isEqualTo(1602779420)
            assertThat(message?.user?.id).isEqualTo("2")
            assertThat(message?.user?.avatarURL).isEqualTo("https://randomuser.me/api/portraits/men/78.jpg")
            assertThat(message?.user?.nickname).isEqualTo("John Appleseed")
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun enqueueMockResponse(jsonFileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(jsonFileName)
        val resource = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(resource.readString(Charsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }
}