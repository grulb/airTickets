package retrofit

import com.google.gson.annotations.SerializedName

data class AllTicketsClass(
    @SerializedName("id") val allId: Int,
    @SerializedName("badge") val allBadge: String?,
    @SerializedName("price") val allPrice: AllPrice,
    @SerializedName("provider_name") val allProviderName: String,
    @SerializedName("company") val allCompany: String,
    @SerializedName("departure") val allDeparture: AllDeparture,
    @SerializedName("arrival") val allArrival: AllArrival,
    @SerializedName("has_transfer") val allTransfer: Boolean,
    @SerializedName("has_visa_transfer") val allVisaTransfer: Boolean,
    @SerializedName("luggage") val allLuggage: AllLuggage,
    @SerializedName("hand_luggage") val handLuggage: HandLuggage,
    @SerializedName("is_returnable") val allIsReturnable: Boolean,
    @SerializedName("is_exchangable") val allIsExchangable: Boolean
)

data class AllPrice(
    @SerializedName("value") val allValue: Int
)

data class AllDeparture(
    @SerializedName("town") val allDepTown: String,
    @SerializedName("date") val allDepDate: String,
    @SerializedName("airport") val allDepAirport: String
)

data class AllArrival(
    @SerializedName("town") val allArrTown: String,
    @SerializedName("date") val allArrDate: String,
    @SerializedName("airport") val allArrAirport: String
)

data class AllLuggage(
    @SerializedName("has_luggage") val luggageBool: Boolean,
    @SerializedName("price") val luggagePrice: AllPrice?
)

data class HandLuggage(
    @SerializedName("has_hand_luggage") val hasHandLuggage: Boolean,
    @SerializedName("size") val handLuggageSize: String?
)

data class AllTicketsResponse(
    @SerializedName("tickets") val allTicketsList: List<AllTicketsClass>
)