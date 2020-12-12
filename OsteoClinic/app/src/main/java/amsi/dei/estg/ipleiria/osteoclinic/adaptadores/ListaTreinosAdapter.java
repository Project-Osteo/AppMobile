package amsi.dei.estg.ipleiria.osteoclinic.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Treino;

public class ListaTreinosAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Treino> listatreinos;

    public ListaTreinosAdapter (Context context, ArrayList<Treino> listatreinos){
        this.context = context;
        this.listatreinos = listatreinos;
    }

    @Override
    public int getCount() {
        return this.listatreinos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listatreinos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listatreinos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(inflater == null){
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.item_lista_treino, null);
        }

        ViewHolderTreino vHolderTreino = (ViewHolderTreino) view.getTag();
        if(vHolderTreino == null){
            vHolderTreino = new ViewHolderTreino(view);
            view.setTag(vHolderTreino);
        }

        return view;
    }

    public class ViewHolderTreino {
        private TextView tvNumTreino, tvDataTreino, tvTipoTreino;

        public ViewHolderTreino(View view){
            tvNumTreino = view.findViewById(R.id.tvNumTreinoItem);
            tvDataTreino = view.findViewById(R.id.tvDataTreinoItem);
            tvTipoTreino = view.findViewById(R.id.tvTipoTreinoItem);
        }
    }
}
