package com.mazrou.toDoApp.framework.datasource.network.tickers.api

import com.mazrou.toDoApp.framework.datasource.network.tickers.models.StockDetailDto
import com.mazrou.toDoApp.framework.datasource.network.tickers.models.StockDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TiingoWebService {

    @GET("iex/")
    suspend fun getStocksList(
        @Query("tickers") tickers: String,
        @Query("token") token: String = "140e943174d37dce53a78dfd460b696d69fc244b"
    ): List<StockDto>

    //https://api.tiingo.com/iex/<ticker>/prices?startDate=2019-01-02&resampleFreq=5min

    @GET("iex/{ticker}/prices")
    suspend fun getStockDetail(
        @Path("ticker") tickers: String,
        @Query("startDate") startDate: String ,
        @Query("resampleFreq") resampleFreq: String = "1hour",
        @Query("token") token: String = "140e943174d37dce53a78dfd460b696d69fc244b"
    ): List<StockDetailDto>
}