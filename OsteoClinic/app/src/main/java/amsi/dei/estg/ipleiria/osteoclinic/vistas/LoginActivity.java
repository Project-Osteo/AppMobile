package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.LoginListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;
import amsi.dei.estg.ipleiria.osteoclinic.utils.ClinicJsonParser;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.etEmail = findViewById(R.id.etEmail);
        this.etPassword = findViewById(R.id.etPassword);

        etEmail.setText("jota@mail.pt");
        etPassword.setText("1234");

        Singleton.getInstance(getApplicationContext()).setLoginListener(this);

    }

    public void onLoginClick(View view) {
        if(!ClinicJsonParser.isConnected(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "Não tem ligação à internet", Toast.LENGTH_SHORT).show();
        }

        if(isEmailValido(etEmail.getText().toString())) {
            if(isPasswordValida(etPassword.getText().toString())) {
                Singleton.getInstance(getApplicationContext()).loginAPI(getApplicationContext(),
                        etEmail.getText().toString(), etPassword.getText().toString());
            }
            else {
                etPassword.setError("Palavra passe incorreta! \nTem de ter pelo menos 4 caracteres!");
            }
        }
        else {
            etEmail.setError("O valor introduzido não é um email!");
        }
    }

    public void onClickRegisto(View view) {
        Intent intent = new Intent(this, RegistoActivity.class);
        startActivity(intent);
    }


    private boolean isEmailValido(String mail) {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    private boolean isPasswordValida(String password) {
        return password.length() >= 4;
    }



    @Override
    public void onValidateLogin(String token, String email) {
        if(token != null) {
            saveSharedPreferencesInfo(token, email);
            Intent intent = new Intent(getApplicationContext(), MenuMainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Login inválido", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
        }
    }

    @Override
    public void onValidateRegisto(long id, String email) {

    }

    private void saveSharedPreferencesInfo(String token, String email) {
        SharedPreferences sharedPreferences = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MenuMainActivity.EMAIL, email);
        editor.putString(MenuMainActivity.TOKEN, token);
        editor.apply();
    }
}

//NOTA 1:perguntar ao professor como fazer com que o Login seja a atividade principal caso o utilizador não tenha sessão iniciada,
//caso o login tinha sido feito, a aplicação devia ser iniciada no menu principal

//NOTA 2: verificar o aspeto da hiperligação do registo, ponderar se deve ser aplicado um botão em vez de textview