package com.example.hoffsilva.lightcontrol;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class LogActivity extends AppCompatActivity {

    Boolean setor1isOn = false;
    private static final String host = null;
    private int port;
    String str= "SC00LF";
    /** Called when the activity is first created. */
    byte[] send_data = new byte[50];
    byte[] receiveData = new byte[1024];
    String modifiedSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        TextView status = (TextView) findViewById(R.id.textViewLog);
        try {
            String texto = client(str);
            //texto.replace("010-", "Falha de comnunicação ");
            texto = texto.replace("100-", "Comnunicação desabilitada ");

            //texto.replace("150-", "Bateria Fraca ");
            //texto.replace("151-", "Bateria muito fraca ");
            //texto.replace("152-", "Tensão fonte alta ");
            //texto.replace("153-", "Tensão fonte baixa ");
            //texto.replace("154-", "Tensão regulador alta ");
            //texto.replace("155-", "Tensao regulador baixa ");
           // texto.replace("156-", "Temp alta dissipador ");
            //texto.replace("157-", "Temp alta central ");
            //texto.replace("200-", "Endereço não encontrado ");
            status.setText(client(str));
        } catch (IOException e) {
            status.setText(e.getMessage());
            e.printStackTrace();
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
