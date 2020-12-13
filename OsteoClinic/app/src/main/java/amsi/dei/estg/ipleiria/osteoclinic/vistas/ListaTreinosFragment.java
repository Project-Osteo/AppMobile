package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaTreinosAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class ListaTreinosFragment extends Fragment {

    private ListView listviewTreinos;
    private ListaTreinosAdapter adapter;

    public ListaTreinosFragment() {
        // Required empty public constructor
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

        listviewTreinos = view.findViewById(R.id.listview_treinos);
        Singleton gestor = Singleton.getInstance();

        adapter = new ListaTreinosAdapter(getActivity().getApplicationContext(), gestor.getListaTreinos());
        listviewTreinos.setAdapter(adapter);

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
}