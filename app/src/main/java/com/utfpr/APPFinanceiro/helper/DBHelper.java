package com.utfpr.APPFinanceiro.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.utfpr.APPFinanceiro.model.contract.BalanceContract;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 10;
    public static final String DATABASE_NAME = "Balance.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BalanceContract.BalanceEntry.TABLE_NAME + " (" +
                    BalanceContract.BalanceEntry._ID + " INTEGER PRIMARY KEY," +
                    BalanceContract.BalanceEntry.COLUMN_BALANCE_DESC + " TEXT," +
                    BalanceContract.BalanceEntry.COLUMN_BALANCE_VALUE + " TEXT," +
                    BalanceContract.BalanceEntry.COLUMN_BALANCE_DATE + " TEXT," +
                    BalanceContract.BalanceEntry.COLUMN_BALANCE_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BalanceContract.BalanceEntry.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            Log.i("DB INFO", "Sucesso ao criar tabela");
            db.execSQL(SQL_CREATE_ENTRIES);
        } catch (Exception e){
            Log.i("DB INFO", "Erro ao criar tabela");
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        try {
            Log.i("DB INFO", "Sucesso ao atualizar tabela");
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        } catch (Exception e){
            Log.i("DB INFO", "Erro ao atualizar tabela");
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
