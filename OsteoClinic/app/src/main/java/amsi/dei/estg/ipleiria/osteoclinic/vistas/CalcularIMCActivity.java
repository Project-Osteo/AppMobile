package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class CalcularIMCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_imc);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragmento = new CalculoIMCFragment();
        if(fragmento != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutIMC, fragmento).commit();
        }
    }
}