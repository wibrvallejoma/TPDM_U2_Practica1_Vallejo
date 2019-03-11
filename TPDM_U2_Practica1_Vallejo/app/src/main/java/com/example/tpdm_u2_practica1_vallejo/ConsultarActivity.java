package com.example.tpdm_u2_practica1_vallejo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConsultarActivity extends AppCompatActivity {
    EditText consultarTxt;
    TextView descripcion, ubicacion, fecha, presupuesto;
    Button cerraBtn, consultarBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        consultarTxt = findViewById(R.id.consultar_proyectoCivil);

        descripcion = findViewById(R.id.descripcion_consultar);
        ubicacion = findViewById(R.id.ubicacion_consultar);
        fecha = findViewById(R.id.fecha_consultar);
        presupuesto = findViewById(R.id.presupuesto_consultar);

        cerraBtn = findViewById(R.id.boton_cerrar_consultar);
        consultarBtn = findViewById(R.id.boton_consultar_proyectoCivil);

        cerraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        consultarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarProyectoCivil();
            }
        });

    }

    private void consultarProyectoCivil() {
        ProyectoCivil proyectoCivil = new ProyectoCivil(this);
        ProyectoCivil vector [] = proyectoCivil.consultar("DESCRIPCION", consultarTxt.getText().toString());

        if(vector != null){
            for(int i = 0; i< vector.length; i++){
                ProyectoCivil temp = vector[i];
                descripcion.setText(temp.getDescripcion());
                ubicacion.setText(temp.getUbicacion());
                fecha.setText(temp.getFecha());
                presupuesto.setText("$ "+temp.getPresupuesto());

            }
        }
    }
}
