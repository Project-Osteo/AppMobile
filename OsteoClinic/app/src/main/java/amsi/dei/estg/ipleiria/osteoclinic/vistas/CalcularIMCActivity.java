package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class CalcularIMCActivity extends AppCompatActivity {

    private EditText etPeso, etAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_imc);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);

    }
}