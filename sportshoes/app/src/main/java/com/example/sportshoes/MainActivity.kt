package com.example.sportshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // initialise the DrawerLayer, NavigationView and ToogleBar

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navDrawerView: NavigationView

    private  lateinit var bottomNavigationView: BottomNavigationView
    var  myAdapter : productAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start Product Item
        val arrayItem = ArrayList<productModel>()
        arrayItem.add(productModel("Adidas","Adidas Cocok Sekali Untuk Dibuat Sepatu Olahraga",R.drawable.adidas,250000))
        arrayItem.add(productModel("Brodo","Sepatu ganteng untuk urusan formal dan penting",R.drawable.brodo,350000))
        arrayItem.add(productModel("Convers"," model sepatu Chuck Taylor menjadi kiblat sepatu casual ",R.drawable.convers,275000))
        arrayItem.add(productModel("Diadora"," Sepatu Diadora Running Original",R.drawable.diadora,200000))
        arrayItem.add(productModel("League","sepatu league legas running seri legas tracer LA original ",R.drawable.league,150000))
        arrayItem.add(productModel("Puma","Puma .....",R.drawable.puma,225000))
        arrayItem.add(productModel("Rebork","Rebork......",R.drawable.recook,450000))
        arrayItem.add(productModel("New Balance","New Balance......",R.drawable.newbalance,350000))
        arrayItem.add(productModel("Vans","Vans .....",R.drawable.vans,550000))
        arrayItem.add(productModel("Wakai","Wakai.....",R.drawable.wakai,125000))

        myAdapter = productAdapter(this)
        myAdapter!!.setData(arrayItem)

        product_RecyclerView.layoutManager  = LinearLayoutManager(this)
        product_RecyclerView.adapter        = myAdapter

        bottomNavigationView = findViewById(R.id.navBottom)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "Go To ", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }

        // Call findViewById on the Drawerlayout
        drawerLayout = findViewById(R.id.drawer)

        // pass the ActionBarTooggle action into the drawerlistener
        actionBarToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            0,
            0
        )

        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // call syncState() on the action bar it"ll automatically change
        // to back button when the drawer layout is open
        actionBarToggle.syncState()

        // cal findViewById on the navigationview
        navDrawerView = findViewById(R.id.navDrawer)

        //call setNavigationItemSelectedListener on the Navigation to
        // detect when items are clicked
        navDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myProfile -> {
                    val intent = Intent(applicationContext, Profile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.myContact -> {
                    Toast.makeText(
                        this,
                        "Go To My Contact",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.MyHelp -> {
                    Toast.makeText(
                        this,
                        "Go To Help",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                else -> {
                    false

                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            this.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.toollbar_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        if (searchItem != null){
            val searchView = searchItem.actionView  as SearchView
            searchView.maxWidth = Int.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
                }

                override fun onQueryTextChange(filterString: String?): Boolean {
                    myAdapter!!.filter.filter(filterString)
                    return true
                }
            })
        }
            return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.shopping){ Toast.makeText(this, "View Shopping Chart", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (id == R.id.account){
            Toast.makeText(
                this, "account clicked", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (id == R.id.logout) {
            val intent = Intent( this, login::class.java)
            startActivity(intent)
            Toast.makeText(
                this, "Logout clicked", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}



