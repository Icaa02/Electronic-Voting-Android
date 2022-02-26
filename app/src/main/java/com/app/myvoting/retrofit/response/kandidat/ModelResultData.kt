package com.app.myvoting.retrofit.response.kandidat

import com.google.gson.annotations.SerializedName

class ModelResultData {
    @SerializedName("data")
    lateinit var modelResult: List<ModelResult>
}