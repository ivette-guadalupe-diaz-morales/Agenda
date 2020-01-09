package igdm.itics.tesoem.edu.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EliminarActivity extends AppCompatActivity {

    EditText eliminad;
    TextView lblcontenido;

    private final String nomarch = "datosagendaivi.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        eliminad = findViewById(R.id.txteliminar);
        lblcontenido = findViewById(R.id.lblLista);
        llamadatos();
    }

    public void eliminar(View v){
        try {
            ManejoArcivhoTXT controlador = new ManejoArcivhoTXT();
            TextoCompleto = controlador.getContenido();
            if (controlador.eliminar(this,eliminad.getText().toString(),nomarch)){
                Toast.makeText(this,"Se elimino correctamente..",Toast.LENGTH_LONG).show();
                llamadatos();
            }else {
                Toast.makeText(this,"No se logro eliminar correctamente..",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void regresar(View v){
        Intent pantalla = new Intent(this,MainActivity.class);
        startActivity(pantalla);
        finish();
    }
    public void llamadatos(){
        ManejoArcivhoTXT informacion = new ManejoArcivhoTXT();
        if (informacion.verificar(this,nomarch)){
            //Toast.makeText(this, "Bienvenid@", Toast.LENGTH_LONG).show();
            if(informacion.leer(this,nomarch)){
                TextoCompleto = informacion.getContenido();
                String temporal = "";
                for (String cadena :TextoCompleto) temporal += "\n"+ cadena;
                lblcontenido.setText(temporal);
            }
        }//else{
        // Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        //}
    }
}
