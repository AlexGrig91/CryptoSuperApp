package ru.startandroid.develop.cryptosuperapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.startandroid.develop.cryptosuperapp.R
import ru.startandroid.develop.cryptosuperapp.pojo.CoinInfo
import ru.startandroid.develop.cryptosuperapp.pojo.CoinPriceInfo

class CoinInfoAdapter(private val context: Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var coinInfoList: List<CoinPriceInfo> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info,parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price
                tvLastUpdate.text = String.format(lastUpdateTemplate,getFormattedTime())
                Picasso.get().load(getFullImageUrl()).into(ivLogoCoin)
                itemView.setOnClickListener{
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }

    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivLogoCoin:ImageView = itemView.findViewById(R.id.ivLogoCoin)
        val tvSymbols:TextView = itemView.findViewById(R.id.tvSymbols)
        val tvPrice:TextView = itemView.findViewById(R.id.tvPrice)
        val tvLastUpdate:TextView = itemView.findViewById(R.id.tvLastUpdate)
    }

    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}