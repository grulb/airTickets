package retrofit

import retrofit2.Response
import retrofit2.http.GET

interface MusicAPI {
    @GET("u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    suspend fun getMusic(): Response<MusicResponse>
}