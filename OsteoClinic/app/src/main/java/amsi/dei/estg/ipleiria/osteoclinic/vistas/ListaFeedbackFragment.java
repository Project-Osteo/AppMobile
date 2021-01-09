package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onRefreshListaFeedbacks(ArrayList<Feedback> listafeedback) {
        if(listafeedback != null){
            listviewfeedback.setAdapter(new ListaFeedbackAdapter(getContext(), listafeedback));
        }
    }
}