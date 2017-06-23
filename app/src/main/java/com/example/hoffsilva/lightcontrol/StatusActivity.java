package com.example.hoffsilva.lightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class StatusActivity extends AppCompatActivity {

    private String m_Text = "";
    Timer timer = new Timer();

    byte[] send_data = new byte[50];
    byte[] receiveData = new byte[50];
    String modifiedSentence;
    /*
    * *********Comando central
SC00TM -> SOLICITA HORARIO DA CENTRAL -> FORMATO RESPOSTA = HH:MM:SS DD/MM/AA-W
AC00TMHMSWDMA -> AJUSTA HORARIO -> FORMATO RESPOSTA = HH:MM:SS DD/MM/AA-W
SC00AD ->SOLICITA LEITURA ANALOGICA -> FORMATO RESPOSTA = 00.0V 00.0V 00.0V 00.0ºC 00.0ºC 000.0
SC00LF - > SOLICITA LOG DE FALHAS -> FORMATO RESPOSTA = (COD:ADD:STATUS-HH:MM-DD/MM*)TRINTA VEZES

 */

    TextView tvVoltage;
    TextView tvHour;
    TextView tvDate;
    TextView tvTemperature;
    TextView tvCoolerSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView tv_voltage = (TextView) findViewById(R.id.tv_voltage);
        tvVoltage = tv_voltage;

        TextView tv_hour = (TextView) findViewById(R.id.hour);
        tvHour = tv_hour;

        TextView tv_date = (TextView) findViewById(R.id.tv_date);
        tvDate = tv_date;

        TextView tv_temperature = (TextView) findViewById(R.id.tv_temperature);
        tvTemperature = tv_temperature;

        TextView tv_cooler_speed = (TextView) findViewById(R.id.tv_cooler_speed);
        tvCoolerSpeed = tv_cooler_speed;

        Button bt_hour = (Button) findViewById(R.id.adjust_hour);

        bt_hour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                String hour = ""+c.get(Calendar.HOUR);
                if (hour.length() != 2) {
                    hour = "0" + hour;
                }
                String min = ""+c.get(Calendar.MINUTE);
                if (min.length() != 2) {
                    min = "0" + min;
                }
                String seconds = ""+c.get(Calendar.SECOND);
                if (seconds.length() != 2) {
                    seconds = "0" + seconds;
                }
                String day = ""+c.get(Calendar.DAY_OF_WEEK);
                if (day.length() != 2) {
                    day = "0" + day;
                }
                String dateDay = ""+c.get(Calendar.DAY_OF_MONTH);
                if (dateDay.length() != 2) {
                    dateDay = "0" + dateDay;
                }
                int dateMonth = c.get(Calendar.MONTH);
                dateMonth += 1;
                String month = ""+dateMonth;
                if (month.length() != 2) {
                    month = "0" + month;
                }
                int year = c.get(Calendar.YEAR)-2000;
                String dateYear = ""+year;

                String fullDate = hour + min + seconds +  day + dateDay + month + dateYear;

                setTime(fullDate);
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checking();
            }
        }, 0 ,5000);
    }

    public void createAlert(String message, String title){
        AlertDialog alertDialog = new AlertDialog.Builder(StatusActivity.this).create();
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



    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    public void getStatus() {
        String modSentence = "";
        try {
            //client("CP02S1");
            modSentence = client("SC00AD");
            if (modSentence == null) {
                createAlert("Sem informacoes... :( ","Alerta");
            } else {
                String[] parts = modSentence.toString().split("-");
                tvVoltage.setText(parts[0] + " " + parts[1] + " " + parts[2]);
                tvTemperature.setText(parts[3] + "ºC " + parts[4] + "ºC");
                tvCoolerSpeed.setText(parts[5] + "%");
            }
        } catch (IOException e) {
            Log.e("Error", e.toString());
            e.printStackTrace();

        }
    }

    public void getTime() {
        String modSentence = "";
        try {
            //client("CP02S1");
            modSentence = client("SC00TM");
            if (modSentence == null) {
                createAlert("Sem informacoes... :( ","Alerta");
            } else {
                String[] parts = modSentence.toString().split("-");
                tvHour.setText(parts[0]);
                tvDate.setText(parts[1]);
            }
        } catch (IOException e) {
            Log.e("Error", e.toString());
            e.printStackTrace();

        }
    }

    public void setTime(String m_Text) {
        String modSentence = "";
        byte[]  array  = BCD.DecimalToBCD(Long.parseLong(m_Text));
        String dateAndHour = "";

        for (int i = 0; i < array.length; i++) {
            dateAndHour += (char) array[i];
        }

        try {
            //client("CP02S1");
            modSentence = client("AC00TM"+dateAndHour);
            if (modSentence == null) {
                createAlert("Sem informacoes... :( ","Alerta");
            } else {
                String[] parts = modSentence.toString().split("-");
                tvHour.setText(parts[0]);
                tvDate.setText(parts[1]);
                Toast.makeText(this, "Data e hora ajustadas com sucesso!    ",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Log.e("Error", e.toString());
            e.printStackTrace();

        }
    }

    public void checking(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                getStatus();
                getTime();
            }
        });
    }

    public void createAlert(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");


        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                setTime(m_Text);
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

    public String client(String setor) throws IOException {


        DatagramSocket client_socket = new DatagramSocket(10000);
        InetAddress IPAddress =  InetAddress.getByName("192.168.0.200");
        client_socket.setSoTimeout(1000);
        //while (true)
        // {
        send_data = setor.getBytes();

        //System.out.println("Type Something (q or Q to quit): ");

        DatagramPacket send_packet = new DatagramPacket(send_data,setor.length(), IPAddress, 10000);
        client_socket.send(send_packet);
        //chandra
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        try {
            client_socket.receive(receivePacket);
            modifiedSentence = new String(receivePacket.getData());
        } catch (SocketTimeoutException ste) {
            Toast.makeText(this, ste.getMessage(),
                    Toast.LENGTH_SHORT).show();
            client_socket.send(send_packet);
        }



        client_socket.disconnect();
        client_socket.close();


        return  modifiedSentence;
        // }

    }

}
