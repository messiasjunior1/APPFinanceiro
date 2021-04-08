package com.utfpr.APPFinanceiro.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.utfpr.APPFinanceiro.helper.DBHelper;
import com.utfpr.APPFinanceiro.model.contract.BalanceContract;
import com.utfpr.APPFinanceiro.model.entity.Balance;

public class BalanceDAO implements IBalanceDAO {

    SQLiteDatabase writer;
    SQLiteDatabase reader;

    public BalanceDAO(Context context) {
        DBHelper db = new DBHelper(context);
        writer = db.getWritableDatabase();
        reader = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Balance c) {

        ContentValues cv = new ContentValues();
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_DESC, c.getDesc());
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_VALUE, c.getValue());
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_DATE, c.getDate());
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_TYPE, c.getTypeBalance());

        try {
            writer.insert(BalanceContract.BalanceEntry.TABLE_NAME, null, cv);
            Log.i("DB INFO", "Registro salvo com sucesso");
        }catch (Exception e){
            Log.e("DB ERROR", "Erro ao salvar registro" + e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public boolean atualizar(Balance c) {

        ContentValues cv = new ContentValues();
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_DESC, c.getDesc());
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_VALUE, c.getValue());
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_DATE, c.getDate());
        cv.put(BalanceContract.BalanceEntry.COLUMN_BALANCE_TYPE, c.getTypeBalance());


        try {
            String[] args = {c.getId().toString()};
            writer.update(BalanceContract.BalanceEntry.TABLE_NAME, cv, BalanceContract.BalanceEntry._ID+"=?", args);
            Log.i("DB INFO", "Registro atualizado com sucesso");
        }catch (Exception e){
            Log.e("DB ERROR", "Erro ao atualizar registro" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remover(Balance c) {
        try {
            String[] args = {c.getId().toString()};
            writer.delete(BalanceContract.BalanceEntry.TABLE_NAME, BalanceContract.BalanceEntry._ID+"=?", args);
            Log.i("DB INFO", "Registro deletado com sucesso");
        }catch (Exception e){
            Log.e("DB ERROR", "Erro ao deletar registro" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Balance> listar() {

        List<Balance> balances = new ArrayList<>();

        String sql = "SELECT * FROM " + BalanceContract.BalanceEntry.TABLE_NAME + " ;";
        Cursor cursor = reader.rawQuery(sql, null);

        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndex(BalanceContract.BalanceEntry._ID));
            String value = cursor.getString(cursor.getColumnIndex(BalanceContract.BalanceEntry.COLUMN_BALANCE_VALUE));
            String desc = cursor.getString(cursor.getColumnIndex(BalanceContract.BalanceEntry.COLUMN_BALANCE_DESC));
            String date = cursor.getString(cursor.getColumnIndex(BalanceContract.BalanceEntry.COLUMN_BALANCE_DATE));
            String type = cursor.getString(cursor.getColumnIndex(BalanceContract.BalanceEntry.COLUMN_BALANCE_TYPE));


            Balance c = new Balance(desc, value, date, type);
            c.setId(id);

            balances.add(c);
            Log.i("BalanceDAO", c.getDesc());
        }

        return balances;
    }
}
