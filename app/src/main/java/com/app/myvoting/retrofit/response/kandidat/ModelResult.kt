package com.app.myvoting.retrofit.response.kandidat

import com.google.gson.annotations.SerializedName

class ModelResult {
    var img: Int = 0

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("nama_kandidat")
    lateinit var namaKandidat: String

    @SerializedName("no_urut")
    var noUrut: Int = 0

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("visi_misi")
    lateinit var visiMisi: String

    @SerializedName("deleted_at")
    lateinit var deleted_at: String

    @SerializedName("created_at")
    lateinit var created_at: String

    @SerializedName("updated_at")
    lateinit var updated_at: String
}