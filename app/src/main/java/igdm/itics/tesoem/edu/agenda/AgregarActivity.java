package igdm.itics.tesoem.edu.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AgregarActivity extends AppCompatActivity {

    EditText txtnombre,txtedad,txtcorreo,txtcurp;
    TextView lblcontenido;

    private final String nomarch = "datosagendaivi.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        txtnombre = findViewById(R.id.lblnombre);
        txtedad = findViewById(R.id.txtedad);
        txtcorreo = findViewById(R.id.txtcorreo);
        txtcurp = findViewById(R.id.txtcurp);
        lblcontenido = findViewById(R.id.lblLista);
        llamadatos();
    }

    public void json(View v){
        try {
            ManejoArcivhoTXT controlador = new ManejoArcivhoTXT();
            Datos datosobj = new Datos(txtnombre.getText().toString(), Integer.parseInt(txtedad.getText().toString()), txtcorreo.getText().toString(), txtcurp.getText().toString());
            Gson gson = new Gson();
            String cjson = gson.toJson(datosobj);
            controlador.agregar(cjson,TextoCompleto);
            TextoCompleto = controlador.getContenido();
            if (controlador.grabar(TextoCompleto,this,nomarch)){
                Toast.makeText(this,"Se grabo correctamente...",Toast.LENGTH_SHORT).show();
                llamadatos();
            }else{
                Toast.makeText(this,"No se pudo grabar correctamente...",Toast.LENGTH_LONG).show();
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
