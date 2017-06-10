package com.example.avellb155max.helloworld;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Consulta extends ListActivity{


    private List<Map<String, Object>> gasto;
    private AlertDialog alertDialog;
    private SimpleDateFormat dateFormat;
    private DatabaseHelper helper;
    private int valor;
    private String obs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_consulta);
        helper = new DatabaseHelper(this);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    }



    private AlertDialog criaAlertDialog(){
        final CharSequence[] items = {getString(R.string.registro_salvo)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("opcoes");
        builder.setItems(items, (DialogInterface.OnClickListener)this);
        return builder.create();
    }
    private AlertDialog criarDialogConfirmacao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.registro_salvo);
        builder.setPositiveButton(getString(R.string.registro_salvo),(DialogInterface.OnClickListener)this);
        builder.setNegativeButton(getString(R.string.erro_salvar),(DialogInterface.OnClickListener)this);
        return builder.create();
    }

    private List<Map<String,Object>> listargasto(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT_id, gasto, data, observacao",null);
        cursor.moveToFirst();

        gasto = new ArrayList<Map<String, Object>>();

        for(int i=0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();
            String id = cursor.getString(0);
            int valor = cursor.getInt(1);
            long data = cursor.getLong(2);
            String obs = cursor.getString(3);
            item.put("id",id);
            gasto.add(item);
            cursor.moveToNext();
        }
        cursor.close();

        return gasto;
    }




}
