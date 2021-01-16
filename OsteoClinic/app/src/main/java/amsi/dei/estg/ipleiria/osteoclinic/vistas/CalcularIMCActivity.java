package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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

                if(resultado < 20){
                   dialogPesoAbaixo();
                }
                else if(resultado < 25){
                    dialogoPesoNormal();
                }
                else if(resultado < 30){
                    dialogExcessoPeso();
                }
                else if(resultado < 35){
                    dialogObesidade();
                }
                else{
                    dialogObesidadeMorbida();
                }

                String imc = String.format("%.1f", resultado);

                //String imc = Double.toString(resultado);

                tvResultado.setText(imc);

                etPeso.setText("");
                etAltura.setText("");
            }
        });
    }

    private void dialogObesidadeMorbida() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Peso ideal:").setMessage(" Muita Atenção !!! O seu peso encontra-se dentro dos valores de obesidade mórbida.");
        builder.show();
    }

    private void dialogObesidade() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Peso ideal:").setMessage("Muita Atenção !! O seu peso encontra-se dentro dos valores de obesidade.");
        builder.show();
    }

    private void dialogExcessoPeso() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Peso ideal:").setMessage("Atenção ! O seu peso encontra-se dentro dos valores de excesso de peso.");
        builder.show();
    }

    private void dialogoPesoNormal() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Peso ideal:").setMessage("Muito bem !!! O seu peso encontra-se dentro dos valores ideais");
        builder.show();
    }

    private void dialogPesoAbaixo() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Peso ideal:").setMessage("Atenção ! O seu peso encontra-se abaixo do ideal.");
        builder.show();
    }
}