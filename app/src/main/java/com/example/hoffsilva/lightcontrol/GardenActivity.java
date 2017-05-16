package com.example.hoffsilva.lightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GardenActivity extends AppCompatActivity {


    Boolean setor1isOn = false;
    private static final String host = null;
    private int port;
    String str= "CP02S1";
    /** Called when the activity is first created. */
    byte[] send_data = new byte[50];
    byte[] receiveData = new byte[50];
    String modifiedSentence;

    //Setores:
    /*
    * CPO2S1 CPO4S1 CPO2S2 CPO4S2 CPO2S3 CPO4S3 CPO2SA
    * */

    String setor1txt = "CP02S1";
    String setor2txt = "CP02S2";
    String setor3txt = "CP02S3";
    String setor4txt = "CP02S4";
    String setor5txt = "CP02S5";
    String setor6txt = "CP02S6";
    String setorAlltxt = "CP02S0";


    public Boolean getSetor1() {
        return setor1isOn;
    }

    public void setSetor1(Boolean setor1) {
        this.setor1isOn = setor1isOn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Button setor1 = (Button) findViewById(R.id.setor1);
        setor1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor1txt);
                    if (!modSentence.isEmpty()){
                        setBgButton(setor1);
                    }
                    createAlert(modSentence,"Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        final Button setor2 = (Button) findViewById(R.id.setor2);
        setor2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor2txt);
                    if (!modSentence.isEmpty()){
                        setBgButton(setor1);
                    }
                    createAlert(modSentence,"Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        final Button setor3 = (Button) findViewById(R.id.setor3);
        setor3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor3txt);
                    if (!modSentence.isEmpty()){
                        setBgButton(setor1);
                    }
                    createAlert(modSentence,"Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        final Button setor4 = (Button) findViewById(R.id.setor4);
        setor4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor4txt);
                    if (!modSentence.isEmpty()){
                        setBgButton(setor1);
                    }
                    createAlert(modSentence,"Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        final Button setor5 = (Button) findViewById(R.id.setor5);
        setor5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor5txt);
                    if (!modSentence.isEmpty()){
                        setBgButton(setor1);
                    }
                    createAlert(modSentence,"Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        final Button setor6 = (Button) findViewById(R.id.setor6);
        setor6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor6txt);
                    if (!modSentence.isEmpty()){
                        setBgButton(setor1);
                    }
                    createAlert(modSentence,"Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        final Button setorAll = (Button) findViewById(R.id.setor7);
        setorAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setorAlltxt);
                    if (!modSentence.isEmpty()){
                        setBgButton(setor1);
                    }
                    createAlert(modSentence,"Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });
    }

    public void createAlert(String message, String title){
        AlertDialog alertDialog = new AlertDialog.Builder(GardenActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void setBgButton(Button botao) {

        if (!setor1isOn) {
            botao.setBackgroundResource(R.drawable.light_on);
            setSetor1(true);
        } else {
            botao.setBackgroundResource(R.drawable.light_off);
            setSetor1(false);
        }

    }

    public String client(String setor) throws IOException {


        DatagramSocket client_socket = new DatagramSocket(10000);
        InetAddress IPAddress =  InetAddress.getByName("192.168.0.200");

        //while (true)
        // {
        send_data = setor.getBytes();

        //System.out.println("Type Something (q or Q to quit): ");

        DatagramPacket send_packet = new DatagramPacket(send_data,str.length(), IPAddress, 10000);
        client_socket.send(send_packet);
        //chandra
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client_socket.receive(receivePacket);
        modifiedSentence = new String(receivePacket.getData());



            client_socket.disconnect();
        client_socket.close();


        return  modifiedSentence;
        // }

    }
}
