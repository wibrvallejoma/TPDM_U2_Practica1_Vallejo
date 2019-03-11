package com.example.tpdm_u2_practica1_vallejo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActualizarEliminarActivity extends AppCompatActivity {
    EditText descripcion, ubicacion, fecha, presupuesto;
    TextView titulo;
    Button actualizar, eliminar, regresar;
    int idProyectoCivil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_eliminar);

        descripcion = findViewById(R.id.descripcion_editar);
        ubicacion = findViewById(R.id.ubicacion_editar);
        fecha = findViewById(R.id.fecha_editar);
        presupuesto = findViewById(R.id.presupuesto_editar);

        titulo = findViewById(R.id.titulo_ActualizarEliminar);

        actualizar = findViewById(R.id.btn_Actualizar);
        eliminar = findViewById(R.id.btnEliminar);

        regresar = findViewById(R.id.regresar_actualizarEliminar);

        Bundle parametros = getIntent().getExtras();
        descripcion.setText(parametros.getString("DESCRIPCION"));
        ubicacion.setText(parametros.getString("UBICACION"));
        fecha.setText(parametros.getString("FECHA"));
        presupuesto.setText(""+parametros.getFloat("PRESUPUESTO"));
        idProyectoCivil = parametros.getInt("IDPROYECTO");
        titulo.setText("Editando "+parametros.getString("DESCRIPCION"));

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void eliminar() {
        ProyectoCivil a = new ProyectoCivil(this);
        String mensaje;
        boolean respuesta = a.elimiar(new ProyectoCivil(idProyectoCivil, descripcion.getText().toString(),
            ubicacion.getText().toString(), fecha.getText().toString(), Float.parseFloat(presupuesto.getText().toString())));
        if(respuesta){
            mensaje = "Se pudo eliminar correctamente el Proyecto Civil: "+descripcion.getText().toString();
            finish();
        }else{
            mensaje = "Error algo salio mal: " + a.error;
        }
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    private void actualizar() {
        ProyectoCivil a = new ProyectoCivil(this);
        String mensaje;
        boolean respuesta = a.actualizar(new ProyectoCivil(idProyectoCivil, descripcion.getText().toString(),
                ubicacion.getText().toString(), fecha.getText().toString(), Float.parseFloat(presupuesto.getText().toString())));
        if(respuesta){
            titulo.setText("Editando "+descripcion.getText().toString());
            mensaje = "Se actualizo correctamente el Proyecto Civil: "+descripcion.getText().toString();
        }else{
            mensaje = "Error algo salio mal: "+a.error;
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}
