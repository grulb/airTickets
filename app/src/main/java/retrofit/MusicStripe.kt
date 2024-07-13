package retrofit

import android.util.Printer
import android.util.TypedValue
import com.google.gson.annotations.SerializedName
import java.io.Serial

data class MusicStripe (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("town") val town: String,
    @SerializedName("price") val price: Price
)

data class Price(
    @SerializedName("value") val value: Int
)

data class MusicResponse(
    @SerializedName("offers") val offers: List<MusicStripe>
)