package amsi.dei.estg.ipleiria.osteoclinic.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;

public class ListaFeedbackAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Feedback> lista_feedback;

    public ListaFeedbackAdapter(Context context, ArrayList<Feedback> lista_feedback){
        this.context = context;
        this.lista_feedback = lista_feedback;
    }

    @Override
    public int getCount() {
        return this.lista_feedback.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista_feedback.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.lista_feedback.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
