package com.example.hoffsilva.lightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BedRoomActivity extends AppCompatActivity {

    Boolean setor1isOn = false;
    private static final String host = null;
    private int port;
    String str= "CP02S1";
    /** Called when the activity is first created. */
    byte[] send_data = new byte[50];
    byte[] receiveData = new byte[50];
    String modifiedSentence;
    /*
    *
    ******************COMANDOS PARA PLACA DE 8 SETORES
CP02S1 –> RELÉ OU LAMPADA 1
CP02S2 –> RELÉ OU LAMPADA 2
CP02S3 –> RELÉ OU LAMPADA 3
CP02S4 –> RELÉ OU LAMPADA 4
CP02S5 –> RELÉ OU LAMPADA 5
CP02S6 –> RELÉ OU LAMPADA 6
CP02S7 –> RELÉ OU LAMPADA 7
CP02S8 –> RELÉ OU LAMPADA 8
CP02S0 –> TODOS RELÉS OU LAMPADAS

Obs:COM O CHECK DESMARCADO(PADRAO)
CP02C1 -> ACIONA CENA 1
CP02C2 -> ACIONA CENA 2
CP02C3 -> ACIONA CENA 3
CP02C4 -> ACIONA CENA 4
CP02C5 -> ACIONA CENA 5
CP02C6 -> ACIONA CENA 6
CP02C7 -> ACIONA CENA 7
CP02C8 -> ACIONA CENA 8
CP02C9 -> ACIONA CENA 9

Obs:COM O CHECK MARCADO
AP02C1 -> CONFIGURA CENA 1
AP02C2 -> CONFIGURA CENA 2
AP02C3 -> CONFIGURA CENA 3
AP02C4 -> CONFIGURA CENA 4
AP02C5 -> CONFIGURA CENA 5
AP02C6 -> CONFIGURA CENA 6
AP02C7 -> CONFIGURA CENA 7
AP02C8 -> CONFIGURA CENA 8
AP02C9 -> CONFIGURA CENA 9
    */

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
        setContentView(R.layout.activity_bed_room);
        //createButtons();

    }



    public void createButtons() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Button setor1 = (Button) findViewById(R.id.setor1);
        setor1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor1txt);
                    if (!modSentence.isEmpty()) {
                        setBgButton(setor1);
                    }
                    createAlert(modSentence, "Alerta");
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
                    if (!modSentence.isEmpty()) {
                        setBgButton(setor1);
                    }
                    createAlert(modSentence, "Alerta");
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
                    if (!modSentence.isEmpty()) {
                        setBgButton(setor1);
                    }
                    createAlert(modSentence, "Alerta");
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
                    if (!modSentence.isEmpty()) {
                        setBgButton(setor1);
                    }
                    createAlert(modSentence, "Alerta");
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
                    if (!modSentence.isEmpty()) {
                        setBgButton(setor1);
                    }
                    createAlert(modSentence, "Alerta");
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
                    if (!modSentence.isEmpty()) {
                        setBgButton(setor1);
                    }
                    createAlert(modSentence, "Alerta");
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
                    if (!modSentence.isEmpty()) {
                        setBgButton(setor1);
                    }
                    createAlert(modSentence, "Alerta");
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });
    }

    public void createAlert(String message, String title){
        AlertDialog alertDialog = new AlertDialog.Builder(BedRoomActivity.this).create();
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
