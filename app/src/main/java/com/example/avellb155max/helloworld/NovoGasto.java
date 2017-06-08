package com.example.avellb155max.helloworld;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class NovoGasto extends AppCompatActivity {
    private int ano,mes,dia;
    private Button dataGasto;
    private EditText valor,obs;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_gasto);

        Calendar calendar= Calendar.getInstance();
        ano=calendar.get(Calendar.YEAR);
        mes= calendar.get(Calendar.MONTH);
        dia=calendar.get(Calendar.DAY_OF_MONTH);

        dataGasto=(Button)findViewById(R.id.btnDataGasto);
        dataGasto.setText(dia +"/"+(mes+1)+"/"+ano);

        valor = (EditText) findViewById(R.id.valorTxt);
        obs = (EditText) findViewById(R.id.obsTxt);
        helper = new DatabaseHelper(this);
    }
    public void selecionarData(View view){
        showDialog(view.getId());
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(R.id.btnDataGasto==id){

            return new DatePickerDialog(this,listener,ano,mes,dia);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener listener=new
    DatePickerDialog.OnDateSetListener(){
        @Override
                public void onDateSet(DatePicker view,int year,int month,int dayOfMonth ){
            ano=year;
            mes=month;
            dia=dayOfMonth;
            dataGasto.setText(dia+"/"+(mes+1)+"/"+ano);
        }
    };

    public void salvarGasto (View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("valor",valor.getText().toString());
        values.put("data",dataGasto.getDrawingTime());
        values.put("descricao",obs.getText().toString());

        long resultado = db.insert("gasto", null, values);
        if(resultado != -1){
            Toast.makeText(this, getString(R.string.registro_salvo),
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, getString(R.string.erro_salvar),
                    Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

}
