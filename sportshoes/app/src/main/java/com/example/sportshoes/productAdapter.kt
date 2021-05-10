package com.example.sportshoes

import android.content.Context
import android.content.Intent
import android.graphics.PointF.length
import android.opengl.Matrix.length
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_product.view.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import java.security.AccessControlContext
import java.util.*
import kotlin.collections.ArrayList

class productAdapter (val context: Context) :
    RecyclerView.Adapter<productAdapter.ViewHolder>(), Filterable {

    var arrayList = ArrayList<productModel>()
    var ProductListFilter = ArrayList<productModel>()

    fun setData(arrayList: ArrayList<productModel>) {
        this.arrayList = arrayList
        this.ProductListFilter = arrayList
        notifyDataSetChanged()

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(model: productModel) {
            itemView.productName.text = model.nmProduct
            itemView.descProduct.text = model.dsProduct
            itemView.priceProduct.text = model.priceofProduct.toString()
            itemView.imgProduct.setImageResource(model.imgProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_product, parent, false)
        return productAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener() {
            val model = arrayList.get(position)

            var gProduct        : String = model.nmProduct
            var gDesc           : String = model.dsProduct
            var gPrice          : Int    = model.priceofProduct.toString().toInt()
            var gImg            : Int    = model.imgProduct

            val intent = Intent(context, Order::class.java)
            intent.putExtra("pProduct", gProduct)
            intent.putExtra("pDesc", gDesc)
            intent.putExtra("pPrice", gPrice)
            intent.putExtra("pImg", gImg)

            context.startActivity(intent)

        }
    }


    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (charSequence == null || charSequence.length < 0) {
                    filterResults.count = ProductListFilter.size
                    filterResults.values = ProductListFilter
                } else {
                    var searchChr = charSequence.toString()
                    val productSearch = ArrayList<productModel>()
                    for (item in ProductListFilter) {
                        if (item.nmProduct.toLowerCase().contains(searchChr) || item.dsProduct.toLowerCase().contains(searchChr)) {
                            productSearch.add(item)
                        }
                    }
                    filterResults.count = productSearch.size
                    filterResults.values = productSearch
                }
                return filterResults

            }

            override fun publishResults(p0: CharSequence, filterResults: FilterResults?) {
                arrayList = filterResults!!.values as ArrayList<productModel>
                notifyDataSetChanged()
            }
        }
    }
}
