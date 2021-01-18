package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.app.Activity;
import android.content.Intent;
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

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaFeedbackAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.FeedbacksListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class ListaFeedbackFragment extends Fragment implements FeedbacksListener {

    private ListView listviewfeedback;

    private FloatingActionButton fab;

    private long consulta_id;

    public ListaFeedbackFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_feedback, container, false);

        DetalhesConsultaActivity detalhesConsultaActivity = (DetalhesConsultaActivity) getActivity();
        consulta_id = detalhesConsultaActivity.getIdConsulta();

        listviewfeedback = view.findViewById(R.id.listview_feedback);
        Singleton gestor = Singleton.getInstance(getActivity().getApplicationContext());

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
                intent.putExtra(DetalhesFeedbackActivity.CONSULTA_ID, ((DetalhesConsultaActivity)getActivity()).getIdConsulta());
                startActivityForResult(intent, DetalhesFeedbackActivity.DETALHE_ADICIONAR);
            }
        });

        gestor.setFeedbackListener(this);
        gestor.getAllFeedbacksAPI(getActivity().getApplicationContext(), consulta_id);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){

                case DetalhesFeedbackActivity.DETALHE_ADICIONAR:
                    Singleton.getInstance(getContext()).getAllFeedbacksAPI(getContext(), consulta_id);
                    String respostaAdd = data.getStringExtra(DetalhesFeedbackActivity.RESPOSTA);
                    if(respostaAdd.equals("Adicionou")){
                        Snackbar.make(getView(), "Novo feedback adicionado com sucesso",
                                Snackbar.LENGTH_SHORT).show();
                    }
                    break;

                case DetalhesFeedbackActivity.DETALHE_EDITAR:
                    Singleton.getInstance(getContext()).getAllFeedbacksAPI(getContext(), consulta_id);

                    String respostaEdit = data.getStringExtra(DetalhesFeedbackActivity.RESPOSTA);

                    if(respostaEdit.equals("Editou")){
                        Snackbar.make(getView(), "Mensagem do feedback alterada com sucesso.",
                                Snackbar.LENGTH_SHORT).show();
                    }
                    else if (respostaEdit.equals("Apagou")){
                        Snackbar.make(getView(), "Feedback apagado com sucesso",
                                Snackbar.LENGTH_SHORT).show();
                    }
                    break;
            }
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