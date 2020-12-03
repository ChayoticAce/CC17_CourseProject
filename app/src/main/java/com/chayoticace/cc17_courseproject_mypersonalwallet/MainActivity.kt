package com.chayoticace.cc17_courseproject_mypersonalwallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.displaybalance -> {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                true
            }
            R.id.widthrawl -> {
                startActivity(Intent(applicationContext, TransactionWidthrawl::class.java))

                true
            }
            R.id.deposit -> {
                startActivity(Intent(applicationContext, TransactionDeposit::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}