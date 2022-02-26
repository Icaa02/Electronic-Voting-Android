package com.app.myvoting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.annisa.evoteing.retrofit.RetrofitClient
import com.app.myvoting.databinding.ActivityDetailKandidatBinding
import com.app.myvoting.retrofit.response.hasil.ModelResultHasilData
import com.app.myvoting.retrofit.response.pemilihan.UserRequest
import com.app.myvoting.retrofit.response.pemilihan.UserResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class DetailKandidatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKandidatBinding

    private var kandidatId: Int? = null
    private lateinit var namaKandidat: String
    private var noUrut: Int? = null
    private lateinit var visiMisi: String
    private var id by Delegates.notNull<Int>()
    private var img by Delegates.notNull<Int>()
    private var checkId = ""

    companion object {
        const val KANDIDAT_ID = "kandidat_id"
        const val NAMA_KANDIDAT = "nama_kandidat"
        const val NO_URUT = "no_urut"
        const val VISI_MISI = "visi_misi"
        const val IMAGE = "image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKandidatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kandidatId = intent.getIntExtra(KANDIDAT_ID, 0)!!
        namaKandidat = intent.getStringExtra(NAMA_KANDIDAT)!!
        noUrut = intent.getIntExtra(NO_URUT, 0)
        visiMisi = intent.getStringExtra(VISI_MISI)!!
        id = intent.getIntExtra("ID", 0)
        img = intent.getIntExtra(IMAGE, 0)

        showDataKandidat()
        getHasil()

        binding.btnVote.setOnClickListener {
                if (id.toString() == checkId){
                    Toast.makeText(this, "Anda Sudah Memilih", Toast.LENGTH_SHORT).show()
                } else {
                    postPemilihan()

                    Handler().postDelayed({
//                        Intent(this@DetailKandidatActivity, MainActivity::class.java).also {
//                            startActivity(it)
//                        }
                        onBackPressed()
                    }, 1000)
                }
        }
    }

    private fun showDataKandidat(){
        binding.imageView2.setImageResource(img)
        binding.tvNamaCalon.text = "Nama Calon: $namaKandidat"
        binding.tvNoUrutDetail.text = "No Urut: $noUrut"
        binding.tvVisiMisi.text = "$visiMisi"
    }

    private fun postPemilihan() {
        val uR = UserRequest()
        uR.kandidat_id = kandidatId.toString()
        uR.pemilih_id = id.toString()

        RetrofitClient.instance.postPemilihan(uR
        ).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    Snackbar.make(binding.root, "Voting Berhasil", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(binding.root, "Anda Yang Tidak Beres", Snackbar.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Snackbar.make(binding.root, t.message.toString(), Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun getHasil(){
        RetrofitClient.instance.getHasil().enqueue(object : Callback<ModelResultHasilData> {
            override fun onResponse(
                call: Call<ModelResultHasilData>,
                response: Response<ModelResultHasilData>
            ) {
                val size = response.body()?.modelResultHasil!!.size
                for (i in 0 until size){
                    val data = response.body()!!.modelResultHasil[i].pemilihId
                    if (data == id.toString()){
                        checkId = data
                    }
                }
            }

            override fun onFailure(call: Call<ModelResultHasilData>, t: Throwable) {
                Toast.makeText(this@DetailKandidatActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}