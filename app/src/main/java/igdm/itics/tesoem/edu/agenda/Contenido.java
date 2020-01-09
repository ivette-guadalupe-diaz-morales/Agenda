package igdm.itics.tesoem.edu.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Contenido extends BaseAdapter {

    Context context;
    ArrayList<String> TextoCompleto;

    public Contenido(Context context, ArrayList<String> TextoCompleto) {
        this.context = context;
        this.TextoCompleto = TextoCompleto;
    }

    @Override
    public int getCount() {
        return TextoCompleto.size();
    }

    @Override
    public Object getItem(int position) {
        return TextoCompleto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.gvdatos,null);
        }
        String llenadatos = TextoCompleto.get(position);
        Gson gson = new Gson();
        Datos objdatos= gson.fromJson(llenadatos,Datos.class);
        TextView txtnombre = convertView.findViewById(R.id.lblnombre);
        txtnombre.setText(objdatos.getNombre());
        TextView txtedad = convertView.findViewById(R.id.lbledad);
        txtedad.setText(String.valueOf(objdatos.getEdad()));
        TextView txtcorreo = convertView.findViewById(R.id.lblcorreo);
        txtcorreo.setText(objdatos.getCorreo());
        TextView txtcurp = convertView.findViewById(R.id.lblcurp);
        txtcurp.setText(objdatos.getCurp());
        ImageView imgv = convertView.findViewById(R.id.imgv);
        String nombbb = objdatos.getNombre();
        if (nombbb=="Ivette") {
            Glide.with(context)
                    .load("https://proyagenda.000webhostapp.com/ivi.jpg")
                    .fitCenter()
                    .into(imgv);
        }else {
            Glide.with(context)
                    .load("https://proyagenda.000webhostapp.com/quien.jpg")
                    .fitCenter()
                    .into(imgv);
        }
        return convertView;
    }
}

