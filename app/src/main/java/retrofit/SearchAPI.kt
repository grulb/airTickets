package retrofit

import retrofit2.Response
import retrofit2.http.GET

interface SearchAPI {
    @GET("u/0/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getTickets(): Response<TicketsResponse>
}