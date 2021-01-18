package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import amsi.dei.estg.ipleiria.osteoclinic.R;


public class InicialFragment extends Fragment implements View.OnClickListener {

    private Button button1, button2, button3;


    public InicialFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicial, container, false);

        button1 = view.findViewById(R.id.btConsultas);
        button2 = view.findViewById(R.id.btTreinos);
        button3 = view.findViewById(R.id.btCalcIMC);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View view) {
        Fragment fragmento;
        switch (view.getId()) {
            case R.id.btConsultas:
                fragmento = new ListaConsultasFragment();
                replaceFragment(fragmento);
                break;

            case R.id.btTreinos:
                fragmento = new ListaTreinosFragment();
                replaceFragment(fragmento);
                break;

            case R.id.btCalcIMC:
                try {
                    Intent intent = new Intent(getActivity().getApplicationContext(), CalcularIMCActivity.class);
                    startActivity(intent);
                    break;
                } catch (NullPointerException e){
                    e.printStackTrace();
                }

        }
    }

    private void replaceFragment(Fragment fragmento) {
        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contentFragment, fragmento);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}