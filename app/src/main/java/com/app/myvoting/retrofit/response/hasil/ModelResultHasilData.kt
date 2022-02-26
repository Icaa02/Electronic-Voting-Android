package com.app.myvoting.retrofit.response.hasil

import com.google.gson.annotations.SerializedName

class ModelResultHasilData {
    @SerializedName("data")
    lateinit var modelResultHasil: List<ModelResultHasil>
}