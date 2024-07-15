package retrofit

import retrofit2.Response
import retrofit2.http.GET

interface AllTicketsAPI {
    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getAllTickets(): Response<AllTicketsResponse>
}