package com.example.sportshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.activity_order.*

class checkOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        //pembuatan variabel intent pemanggilan ke class order
        var intent = intent

        val aProduct = intent.getStringExtra("name")
        val aPrice = intent.getIntExtra("price", 0)
        val aQty = intent.getIntExtra("qty", 0)
        val aTotal = intent.getIntExtra("tot", 0)

        actionBar.setTitle("Pembayaran" + aProduct)

        productName.text = aProduct
        priceProduct.text = aPrice.toString()
        qty.text = aQty.toString()
        total.text = aTotal.toString()


        button.setOnClickListener {
            //pembuatan variabel intent untuk ke class
            val i = Intent(this, struk::class.java)
            i.putExtra("name", aProduct)
            i.putExtra("price", aPrice.toString().toInt())
            i.putExtra("qty", qty.text.toString().toInt())
            i.putExtra("tot", total.text.toString().toInt())
            i.putExtra("nm", editName.text.toString())
            i.putExtra("nohp", editNo.text.toString())
            i.putExtra("alamat", editAlamat.text.toString())

            startActivity(i)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}