package amsi.dei.estg.ipleiria.osteoclinic.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
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
        if(inflater == null){
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.item_lista_consultas, null);
        }

        ViewHolderConsulta vHolder = (ViewHolderConsulta) view.getTag();
        if(vHolder == null){
            vHolder = new ViewHolderConsulta(view);
            view.setTag(vHolder);
        }

        return view;
    }

    public class ViewHolderConsulta{
        private TextView tvNumConsulta, tvDataConsulta, tvDescricaoConsulta;

        public ViewHolderConsulta(View view){
            tvNumConsulta = view.findViewById(R.id.tvNumConsulta);
            tvDataConsulta = view.findViewById(R.id.tvDataConsulta);
            tvDescricaoConsulta = view.findViewById(R.id.tvDescricaoConsulta);
        }

        //public void update(Consulta consulta) { ?!?!?!?
    }
}
