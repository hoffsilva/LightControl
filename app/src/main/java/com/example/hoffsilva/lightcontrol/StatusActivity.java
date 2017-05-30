package com.example.hoffsilva.lightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    private String m_Text = "";
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


    public void createAlert(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");


        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
