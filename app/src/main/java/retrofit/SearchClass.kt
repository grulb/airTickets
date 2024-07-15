package retrofit

import com.google.gson.annotations.SerializedName

data class SearchClass(
    @SerializedName("id") val searchId: Int,
    @SerializedName("title") val searchTitle: String,
    @SerializedName("time_range") val searchFlightRange: List<String>,
    @SerializedName("price") val searchPrice: SearchPrice
)

data class SearchPrice(
    @SerializedName("value") val searchValue: Int
)

data class TicketsResponse(
    @SerializedName("tickets_offers") val searchTicketsOffers: List<SearchClass>
)