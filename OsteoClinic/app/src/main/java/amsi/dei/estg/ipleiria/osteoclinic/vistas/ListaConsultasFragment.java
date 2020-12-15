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

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaConsultasAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;


public class ListaConsultasFragment extends Fragment {

    private ListView listviewConsultas;
    private ListaConsultasAdapter adapter;

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

        listviewConsultas = view.findViewById(R.id.listview_consultas);
        Singleton gestor = Singleton.getInstance(getActivity().getApplicationContext());

        try {
            adapter = new ListaConsultasAdapter(getActivity(), gestor.getListaConsultas());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listviewConsultas.setAdapter(adapter);


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



}