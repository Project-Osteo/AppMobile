package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;
import amsi.dei.estg.ipleiria.osteoclinic.utils.ClinicJsonParser;

public class RegistoActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etConfirmarEmail, etConfirmarPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        this.etEmail = findViewById(R.id.etEmail);
        this.etConfirmarEmail = findViewById(R.id.etConfirmarEmail);
        this.etPassword = findViewById(R.id.etPassword);
        this.etConfirmarPassword = findViewById(R.id.etConfirmarPassword);

    }

    public void onRegistoClick(View view){
        if(!ClinicJsonParser.isConnected(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "Não tem ligação à internet", Toast.LENGTH_SHORT).show();
        }

        if(isEmailValido(etEmail.getText().toString()) && isEmailValido(etConfirmarEmail.getText().toString())){
                if(emailsMatch(etEmail.getText().toString(), etConfirmarEmail.getText().toString())){
                    if(isPasswordValida(etPassword.getText().toString()) && isPasswordValida(etConfirmarPassword.getText().toString())){
                        if(passwordsMatch(etPassword.getText().toString(), etConfirmarPassword.getText().toString())){
                            Singleton.getInstance(getApplicationContext()).registarUtilizador(getApplicationContext(),
                                    etEmail.getText().toString(), etPassword.getText().toString());
                        }
                    }
                }
        }
    }

    private boolean passwordsMatch(String password, String confirm) {
        if(password.equals(confirm))
            return true;
        return false;
    }

    private boolean emailsMatch(String email, String confirm) {
        if(email.equals(confirm))
            return true;
        return false;
    }

    private boolean isEmailValido(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValida(String password) {
        return password.length() >= 4;
    }
}