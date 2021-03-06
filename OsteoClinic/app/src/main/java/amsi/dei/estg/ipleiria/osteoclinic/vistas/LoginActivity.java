package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.LoginListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;
import amsi.dei.estg.ipleiria.osteoclinic.utils.ClinicJsonParser;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private EditText etEmail, etPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.etEmail = findViewById(R.id.etEmail);
        this.etPassword = findViewById(R.id.etPassword);

        sharedPreferences = this.getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean(MenuMainActivity.LOGIN_BOOL, false)){
            Intent intent = new Intent(getApplicationContext(), MenuMainActivity.class);
            startActivity(intent);
            finish();
        }

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
    public void onValidateLogin(String dados, String email) {
        if(dados != null && !dados.equals("")) {
            saveSharedPreferencesInfo(dados, email);
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

    private void saveSharedPreferencesInfo(String dados, String email) {
        StringTokenizer st = new StringTokenizer(dados, ";");
        String token = st.nextToken();
        String id_user = st.nextToken();
        String id_paciente = st.nextToken();
        getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MenuMainActivity.LOGIN_BOOL, true);
        editor.putString(MenuMainActivity.EMAIL, email);
        editor.putString(MenuMainActivity.TOKEN, token);
        editor.putString(MenuMainActivity.ID_USER, id_user);
        editor.putString(MenuMainActivity.ID_PACIENTE, id_paciente);
        editor.apply();
    }
}
