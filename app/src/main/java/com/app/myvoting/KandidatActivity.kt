package com.app.myvoting

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.annisa.evoteing.retrofit.RetrofitClient
import com.app.myvoting.adapter.KandidatAdapter
import com.app.myvoting.databinding.ActivityKandidatBinding
import com.app.myvoting.retrofit.response.kandidat.ModelResult
import com.app.myvoting.retrofit.response.kandidat.ModelResultData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KandidatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKandidatBinding
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKandidatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvKandidat.setHasFixedSize(true)
        binding.rvKandidat.layoutManager = LinearLayoutManager(this@KandidatActivity)

        id = intent.getIntExtra("ID", 0)
        getKandidat(id)


    }

    private fun getKandidat(id: Int){
        val progressDialog = ProgressDialog(this@KandidatActivity)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Mohon Menunggu..")
        progressDialog.show()
        RetrofitClient.instance.getKandidat().enqueue(object : Callback<ModelResultData> {
            override fun onResponse(
                call: Call<ModelResultData>,
                response: Response<ModelResultData>
            ) {
                val kandidat = ArrayList<ModelResult>()
                if (response.isSuccessful){
                    val size = response.body()?.modelResult!!.size
                    for (i in 0 until size){
                        val data = response.body()!!.modelResult[i]

                        kandidat.add(data)
                    }
                } else {
                    Log.d("Ica-Error", "Gagal".toString())
                    Toast.makeText(this@KandidatActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }

                progressDialog.dismiss()

                binding.rvKandidat.adapter = KandidatAdapter(this@KandidatActivity, kandidat, id)
            }

            override fun onFailure(call: Call<ModelResultData>, t: Throwable) {
                progressDialog.dismiss()
                Log.d("Ica-Error", t.message.toString())
                Toast.makeText(this@KandidatActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}