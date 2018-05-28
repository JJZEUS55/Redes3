package redes.com.proyectoredescliente;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tooltip.Tooltip;


import at.markushi.ui.CircleButton;
import redes.com.sockets.Cliente;

public class VistaBebidas extends AppCompatActivity {

    private String bebida;
    private String ip = null;
    private int puerto = 0;
    Tooltip tooltip = null;
    private int contadorToolTip = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bebidas);

        ip = getIntent().getStringExtra("ip");
        puerto = getIntent().getIntExtra("puerto", 0);


        ImageButton btnCoca = findViewById(R.id.jbtnCoca);
        ImageButton btnSprite = findViewById(R.id.jbtnSprite);
        ImageButton btnFanta = findViewById(R.id.jbtnFanta);

        CircleButton btnInfoCoca = findViewById(R.id.jbtnInfoCoca);
        CircleButton btnInfoSprite = findViewById(R.id.jbtnInfoSprite);
        CircleButton btnInfoFanta = findViewById(R.id.jbtnInfoFanta);

        //Boton Coca
        btnCoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bebida = "Coca";
                enviarBebida(bebida);
                Toast.makeText(getApplicationContext(), "ip: " + ip + "\npuerto: " + puerto, Toast.LENGTH_SHORT).show();
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


        //Botones Informacion
        btnInfoCoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInfoBebida(v, Gravity.TOP, "coca");
            }
        });

        btnInfoSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInfoBebida(v, Gravity.TOP, "sprite");
            }
        });

        btnInfoFanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInfoBebida(v, Gravity.TOP, "fanta");
            }
        });
    }

    private void mostrarInfoBebida(View v, int posicion, String bebida) {
        CircleButton btn = (CircleButton) v;
        String infomacion = "";
        int color = Color.WHITE;

        switch (bebida) {
            case "coca":
                infomacion = "Nombre: Coca-Cola"
                        + "\nCosto: $16"
                        + "\nCalorias: 300 Calorias"
                        + "\nTamaño: 600ml";
                color = Color.RED;
                break;
            case "sprite":
                infomacion = "Nombre: Sprite"
                        + "\nCosto: $14"
                        + "\nCalorias: 240 Calorias"
                        + "\nTamaño: 600ml";
                color = Color.parseColor("#ff669900");
                break;
            case "fanta":
                infomacion = "Nombre: Fanta"
                        + "\nCosto: $12"
                        + "\nCalorias: 210 Calorias"
                        + "\nTamaño: 600ml";
                color = Color.parseColor("#ffff8800");
                break;
            default:
                infomacion = "Eso no existe negro >:v";
                break;
        }

        if (contadorToolTip == 0) {
            tooltip = new Tooltip.Builder(btn)
                    .setBackgroundColor(Color.rgb(209, 195, 195))
                    .setText(infomacion)
                    .setTextColor(color)
                    .setGravity(posicion)
                    .setCornerRadius(8f)
                    .setDismissOnClick(true)
                    .show();

            contadorToolTip++;
        } else {
            tooltip.dismiss();
            contadorToolTip = 0;
        }
    }

    public void enviarBebida(String bebida) {
        Cliente cli;
        if (ip != null && puerto != 0) {
            cli = new Cliente(ip, puerto);
        } else {
            cli = new Cliente();
        }
        cli.execute(bebida);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent configuracion = new Intent(VistaBebidas.this, VistaConfiguracion.class);
        int idItem = item.getItemId();
        if (idItem == R.id.menu_conexion) {
            startActivity(configuracion);
            //VistaBebidas.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
