package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaConsultasAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.ConsultasListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;


public class ListaConsultasFragment extends Fragment implements ConsultasListener {

    private ListView listviewConsultas;
    private ListaConsultasAdapter adapter;
    private SharedPreferences sharedPreferences;

    private Consulta consulta;

    public ListaConsultasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_consultas, container, false);

        setHasOptionsMenu(true);

        sharedPreferences = getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        long id_paciente = sharedPreferences.getLong("id_paciente", -1);

        listviewConsultas = view.findViewById(R.id.listview_consultas);
        Singleton gestor = Singleton.getInstance(getActivity().getApplicationContext());
        Singleton.getInstance(getContext()).setConsultasListener(this);
        Singleton.getInstance(getContext()).getAllConsultasAPI(getContext(), id_paciente);

        //chamar atividade DetalhesConsulta do item da lista selecionado
        listviewConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //chamar atividade usando a posição do elemento clicado
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalhesConsultaActivity.class);
                intent.putExtra(DetalhesConsultaActivity.ID_CONSULTA, id);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onRefreshListaConsultas(ArrayList<Consulta> listaconsultas) {
        if(listaconsultas != null)
            listviewConsultas.setAdapter(new ListaConsultasAdapter(getContext(), listaconsultas));
    }
}

