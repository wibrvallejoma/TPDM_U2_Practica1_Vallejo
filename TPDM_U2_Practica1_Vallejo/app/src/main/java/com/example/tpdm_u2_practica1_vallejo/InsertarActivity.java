package com.example.tpdm_u2_practica1_vallejo;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InsertarActivity extends AppCompatActivity {
    TextView descripcion, ubicacion, fecha, presupuesto;
    Button regresar, insertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        descripcion = findViewById(R.id.descripcion_insertar);
        ubicacion = findViewById(R.id.ubicacion_insertar);
        fecha = findViewById(R.id.fecha_insertar);
        presupuesto = findViewById(R.id.presupuesto_insertar);

        regresar = findViewById(R.id.boton_regresar_proyectoCivil);
        insertar = findViewById(R.id.boton_insertar_proyectoCivil);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarNuevoProyectoCivil();
            }
        });
    }

    private void insertarNuevoProyectoCivil() {
        String mensaje = "";
        ProyectoCivil proyectoCivil = new ProyectoCivil(this);
        boolean respuesta = proyectoCivil.insertar(new ProyectoCivil(-1, descripcion.getText().toString(),
                ubicacion.getText().toString(), fecha.getText().toString(), Float.parseFloat(presupuesto.getText().toString())));

        if(respuesta){
            mensaje = "Se pudo insertar correctamente el Proyecto Civil: "+descripcion.getText().toString();
        }else{
            mensaje = "Error, no se pudo crear el Proyecto Civil, respuesta de SQLite: "+proyectoCivil.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atencion")
                .setMessage(mensaje)
                .setPositiveButton("ok", null)
                .show();
        descripcion.setText("");
        ubicacion.setText("");
        fecha.setText("");
        presupuesto.setText("");
    }
}
