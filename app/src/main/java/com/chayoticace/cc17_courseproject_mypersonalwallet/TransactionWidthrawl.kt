package com.chayoticace.cc17_courseproject_mypersonalwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.chayoticace.cc17_courseproject_mypersonalwallet.models.Account

class TransactionWidthrawl : AppCompatActivity() {
    lateinit var widthrawlAmountET:EditText
    lateinit var proceedbtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_widthrawl)

        val databaseHandler = TransactionHandler(this)
        widthrawlAmountET = findViewById(R.id.trans_widthrawl_amount)

        proceedbtn = findViewById(R.id.trans_widthrawl_btn)
        proceedbtn.setOnClickListener{
            //get the field from the form
            val amountWidthrawn = widthrawlAmountET.text.toString().toDouble()

            //assign it to a book model
            val account = Account(0.0, amountWidthrawn, 0.0)

            //save it to database
            if(databaseHandler.create(account)){
                Toast.makeText(applicationContext, "Amount was added", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
            clearFIelds()
            applyChanges()
        }
    }
    fun clearFIelds(){
        widthrawlAmountET.text.clear()
    }
    fun applyChanges(){
        val db = TransactionHandler(this)
        var balance_display = findViewById<TextView>(R.id.mainact_balance_display)
        val changedtobeapplied = db.balance_amount().toString()

        balance_display.text = changedtobeapplied
    }
}