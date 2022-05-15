package com.example.romankolindatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper
{

    public Database(@Nullable Context cont, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory fact, int ver)
    {
        super(cont, name, fact, ver);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int nv, int ov)
    {

    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB)
    {
        String db = "CREATE TABLE Databaseapp (k TEXT, v TEXT)";
        sqlDB.execSQL(db);
    }

    public String select(String key)
    {
        String sel = "SELECT v FROM Databaseapp WHERE k = '" + key + "';";
        SQLiteDatabase sqlDB = getReadableDatabase();
        @SuppressLint("Recycle") Cursor curs = sqlDB.rawQuery(sel, null);
        if (curs.moveToFirst())
            return curs.getString(0);
        return  "This entry doesn't exists";
    }

    public void insert(String key, String value)
    {
        String ins = "INSERT INTO Databaseapp VALUES ('" + key + "', '" + value + "');";
        SQLiteDatabase sqlDB = getWritableDatabase();
        sqlDB.execSQL(ins);
    }

    public void update(String key, String value)
    {
        String upd = "UPDATE Databaseapp SET v = '" + value + "' WHERE k = '" + key + "';";
        SQLiteDatabase sqlDB = getWritableDatabase();
        sqlDB.execSQL(upd);
    }

    public void delete(String key)
    {
        String del = "DELETE FROM Databaseapp WHERE k = '" + key + "';";
        SQLiteDatabase sqlDB = getWritableDatabase();
        sqlDB.execSQL(del);
    }
}
