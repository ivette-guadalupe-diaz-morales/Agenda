package igdm.itics.tesoem.edu.agenda;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ManejoArcivhoTXT {
    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    public void  agregar(String dato,ArrayList<String> contenido){
        this.TextoCompleto=contenido;
        this.TextoCompleto.add(dato);
    }

    public boolean grabar(ArrayList<String> dato, Context contexto, String nomarch){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(contexto.openFileOutput(nomarch, Activity.MODE_PRIVATE));
            for (String Texto : dato)
                archivo.write(Texto+"\n");
            archivo.flush();
            archivo.close();
        } catch (Exception ex){
            return false;
        }
        return true;
    }

    public boolean leer(Context context,String nomarch) {
        try {
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(nomarch));
            BufferedReader br = new BufferedReader(archivo);
            String cadena = br.readLine();
            while (cadena != null){
                TextoCompleto.add(cadena);
                cadena = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean verificar(Context context, String nomarch){
        String[] archivos = context.fileList();
        for (String archivo: archivos){
            if (archivo.equals(nomarch)) return true;
        }
        return false;
    }

    public boolean eliminar(Context context,String elim,String nomarch){
        try {
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(nomarch));
            BufferedReader br = new BufferedReader(archivo);
            String cadena = br.readLine();
            ArrayList<String> nuevoarreglo = new ArrayList<>();
            while (cadena != null) {
                Gson gson = new Gson();
                Datos objdatos= gson.fromJson(cadena,Datos.class);
                if (elim.equals(objdatos.getNombre())) {
                    cadena = br.readLine();
                }else {
                    nuevoarreglo.add(cadena);
                    cadena = br.readLine();
                }
            }
            File viejoarchivo = new File(nomarch);
            viejoarchivo.delete();
            try {
                OutputStreamWriter archivonuevo = new OutputStreamWriter(context.openFileOutput(nomarch, Activity.MODE_PRIVATE));
                for (String Texto : nuevoarreglo)
                    archivonuevo.write(Texto+"\n");
                archivonuevo.flush();
                archivonuevo.close();
                Toast.makeText(context,"El dato a sido eliminado...",Toast.LENGTH_LONG).show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<String> getContenido(){
        return TextoCompleto;
    }

}
