package com.example.hoffsilva.lightcontrol;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StatusActivity extends AppCompatActivity {


    /*
    * *********Comando central
SC00TM -> SOLICITA HORARIO DA CENTRAL -> FORMATO RESPOSTA = HH:MM:SS DD/MM/AA-W
AC00TMHMSWDMA -> AJUSTA HORARIO -> FORMATO RESPOSTA = HH:MM:SS DD/MM/AA-W
SC00AD ->SOLICITA LEITURA ANALOGICA -> FORMATO RESPOSTA = 00.0V 00.0V 00.0V 00.0ºC 00.0ºC 000.0
SC00LF - > SOLICITA LOG DE FALHAS -> FORMATO RESPOSTA = (COD:ADD:STATUS-HH:MM-DD/MM*)TRINTA VEZES

 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
    }

}
