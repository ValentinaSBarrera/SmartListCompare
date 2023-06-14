package com.valentinasbarrera.smartlistcompare.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pacopulido on 23/02/2021.
 */

object WebAccess {

    val smartListService : SmartListService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://40.117.60.192:8000/apismartlist/")
            .build()

        return@lazy retrofit.create(SmartListService::class.java)
    }
}
