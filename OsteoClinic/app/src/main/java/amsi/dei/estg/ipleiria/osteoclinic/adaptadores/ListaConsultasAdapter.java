package amsi.dei.estg.ipleiria.osteoclinic.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;

public class ListaConsultasAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Consulta> lista_consultas;

    public ListaConsultasAdapter(Context context, ArrayList<Consulta> lista_consultas) {
        this.context = context;
        this.lista_consultas = lista_consultas;
    }


    @Override
    public int getCount() {
        return this.lista_consultas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista_consultas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.lista_consultas.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return null;
    }
}
