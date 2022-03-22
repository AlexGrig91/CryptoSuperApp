package ru.startandroid.develop.cryptosuperapp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.startandroid.develop.cryptosuperapp.pojo.CoinInfoListOfData
import ru.startandroid.develop.cryptosuperapp.pojo.CoinPriceInfo
import ru.startandroid.develop.cryptosuperapp.pojo.CoinPriceInfoRawData

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey:String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
    ): Single<CoinInfoListOfData>


    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey:String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSums:String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSums:String = CURRENCY,
    ): Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}