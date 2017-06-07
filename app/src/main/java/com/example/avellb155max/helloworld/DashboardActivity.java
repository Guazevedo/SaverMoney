package com.example.avellb155max.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void novoGasto(View view){
            startActivity(new Intent(this,NovoGasto.class));
        }
    public void consulta(View view){
        startActivity(new Intent(this,Consulta.class));
    }
    public void excluir(View view){
        startActivity(new Intent(this,excluir.class));
    }


}
