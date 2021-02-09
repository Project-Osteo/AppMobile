package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.PacientesListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Paciente;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesPacienteActivity extends AppCompatActivity implements PacientesListener {

    private EditText etNome, etSexo, etNacionalidade, etLocalidade, etTelemovel, etPeso, etAltura;
    private Button btConfirmarDados;
    private String alterar, confirmar;

    private long user_id;

    private Paciente paciente;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_paciente);

        Singleton.getInstance(getApplicationContext()).setPacientesListener(this);

        etNome = findViewById(R.id.etNome);
        etSexo = findViewById(R.id.etSexo);
        etNacionalidade = findViewById(R.id.etNacionalidade);
        etLocalidade = findViewById(R.id.etLocalidade);
        etTelemovel = findViewById(R.id.etTelemovel);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);

        btConfirmarDados = findViewById(R.id.btConfirmarDados);

        Bundle extras = getIntent().getExtras();
        String request = extras.getString("tarefa");

        if(request.equals("editar")){
            setTitle("Perfil do utilizador");
            sharedPreferences = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
            long id_paciente = Long.parseLong(sharedPreferences.getString(MenuMainActivity.ID_PACIENTE,"-1"));
            Singleton.getInstance(getApplicationContext()).getPacienteAPI(getApplicationContext(), id_paciente);

            btConfirmarDados.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alterarPaciente();
                }
            });
        }


        if(request.equals("adicionar")){
            setTitle("Inserir dados pessoais");
            user_id = extras.getLong("user_id");

            btConfirmarDados.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adicionarPaciente(user_id);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (paciente == null) {

        }else{
            setTitle("Dados pessoais");
        }
    }

    private void adicionarPaciente(long user_id) {
        if(dadosPreenchidos()){
            paciente = new Paciente(0, user_id, etNome.getText().toString(),
                    etSexo.getText().toString(), etNacionalidade.getText().toString(),
                    etLocalidade.getText().toString(), Integer.parseInt(etTelemovel.getText().toString()),
                    Double.parseDouble(etPeso.getText().toString()), Double.parseDouble(etAltura.getText().toString()));
            Singleton.getInstance(getApplicationContext()).adicionarPacienteAPI(paciente, getApplicationContext());
        }
    }


    private void alterarPaciente() {
        if(dadosPreenchidos()){
            paciente.setNome(etNome.getText().toString());
            paciente.setSexo(etSexo.getText().toString());
            paciente.setNacionalidade(etNacionalidade.getText().toString());
            paciente.setLocalidade(etLocalidade.getText().toString());
            paciente.setTelemovel(Integer.parseInt(etTelemovel.getText().toString()));
            paciente.setPeso(Double.parseDouble(etPeso.getText().toString()));
            paciente.setAltura(Double.parseDouble(etAltura.getText().toString()));

            Singleton.getInstance(getApplicationContext()).editarPacienteAPI(paciente, getApplicationContext());
        }
    }

    private boolean dadosPreenchidos() {
        if(etNome.getText() == null || etNome.getText().toString().trim().equals("")){
            etNome.setError("Preencha todos os campos corretamente antes de avançar.");
            return false;
        }else if(etSexo.getText() == null || etSexo.getText().toString().trim().equals("")){
            etSexo.setError("Preencha todos os campos corretamente antes de avançar.");
            return false;
        }else if(etNacionalidade.getText() == null || etNacionalidade.getText().toString().trim().equals("")){
            etNacionalidade.setError("Preencha todos os campos corretamente antes de avançar.");
            return false;
        }else if(etLocalidade.getText() == null || etLocalidade.getText().toString().trim().equals("")){
            etLocalidade.setError("Preencha todos os campos corretamente antes de avançar.");
            return false;
        }else if(etTelemovel.getText() == null || etTelemovel.getText().toString().trim().equals("")){
            etTelemovel.setError("Preencha todos os campos corretamente antes de avançar.");
            return false;
        }
        return true;
    }

    @Override
    public void onAddPaciente() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPatchPaciente() {
        Intent intent = new Intent(getApplicationContext(), MenuMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConfirmPaciente(Paciente paciente){
        etNome.setText(paciente.getNome());
        etSexo.setText(paciente.getSexo());
        etNacionalidade.setText(paciente.getNacionalidade());
        etLocalidade.setText(paciente.getLocalidade());
        etTelemovel.setText(paciente.getTelemovel()+"");
        etPeso.setText(String.format("%4.1f", paciente.getPeso()));
        etAltura.setText(String.format("%3.2f", paciente.getAltura()));
        this.paciente = paciente;
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
        final long id_user = Long.parseLong(sharedPreferences.getString(MenuMainActivity.ID_USER, "-1"));

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

                if(isPasswordValida(alterar)){
                    if(passwordsMatch(alterar, confirmar)){
                        Singleton.getInstance(getApplicationContext()).alterarPassword(getApplicationContext(), id_user, alterar);
                    }else{
                        Toast.makeText(getApplicationContext(), "Passwords não coincidem", Toast.LENGTH_SHORT).show();
                        etConfirmar.setText("");
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Password com minimo de 4 caracteres", Toast.LENGTH_SHORT).show();
                    etAlterar.setText("");
                    etConfirmar.setText("");
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
        final long id_user = Long.parseLong(sharedPreferences.getString(MenuMainActivity.ID_USER, "-1"));

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
                alterar = etAlterar.getText().toString();
                confirmar = etConfirmar.getText().toString();

                if(isEmailValido(alterar)){
                    if(emailsMatch(alterar, confirmar)){
                        Singleton.getInstance(getApplicationContext()).alterarEmail(getApplicationContext(), id_user, alterar);
                    }else{
                        Toast.makeText(getApplicationContext(), "Emails não coincidem", Toast.LENGTH_SHORT).show();
                        etConfirmar.setText("");
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Email inválido", Toast.LENGTH_SHORT).show();
                    etAlterar.setText("");
                    etConfirmar.setText("");
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

    private boolean emailsMatch(String email, String confirm) {
        if(email.equals(confirm))
            return true;
        return false;
    }

    private boolean isEmailValido(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean passwordsMatch(String password, String confirm) {
        if(password.equals(confirm))
            return true;
        return false;
    }

    private boolean isPasswordValida(String password) {
        return password.length() >= 4;
    }

}