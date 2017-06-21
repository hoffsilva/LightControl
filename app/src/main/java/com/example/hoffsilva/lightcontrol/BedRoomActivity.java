package com.example.hoffsilva.lightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BedRoomActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Boolean setor1isOn = false;
    private static final String host = null;
    private int port;
    String str= "CP02S1";
    /** Called when the activity is first created. */
    byte[] send_data = new byte[50];
    byte[] receiveData = new byte[50];
    String modifiedSentence;


    //De acionamento

    String scene1 = "CP02C1";
    String scene2 = "CP02C2";
    String scene3 = "CP02C3";
    String scene4 = "CP02C4";
    String scene5 = "CP02C5";
    String scene6 = "CP02C6";
    String scene7 = "CP02C7";
    String scene8 = "CP02C8";
    String scene9 = "CP02C9";


    //De configuracao
    String confScene1 = "AP02C1";
    String confScene2 = "AP02C2";
    String confScene3 = "AP02C3";
    String confScene4 = "AP02C4";
    String confScene5 = "AP02C5";
    String confScene6 = "AP02C6";
    String confScene7 = "AP02C7";
    String confScene8 = "AP02C8";
    String confScene9 = "AP02C9";


    ArrayList<Button> arrayOfSector = new ArrayList<>();
    ArrayList<Button> arrayOfScene = new ArrayList<>();
    Timer timer = new Timer();


    String setor1txt = "CP02S1";
    String setor2txt = "CP02S2";
    String setor3txt = "CP02S3";
    String setor4txt = "CP02S4";
    String setor5txt = "CP02S5";
    String setor6txt = "CP02S6";
    String setorAlltxt = "CP02S0";

    Button all;

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


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Lista de botoes das cenas.
        //Botao 1
        Button cena1 = (Button) findViewById(R.id.cena1);
        //buttons.add(setor1);
        //botao 2
        final Button cena2 = (Button) findViewById(R.id.cena2);
        //buttons.add(setor2);
        //botao 3
        final Button cena3 = (Button) findViewById(R.id.cena3);
        //buttons.add(setor3);
        //botao 4
        final Button cena4 = (Button) findViewById(R.id.cena4);
        //buttons.add(setor4);
        //botao 5
        final Button cena5 = (Button) findViewById(R.id.cena5);
        //buttons.add(setor5);
        //botao 6
        final Button cena6 = (Button) findViewById(R.id.cena6);
        //buttons.add(setor6);
        //botao 7
        final Button cena7 = (Button) findViewById(R.id.cena7);
        //buttons.add(setor7);
        //botao 8
        final Button cena8 = (Button) findViewById(R.id.cena8);
        //buttons.add(setor7);
        //botao 9
        final Button cena9 = (Button) findViewById(R.id.cena9);
        //buttons.add(setor7);

        //Switch
        final Switch switchKey = (Switch) findViewById(R.id.switch_config);




        //Lista de botoes referentes aos setores.
        //Botao 1
        final Button setor1 = (Button) findViewById(R.id.setor1);

        //seek 1
        final SeekBar seekSetor1 = (SeekBar) findViewById(R.id.seekSetor1);

        //botao 2
        final Button setor2 = (Button) findViewById(R.id.setor2);

        //seek 1
        final SeekBar seekSetor2 = (SeekBar) findViewById(R.id.seekSetor2);

        //botao 3
        final Button setor3 = (Button) findViewById(R.id.setor3);

        //botao 4
        final Button setor4 = (Button) findViewById(R.id.setor4);

        //botao 5
        final Button setor5 = (Button) findViewById(R.id.setor5);

        //botao 6
        final Button setor6 = (Button) findViewById(R.id.setor6);

        //botao 7
        final Button setor7 = (Button) findViewById(R.id.setor7);



//Lista de botoes das cenas.
        //Cena 1
        arrayOfScene.add(cena1);
        //botao 2
        arrayOfScene.add(cena2);
        //botao 3
        arrayOfScene.add(cena3);
        //botao 4
        arrayOfScene.add(cena4);
        //botao 5
        arrayOfScene.add(cena5);
        //botao 6
        arrayOfScene.add(cena6);
        //botao 7
        arrayOfScene.add(cena7);
        //botao 8
        arrayOfScene.add(cena8);
        //botao 9
        arrayOfScene.add(cena9);

        if (switchKey != null){
            switchKey.setOnCheckedChangeListener(this);
        }


//Lista de botoes referentes aos setores.
        //Botao 1
        arrayOfSector.add(setor1);
        //botao 2
        arrayOfSector.add(setor2);
        //botao 3
        arrayOfSector.add(setor3);
        //botao 4
        arrayOfSector.add(setor4);
        //botao 5
        arrayOfSector.add(setor5);
        //botao 6
        arrayOfSector.add(setor6);
        //botao 7
        arrayOfSector.add(setor7);

        final TextView textView2 = (TextView) findViewById(R.id.textView2);

        seekSetor1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int progresss = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresss = progress;

                setor1.setAlpha((float) progress/100);
                textView2.setText(String.valueOf(progresss));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String modSentence = "";

                int value = (progresss*255)/100;

                try {
                    //client("CP02S1");
                    char dimmerValue = (char) value;

                    modSentence = client("CP02D1"+dimmerValue);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor2, retorno[3]);
                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }

            }
        });

        final TextView textView = (TextView) findViewById(R.id.textView);

        seekSetor2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int progresss = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresss = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String modSentence = "";
                int value = (progresss*255)/100;
                textView.setText(String.valueOf(value));
                try {
                    //client("CP02S1");
                    char dimmerValue = (char) value;

                    modSentence = client("CP02D2"+dimmerValue);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor2, retorno[3]);
                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }

            }
        });

        setor1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor1txt);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor1, retorno[2]);
                    }


                } catch (IOException e) {
                    createAlert(e.toString(),"Alerta");
                }
            }
        });


        setor2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor2txt);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor2, retorno[3]);
                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });


        setor3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor3txt);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor3, retorno[4]);
                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });


        setor4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor4txt);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor4, retorno[5]);
                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });


        setor5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor5txt);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor5, retorno[6]);
                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });


        setor6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setor6txt);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        setBgButton(setor6, retorno[7]);
                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        final Button setorAll = (Button) findViewById(R.id.all);
        setorAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String modSentence = "";
                try {
                    //client("CP02S1");
                    modSentence = client(setorAlltxt);
                    if (!modSentence.contains("St")) {
                        createAlert(modSentence,"Alerta");
                    } else {
                        char[] retorno = modSentence.toCharArray();
                        for (int i = 2; i < 10; i++ ) {
                            setBgButton(arrayOfSector.get(i - 2), retorno[i]);
                        }

                    }
                } catch (IOException e) {
                    Log.e("Error", e.toString());
                    e.printStackTrace();
                }
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checking();
            }
        }, 0 ,5000);




    }







    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    public void checking(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //createAlert("aasas", "asasas");
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

    public void setBgButton(Button botao, Character isOn) {

        if (isOn.toString().equals("1")) {
            botao.setBackgroundResource(R.drawable.light_on);
        } else {
            botao.setBackgroundResource(R.drawable.light_off);
        }

    }

    public void setBgButtonPump(Button botao, Character isOn) {

        if (isOn.toString().equals("1")) {
            botao.setBackgroundResource(R.drawable.pump_on);
        } else {
            botao.setBackgroundResource(R.drawable.pump_off);
        }

    }

    public String client(String setor) throws IOException {


        DatagramSocket client_socket = new DatagramSocket(10000);
        InetAddress IPAddress =  InetAddress.getByName("192.168.0.200");

        //while (true)
        // {
        send_data = setor.getBytes();

        //System.out.println("Type Something (q or Q to quit): ");

        DatagramPacket send_packet = new DatagramPacket(send_data,setor.length(), IPAddress, 10000);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "Modo " + (isChecked ? "Aciona ativado." : "Configura ativado."),
                Toast.LENGTH_SHORT).show();
        if(isChecked) {
            scene1 = "AP02C1";
            scene2 = "AP02C2";
            scene3 = "AP02C3";
            scene4 = "AP02C4";
            scene5 = "AP02C5";
            scene6 = "AP02C6";
            scene7 = "AP02C7";
            scene8 = "AP02C8";
            scene9 = "AP02C9";
            createAlert("asasas", "asasasas");
        } else {
            scene1 = "CP02C2";
            scene2 = "CP02C2";
            scene3 = "CP02C3";
            scene4 = "CP02C4";
            scene5 = "CP02C5";
            scene6 = "CP02C6";
            scene7 = "CP02C7";
            scene8 = "CP02C8";
            scene9 = "CP02C9";
            createAlert("ghghgh", "hjghjgjhj");
        }
    }


}
