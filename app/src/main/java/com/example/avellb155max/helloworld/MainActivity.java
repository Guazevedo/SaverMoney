package com.example.avellb155max.helloworld;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        usuario= (EditText) findViewById(R.id.login);
        senha= (EditText) findViewById(R.id.senha);
        /*botao futuro para cadastro no sistema
        Button submit= (Button)  findViewById(R.id.btnLogin);*/

    }

    public void entraOnClick(View view){
        String usuarioInf =usuario.getText().toString();
        String senhaInf =senha.getText().toString();

        if("adm".equals(usuarioInf)&&"123".equals(senhaInf)){
            startActivity(new Intent(this,DashboardActivity.class));
        } else {

            String mensagemErro= getString(R.string.erroAut);
            Toast toast= Toast.makeText(this,mensagemErro,Toast.LENGTH_SHORT);
            toast.show();
        }

    }


}
