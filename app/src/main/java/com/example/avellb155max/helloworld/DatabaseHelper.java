package com.example.avellb155max.helloworld;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Avell B155 MAX on 07/06/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "SaverMoney";
    private static int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE gasto(_id INTEGER PRIMARY KEY," +
                "data DATE not null,valor INTEGER not null," +
                "descricao TEXT not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("ALTER TABLE gasto");

    }
}