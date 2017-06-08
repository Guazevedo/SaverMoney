package com.example.avellb155max.helloworld;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class Excluir extends AppCompatActivity {

    private int ano,mes,dia;
    private Button dataExt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);
        Calendar calendar= Calendar.getInstance();
        ano=calendar.get(Calendar.YEAR);
        mes= calendar.get(Calendar.MONTH);
        dia=calendar.get(Calendar.DAY_OF_MONTH);

        dataExt=(Button)findViewById(R.id.btnDataExt);
        dataExt.setText(dia +"/"+(mes+1)+"/"+ano);

    }
    public void selecionarData(View view){
        showDialog(view.getId());
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(R.id.btnDataExt==id){

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
                    dataExt.setText(dia+"/"+(mes+1)+"/"+ano);
                }
            };



}
