package redes.com.proyectoredescliente;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import redes.com.sockets.Cliente;

public class VistaBebidas extends AppCompatActivity {

    private String bebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bebidas);


         Button btnCoca = findViewById(R.id.jbtnCoca);
         Button btnSprite = findViewById(R.id.jbtnSprite);
         Button btnFanta = findViewById(R.id.jbtnFanta);

        //Boton Coca
        btnCoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cli.mandarMensajeServidor("Coca");
                bebida = "Coca";
                enviarBebida(bebida);
            }
        });

        btnSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bebida = "Sprite";
                enviarBebida(bebida);
            }
        });

        btnFanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bebida = "Fanta";
                enviarBebida(bebida);
            }
        });
    }

    public void enviarBebida(String bebida){
        Cliente cli = new Cliente();
        cli.execute(bebida);
    }

}
