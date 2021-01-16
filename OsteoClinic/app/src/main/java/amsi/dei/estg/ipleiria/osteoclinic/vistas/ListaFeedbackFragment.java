package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaFeedbackAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.FeedbacksListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class ListaFeedbackFragment extends Fragment implements FeedbacksListener {

    private ListView listviewfeedback;
    private ListaFeedbackAdapter adapter;

    private FloatingActionButton fab;

    private long consulta_id;

    public ListaFeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_feedback, container, false);

        setHasOptionsMenu(true);

        DetalhesConsultaActivity detalhesConsultaActivity = (DetalhesConsultaActivity) getActivity();
        consulta_id = detalhesConsultaActivity.getIdConsulta();

        listviewfeedback = view.findViewById(R.id.listview_feedback);
        Singleton gestor = Singleton.getInstance(getActivity().getApplicationContext());


        try{
            adapter = new ListaFeedbackAdapter(getActivity(), gestor.getListaFeedbackBD());
        }catch (ParseException e){
            e.printStackTrace();
        }
        listviewfeedback.setAdapter(adapter);

        listviewfeedback.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalhesFeedbackActivity.class);
                intent.putExtra(DetalhesFeedbackActivity.ID_FEEDBACK, id);
                intent.putExtra("id_consulta", ((DetalhesConsultaActivity)getActivity()).getIdConsulta());
                startActivityForResult(intent, DetalhesFeedbackActivity.DETALHE_EDITAR);
            }
        });

        fab = view.findViewById(R.id.fab_add_list);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalhesFeedbackActivity.class);
                intent.putExtra("id_consulta", ((DetalhesConsultaActivity)getActivity()).getIdConsulta());
                startActivityForResult(intent, DetalhesFeedbackActivity.DETALHE_ADICIONAR);
            }
        });

        Singleton.getInstance(getContext()).setFeedbackListener(this);
        Singleton.getInstance(getContext()).getAllFeedbacksAPI(getContext(), consulta_id);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){

                case DetalhesFeedbackActivity.DETALHE_ADICIONAR:
                    Singleton.getInstance(getContext()).getAllFeedbacksAPI(getContext(), consulta_id);
                    Snackbar.make(getView(), "Novo feedback adicionado",
                            Snackbar.LENGTH_SHORT).show();
                    break;

                case DetalhesFeedbackActivity.DETALHE_EDITAR:
                    Singleton.getInstance(getContext()).getAllFeedbacksAPI(getContext(), consulta_id);

                    String resposta = data.getStringExtra(DetalhesFeedbackActivity.RESPOSTA);

                    if(resposta.equals("Editou")){
                        Snackbar.make(getView(), "Mensagem do feedback alterada.",
                                Snackbar.LENGTH_SHORT).show();
                    }
                    else {
                        Snackbar.make(getView(), "Feedback apagado",
                                Snackbar.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
        else {
            Snackbar.make(getView(), "Problemas a manipular informação",
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefreshListaFeedbacks(ArrayList<Feedback> listafeedback) {
        if(listafeedback != null){
            listviewfeedback.setAdapter(new ListaFeedbackAdapter(getContext(), listafeedback));
        }
    }

    @Override
    public void onRefreshDetalhes() {

    }
}