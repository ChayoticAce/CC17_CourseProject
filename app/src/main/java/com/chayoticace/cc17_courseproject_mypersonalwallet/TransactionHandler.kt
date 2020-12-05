package com.chayoticace.cc17_courseproject_mypersonalwallet

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TransactionHandler(var context: Context):SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "account_database"
        private val TABLE_NAME = "balance"
        private val COL_ID = "id"
        private val COL_DEPOSIT = "deposit"
        private val COL_WIDTHRAWL = "widthrawl"
        private val COL_BALANCE = "balance"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        //define the query
        val query =
            "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_DEPOSIT DECIMAL(10,2), $COL_WIDTHRAWL DECIMAL(10,2), $COL_BALANCE DECIMAL(10,2))"
        //execute the query
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun create(account: com.chayoticace.cc17_courseproject_mypersonalwallet.models.Account): Boolean {
        //get the database
        val database = this.writableDatabase

        //set the contentvalues
        val contentValues = ContentValues()

        //deposit
        val convertedDeposit = account.deposit
        contentValues.put(COL_DEPOSIT, convertedDeposit)

        //widthdrawl
        val convertedWidthrawl = account.widthrawl
        contentValues.put(COL_WIDTHRAWL, convertedWidthrawl)

        //insert
        val result = database.insert(TABLE_NAME, null, contentValues)

        //check for result
        if (result == (0).toLong()) {
            return true
        }
        return false
    }

    fun balance_amount(): Double {
        val database = this.writableDatabase

        if (depositTotal() > WidthrawlTotal()){
            return depositTotal() - WidthrawlTotal()
        }else{
            return WidthrawlTotal() - depositTotal()
        }
    }

    fun depositTotal(): Double {
        val database = this.writableDatabase

        var total = 0.0
        val cursor = database.rawQuery("SELECT SUM($COL_DEPOSIT) FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            total = cursor.getInt(0).toDouble()
        }

        while (cursor.moveToNext()){return total}
        return total
    }

    fun WidthrawlTotal(): Double {
        val database = this.writableDatabase

        var total = 0.0
        val cursor = database.rawQuery("SELECT SUM($COL_WIDTHRAWL) FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            total = cursor.getInt(0).toDouble()
        }

        while (cursor.moveToNext()){return total}
        return total

    }

}