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


public class ListaConsultasFragment extends Fragment {

    private ListView listView_consultas;

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


        return inflater.inflate(R.layout.fragment_lista_consultas, container, false);


    }



}