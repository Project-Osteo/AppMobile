package amsi.dei.estg.ipleiria.osteoclinic.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
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
    public View getView(int position, View view, ViewGroup parent) {
        if(inflater == null){
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.card_lista_feedback, null);
        }

        ViewHolderFeedback vHolder = (ViewHolderFeedback) view.getTag();
        if(vHolder == null){
            vHolder = new ViewHolderFeedback(view);
            view.setTag(vHolder);
        }

        vHolder.update(this.lista_feedback.get(position));

        return view;
    }

    private class ViewHolderFeedback {
        private TextView tvDataFeedback, tvMensagemFeedback, tvNumFeedbackCard;

        public ViewHolderFeedback(View view){
            tvNumFeedbackCard = view.findViewById(R.id.tvNumFeedbackCard);
            tvDataFeedback = view.findViewById(R.id.tvDataFeedbackCard);
            tvMensagemFeedback = view.findViewById(R.id.tvFeedbackCard);
        }

        public void update(Feedback feedback){
            this.tvNumFeedbackCard.setText(feedback.getId()+"");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:MM:ss / yyyy-MM-dd");
            this.tvDataFeedback.setText(formatter.format(feedback.getDatahora()));
            this.tvMensagemFeedback.setText(feedback.getMensagem());
        }
    }
}
