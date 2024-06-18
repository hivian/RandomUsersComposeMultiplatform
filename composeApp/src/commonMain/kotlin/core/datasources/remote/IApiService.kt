package datasources.remote

interface IApiService {

    @Throws(Exception::class)
    @GET("api/1.3")
    suspend fun fetchRandomUsers(
        @Query("seed") seed: String = "easypeasy",
        @Query("results") results: Int = 20,
        @Query("page") page: Int = 1): Results

}