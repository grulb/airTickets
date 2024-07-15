package retrofit

import com.google.gson.annotations.SerializedName

data class MusicStripe (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("town") val town: String,
    @SerializedName("price") val price: MusicPrice
)

data class MusicPrice(
    @SerializedName("value") val value: Int
)

data class MusicResponse(
    @SerializedName("offers") val offers: List<MusicStripe>
)