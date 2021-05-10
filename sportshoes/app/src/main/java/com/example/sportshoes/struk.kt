package com.example.sportshoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_struk.*

class struk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_struk)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)


        //pembuatan variabel intent pemanggilan ke class order
        var intent = intent

        val aProduct = intent.getStringExtra("name")
        val aPrice = intent.getIntExtra("price", 0)
        val aQty = intent.getIntExtra("qty", 0)
        val aTot = intent.getIntExtra("tot", 0)
        val anm = intent.getStringExtra("nm")
        val anohp = intent.getStringExtra("nohp")
        val aalmt = intent.getStringExtra("alamat")

        actionBar.setTitle("Pembayaran" + aProduct)

        textnm.text = aProduct
        txtprice.text = aPrice.toString()
        txtqty.text = aQty.toString()
        txttot.text = aTot.toString()
        nmcust.text = anm
        nocust.text = anohp.toString()
        almtcust.text = aalmt

    }
        override fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }
}