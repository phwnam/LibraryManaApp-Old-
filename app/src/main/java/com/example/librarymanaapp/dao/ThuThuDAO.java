package com.example.librarymanaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.librarymanaapp.database.DbHelper;
import com.example.librarymanaapp.model.ThanhVien;
import com.example.librarymanaapp.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;
    public ThuThuDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert (ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("hoTen",obj.hoTen);
        values.put("matKhau",obj.matKhau);

        return db.insert("ThuThu", null, values);
    }

    public int update(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("hoTen",obj.hoTen);
        values.put("matKhau",obj.matKhau);

        return db.update("ThuThu", values, "maTT=?", new String[]{String.valueOf(obj.maTT)});
    }

    public int delete(String id){
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    //get All data
    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getAll();
    }

    //get data theo id
    public ThuThu getID(String id){
        String sql ="SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }

    //get data nhiều tham số
    private List<ThuThu> getData(String sql, String...selectionArgs) {
        ArrayList<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu obj = new ThuThu();
            obj.maTT = c.getString(c.getColumnIndex("maTV"));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.matKhau = c.getString(c.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }
}
