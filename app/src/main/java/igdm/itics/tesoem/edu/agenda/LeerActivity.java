package igdm.itics.tesoem.edu.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeerActivity extends AppCompatActivity {

    TextView lblcontenido;
    private final String nomarch = "datosagendaivi.txt";
    private ArrayList<String> textocompleto = new ArrayList<String>();

    GridView gvdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer);

        lblcontenido = findViewById(R.id.lblLista);
        cargarinfo();

        gvdatos=findViewById(R.id.gvlistar);
        Contenido contenidoobj = new Contenido(this,textocompleto);
        gvdatos.setAdapter(contenidoobj);
    }

    private void cargarinfo(){
        ManejoArcivhoTXT objmanarch = new ManejoArcivhoTXT();
        if (objmanarch.verificar(this, nomarch)) {
            Toast.makeText(this, "Existe el archivo...", Toast.LENGTH_SHORT).show();
            if (objmanarch.leer(this, nomarch)){
                textocompleto=objmanarch.getContenido();
                String temporal="";
                for (String cadena:textocompleto) temporal += "\n"+cadena;
                lblcontenido.setText(temporal);
            }
        } else {
            Toast.makeText(this, "No Existe el Archivo", Toast.LENGTH_LONG).show();
        }
    }

    public void regresar(View v){
        Intent pantalla = new Intent(this,MainActivity.class);
        startActivity(pantalla);
        finish();
    }
}

