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

public class Consulta extends AppCompatActivity {

    private int ano,mes,dia;
    private Button data;
    private List<Map<String, Object>> gasto;
    private AlertDialog alertDialog;
    private SimpleDateFormat dateFormat;
    private DatabaseHelper helper;
    private int valor;
    private String obs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        Calendar calendar= Calendar.getInstance();
        ano=calendar.get(Calendar.YEAR);
        mes= calendar.get(Calendar.MONTH);
        dia=calendar.get(Calendar.DAY_OF_MONTH);

        data=(Button)findViewById(R.id.btnData);
        data.setText(dia +"/"+(mes+1)+"/"+ano);

        helper = new DatabaseHelper(this);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    }

    public void selecionarData(View view){
        showDialog(view.getId());
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(R.id.btnData==id){

            return new DatePickerDialog(this,listener,ano,mes,dia);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener listener=new
            DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth ){
                    ano=year;
                    mes=month;
                    dia=dayOfMonth;
                    data.setText(dia+"/"+(mes+1)+"/"+ano);
                }
            };

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
