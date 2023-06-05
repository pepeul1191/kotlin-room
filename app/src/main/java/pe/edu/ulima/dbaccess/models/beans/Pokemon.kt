package pe.edu.ulima.dbaccess.models.beans

import com.google.gson.annotations.SerializedName

data class Pokemon(
    var id: Int = 0,
    var number: Int = 0,
    var name: String = "",
    var weight: Float = 0F,
    var height: Float = 0F,
    @SerializedName("image_url")
    var imageUrl: String = "",
)