package com.example.venten
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venten.helper.FilterAdapter
import com.example.venten.model.Filter
import com.example.venten.model.FilterItem
import com.example.venten.service.FilterService
import com.example.venten.service.ServiceBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFilters()


}








    private fun loadFilters() {
        //initiate the service
        val destinationService  = ServiceBuilder.buildService(FilterService::class.java)
        val requestCall =destinationService.getFilters()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<Filter> {
            override fun onResponse(call: Call<Filter>, response: Response<Filter>) {
                Log.d("Response", "onResponse: ${response.body()}")
                d("Response", "onResponse: ${response.isSuccessful}")
                d("Response", "onResponse: ${response.message()} ${response.code()}")
                if (response.isSuccessful){
                    val filterList  = response.body()!!
                    Log.d("Response", "countrylist size : ${filterList.size}")
                    val adapter = FilterAdapter(response.body()!!)
                    main_list.adapter = adapter
                        adapter.onItemClick= { filter ->


                        Log.d("TAG", "")
                    }
                    main_list.layoutManager = LinearLayoutManager(this@MainActivity)
                    main_list.setHasFixedSize(true)
                }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Filter>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_LONG).show()
                Log.d("Response", "Error : ${t}")
            }
        })
    }




}


