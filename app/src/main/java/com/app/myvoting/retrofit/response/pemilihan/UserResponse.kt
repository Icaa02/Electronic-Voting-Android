package com.app.myvoting.retrofit.response.pemilihan

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class UserResponse {
    @SerializedName("kandidat_id")
    @Expose
    var kandidat_id: String? = null

    @SerializedName("pemilih_id")
    @Expose
    var pemilih_id: String? = null

    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null
}

