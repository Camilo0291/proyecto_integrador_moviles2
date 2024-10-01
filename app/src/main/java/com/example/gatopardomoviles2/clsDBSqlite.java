package com.example.gatopardomoviles2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class clsDBSqlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";

    public clsDBSqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Método para registrar un usuario
    public boolean insertUser(String username, String email, String password) {  // Agregamos el parámetro 'email'
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, username);
        contentValues.put(COL_2, email);  // Corregimos esta línea
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Si el resultado no es -1, la inserción fue exitosa
    }

    // Método para verificar las credenciales del usuario
    public boolean checkUser(String username, String password) {  // Solo necesitamos username y password
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?",
                new String[]{username, password});  // Eliminamos 'email' de la consulta
        return cursor.getCount() > 0; // Si hay un resultado, el usuario existe con esas credenciales
    }
}
