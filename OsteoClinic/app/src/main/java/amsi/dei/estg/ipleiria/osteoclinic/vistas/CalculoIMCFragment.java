package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import amsi.dei.estg.ipleiria.osteoclinic.R;


public class CalculoIMCFragment extends Fragment {

    private EditText etPeso, etAltura;
    private Button btnCalcular;

    public CalculoIMCFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculo_imc, container, false);

        return view;
    }
}