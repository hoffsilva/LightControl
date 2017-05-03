package com.example.hoffsilva.lightcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GardenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);

        final Button setor1 = (Button) findViewById(R.id.setor1);
        setor1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    setBgButton(setor1);
                }
        });
    }

    public void setBgButton(Button botao) {
        botao.setBackgroundResource(R.drawable.light_on);
    }
}
