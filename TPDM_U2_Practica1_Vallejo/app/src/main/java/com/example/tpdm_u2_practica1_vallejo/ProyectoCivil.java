package com.example.tpdm_u2_practica1_vallejo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class ProyectoCivil {
    private BaseDatos base;
    private int idProyecto;
    private String descripcion, ubicacion, fecha;
    private float presupuesto;
    protected String error;


    public ProyectoCivil(Activity activity){
        base = new BaseDatos(activity, "civil", null, 1);
    }

    public ProyectoCivil(int idProyecto, String descripcion, String ubicacion, String fecha, float presupuesto){
        this.idProyecto = idProyecto;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.presupuesto = presupuesto;
    }

    //CRUD
    public boolean insertar(ProyectoCivil proyectoCivil){
        try{
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyectoCivil.getDescripcion());
            datos.put("UBICACION", proyectoCivil.getUbicacion());
            datos.put("FECHA", proyectoCivil.getFecha());
            datos.put("PRESUPUESTO", proyectoCivil.getPresupuesto());

            long resultado = transaccionInsertar.insert("PROYECTOS", "IDPROYECTO",datos);

            transaccionInsertar.close();

            if(resultado == -1 ) return false;

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public ProyectoCivil[] consultar(String columna, String condicion){
        ProyectoCivil[] resultado = null;
        try{
            SQLiteDatabase transaccionConsulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS WHERE IDPROYECTO = "+condicion;

            if(columna.startsWith("DESCRIPCION")){
                SQL = "SELECT * FROM PROYECTOS WHERE DESCRIPCION LIKE '%"+condicion+"%'";
            }
            Cursor c = transaccionConsulta.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new ProyectoCivil[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos] = new ProyectoCivil(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3), c.getFloat(4));
                    pos ++;
                }while(c.moveToNext());
            }
            transaccionConsulta.close();
        }catch (SQLiteException e){
            error = e.getMessage();
            return null;
        }
        return resultado;
    }

    public ProyectoCivil[] consultar(){
        ProyectoCivil[] resultado = null;
        try{
            SQLiteDatabase transaccionConsulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS";

            Cursor c = transaccionConsulta.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new ProyectoCivil[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos] = new ProyectoCivil(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3), c.getFloat(4));
                    pos ++;
                }while(c.moveToNext());
            }
            transaccionConsulta.close();
        }catch (SQLiteException e){
            error = e.getMessage();
            return null;
        }
        return resultado;
    }

    public boolean elimiar(ProyectoCivil proyectoCivil){
        int resultado;
        try{
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            String claves[] = {""+proyectoCivil.getIdProyecto()};
            resultado = transaccionEliminar.delete("PROYECTOS", "IDPROYECTO = ?", claves);
            transaccionEliminar.close();
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado>0;
    }

    public boolean actualizar(ProyectoCivil proyectoCivil){
        try{
            SQLiteDatabase transaccionActualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyectoCivil.getDescripcion());
            datos.put("UBICACION", proyectoCivil.getUbicacion());
            datos.put("FECHA", proyectoCivil.getFecha());
            datos.put("PRESUPUESTO", proyectoCivil.getPresupuesto());

            long resultado =
                    transaccionActualizar.update("PROYECTOS", datos,
                            "IDPROYECTO = ?", new String[]{"" + proyectoCivil.getIdProyecto()});
            transaccionActualizar.close();
            if(resultado == -1) return false;
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
