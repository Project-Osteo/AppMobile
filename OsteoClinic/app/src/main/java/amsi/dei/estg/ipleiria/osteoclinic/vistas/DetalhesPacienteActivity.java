package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Paciente;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesPacienteActivity extends AppCompatActivity {

    public static final String USER_ID = "amsi.dei.estg.ipleiria.osteoclinic.vistas.USER_ID";
    public static final String ID_PACIENTE = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID_PACIENTE";
    public static final int DETALHE_ADICIONAR = 1;
    public static final int DETALHE_EDITAR = 2;
    public static final String RESPOSTA = "resposta";

    private EditText etNome, etSexo, etNacionalidade, etLocalidade, etTelemovel, etPeso, etAltura;

    private Button btConfirmarDados;

    private String alterar, confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_paciente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNome = findViewById(R.id.etNome);
        etSexo = findViewById(R.id.etSexo);
        etNacionalidade = findViewById(R.id.etNacionalidade);
        etLocalidade = findViewById(R.id.etLocalidade);
        etTelemovel = findViewById(R.id.etTelemovel);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);

        btConfirmarDados = findViewById(R.id.btConfirmarDados);

        Bundle extras = getIntent().getExtras();
        long user_id = extras.getLong("user_id");

        if(user_id > 0){

        }

//        Spinner dropdown = findViewById(R.id.spinner1);
//        String[] items = new String[]{"Masculino", "Feminino"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        dropdown.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflate = getMenuInflater();

        inflate.inflate(R.menu.menu_detalhes_paciente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.btAlterarEmail:
                dialogAlterarEmail();
            break;

            case R.id.btAlterarPass:
                dialogAlterarPass();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dialogAlterarPass() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Alterar Password !");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText etAlterar = new EditText(this);
        etAlterar.setHint("Nova Password");
        etAlterar.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        final EditText etConfirmar = new EditText(this);
        etConfirmar.setHint("Confirmar nova Password");
        etConfirmar.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        layout.addView(etAlterar);
        layout.addView(etConfirmar);
        builder.setView(layout);


        builder.setPositiveButton("Guardar alterações", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alterar = etAlterar.getText().toString();
                confirmar = etConfirmar.getText().toString();

                if(alterar.equals(confirmar)){
                    
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void dialogAlterarEmail() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Alterar Email !");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText etAlterar = new EditText(this);
        etAlterar.setHint("Novo Email");
        etAlterar.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        final EditText etConfirmar = new EditText(this);
        etConfirmar.setHint("Confirmar novo Email");
        etConfirmar.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        layout.addView(etAlterar);
        layout.addView(etConfirmar);
        builder.setView(layout);


        builder.setPositiveButton("Guardar alterações", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alterar = etAlterar.getText().toString().trim();
                confirmar = etConfirmar.getText().toString().trim();

                if(alterar.equals(confirmar)){

                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}