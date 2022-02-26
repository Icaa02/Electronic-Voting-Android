package com.app.myvoting.retrofit.response.pemilihan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserRequest{
    @SerializedName("kandidat_id")
    @Expose
    var kandidat_id: String? = null

    @SerializedName("pemilih_id")
    @Expose
    var pemilih_id: String? = null
}
