package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaConsultasAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.ConsultasListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;


public class ListaConsultasFragment extends Fragment implements ConsultasListener {

    private ListView listviewConsultas;
    private SharedPreferences sharedPreferences;


    public ListaConsultasFragment() {

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

        sharedPreferences = getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        long id_paciente = Long.parseLong(sharedPreferences.getString(MenuMainActivity.ID_PACIENTE, "-1"));

        listviewConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalhesConsultaActivity.class);
                intent.putExtra(DetalhesConsultaActivity.ID_CONSULTA, id);
                startActivity(intent);
            }
        });

        gestor.setConsultasListener(this);
        gestor.getAllConsultasAPI(getActivity().getApplicationContext(), id_paciente);

        return view;
    }

    @Override
    public void onRefreshListaConsultas(ArrayList<Consulta> listaconsultas) {
        if(listaconsultas != null)
            listviewConsultas.setAdapter(new ListaConsultasAdapter(getContext(), listaconsultas));
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_datas_listas, menu);

        super.onCreateOptionsMenu(menu, inflater);

    }
}

