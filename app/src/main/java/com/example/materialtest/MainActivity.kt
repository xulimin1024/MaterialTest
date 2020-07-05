package com.example.materialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple), Fruit("Banana", R.drawable.banana), Fruit("Orange", R.drawable.orange), Fruit("Watermelon", R.drawable.watermelon), Fruit("Pear", R.drawable.pear), Fruit("Grape", R.drawable.grape), Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry", R.drawable.strawberry), Fruit("Cherry", R.drawable.cherry), Fruit("Mango", R.drawable.mango))

    val fruitList = ArrayList<Fruit>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        //默认选中这个
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
               R.id.navCall-> Toast.makeText(this,"navCall",Toast.LENGTH_SHORT).show()
                R.id.navFriends-> Toast.makeText(this,"navFriends",Toast.LENGTH_SHORT).show()

                R.id.navLocation-> Toast.makeText(this,"navLocation",Toast.LENGTH_SHORT).show()
                R.id.navMail-> Toast.makeText(this,"navMail",Toast.LENGTH_SHORT).show()


            }

            drawerLayout.closeDrawers()
            true
        }

        fab.setOnClickListener {
           //
            Snackbar.make(it,"Data deleted",Snackbar.LENGTH_SHORT)
                .setAction("Undo"){
                    Toast.makeText(this,"date restored",Toast.LENGTH_SHORT).show()
                }.show()
        }


        initFruits()
        val layoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(this,fruitList)
        recyclerView.adapter =adapter


    }
    //构建菜单
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.backup->Toast.makeText(this,"BackUp",Toast.LENGTH_SHORT).show()
            R.id.delete->Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show()
            R.id.setting->Toast.makeText(this,"setting",Toast.LENGTH_SHORT).show()
            android.R.id.home->drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    private fun initFruits(){
        fruitList.clear()
        repeat(50){
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }
}