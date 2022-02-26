package com.app.myvoting.retrofit.response.hasil

import com.google.gson.annotations.SerializedName


class ModelResultHasil {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("kandidat_id")
    lateinit var kandidatId: String

    @SerializedName("pemilih_id")
    lateinit var pemilihId: String

    @SerializedName("deleted_at")
    lateinit var deleted_at: String

    @SerializedName("created_at")
    lateinit var created_at: String

    @SerializedName("updated_at")
    lateinit var updated_at: String
}