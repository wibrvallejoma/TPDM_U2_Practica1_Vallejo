package com.example.tpdm_u2_practica1_vallejo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    ProyectoCivil vector[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista_proyectosCiviles);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlerta(position);
            }
        });
    }

    private void mostrarAlerta(final int position) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Atencion")
                .setMessage("Deseas modificar/editar el Proyecto civil: "+vector[position].getDescripcion()+"?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        invocarEliminarActualizar(position);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void invocarEliminarActualizar(int position) {
        Intent eliminaActualiza = new Intent(this, ActualizarEliminarActivity.class );

        eliminaActualiza.putExtra("IDPROYECTO", vector[position].getIdProyecto());
        eliminaActualiza.putExtra("DESCRIPCION", vector[position].getDescripcion());
        eliminaActualiza.putExtra("UBICACION", vector[position].getUbicacion());
        eliminaActualiza.putExtra("FECHA", vector[position].getFecha());
        eliminaActualiza.putExtra("PRESUPUESTO", vector[position].getPresupuesto());

        startActivity(eliminaActualiza);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_insertar_ProyectoCivil:
                Intent nuevoProyectoCivil = new Intent(this, InsertarActivity.class);
                startActivity(nuevoProyectoCivil);
                break;
            case R.id.item_consultar_ProyectoCivil:
                Intent consultarProyectoCivil = new Intent(this, ConsultarActivity.class);
                startActivity(consultarProyectoCivil);
                break;
            case R.id.item_acercaDe:
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setTitle("Acerca de")
                        .setMessage("Elaborado por: Williams Vallejo. 2019")
                        .setPositiveButton("Aceptar", null)
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        ProyectoCivil proyectoCivil = new ProyectoCivil(this);
        vector = proyectoCivil.consultar();
        String[] descripcion = null;

        if(vector == null){
            descripcion = new String[1];
            descripcion[0] = "No hay Proyectos capturados";
        }else{
            descripcion = new String[vector.length];
            for(int i = 0; i < vector.length; i++){
                ProyectoCivil temp = vector[i];
                descripcion[i] = temp.getDescripcion();
            }
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, descripcion);

        lista.setAdapter(adaptador);

        super.onStart();
    }
}
