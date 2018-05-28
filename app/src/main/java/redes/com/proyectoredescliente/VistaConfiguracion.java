package redes.com.proyectoredescliente;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VistaConfiguracion extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_config_conexion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText etDirIP = findViewById(R.id.jetDirIP);
        final EditText etPuerto = findViewById(R.id.jetPuerto);
        Button btnGuardar = findViewById(R.id.jbtnGuardarConexion);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vistaBebidas = new Intent(VistaConfiguracion.this, VistaBebidas.class);
                String dirIp;
                int puerto;
                dirIp = etDirIP.getText().toString();
                puerto = Integer.parseInt(etPuerto.getText().toString());
                Toast.makeText(VistaConfiguracion.this, "Guardando Cambios", Toast.LENGTH_SHORT).show();

                vistaBebidas.putExtra("ip", dirIp);
                vistaBebidas.putExtra("puerto", puerto);
                startActivity(vistaBebidas);
                try {
                    VistaConfiguracion.this.finish();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });


    }
}
