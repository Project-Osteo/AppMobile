package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class CalcularIMCActivity extends AppCompatActivity {

    private EditText etPeso, etAltura;
    private TextView tvResultado;
    private double peso, altura;
    private double resultado;
    Button btCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_imc);

        setTitle("Cálculo de IMC");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        tvResultado = findViewById(R.id.textView4);

        btCalculo = findViewById(R.id.btCalcIMC);

        btCalculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peso = Double.parseDouble(etPeso.getText().toString());
                altura = Double.parseDouble(etAltura.getText().toString());

                resultado = peso / (altura * altura);

                String imc = String.format("%.1f", resultado);

                //String imc = Double.toString(resultado);

                tvResultado.setText(imc);

                etPeso.setText("");
                etAltura.setText("");
            }
        });
    }
}