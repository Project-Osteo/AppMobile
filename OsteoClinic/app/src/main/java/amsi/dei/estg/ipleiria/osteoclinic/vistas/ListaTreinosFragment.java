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

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaTreinosAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.TreinosListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Treino;

public class ListaTreinosFragment extends Fragment implements TreinosListener {

    private ListView listviewTreinos;
    private ListaTreinosAdapter adapter;
    private SharedPreferences sharedPreferences;

    public ListaTreinosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_treinos, container, false);

        setHasOptionsMenu(true);

        sharedPreferences = getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        long id_paciente = Long.parseLong(sharedPreferences.getString(MenuMainActivity.ID_PACIENTE, "-1"));

        listviewTreinos = view.findViewById(R.id.listview_treinos);



        Singleton.getInstance(getActivity().getApplicationContext()).setTreinosListener(this);
        Singleton.getInstance(getActivity().getApplicationContext()).getAllTreinosAPI(getContext(), id_paciente);

        listviewTreinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalhesTreinoActivity.class);
                intent.putExtra(DetalhesTreinoActivity.ID_TREINO, id);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onRefreshListaTreinos(ArrayList<Treino> listatreinos) {
        if(listatreinos != null)
            listviewTreinos.setAdapter(new ListaTreinosAdapter(getContext(), listatreinos));
    }
}