package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.LoginListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;
import amsi.dei.estg.ipleiria.osteoclinic.utils.ClinicJsonParser;

public class RegistoActivity extends AppCompatActivity implements LoginListener {

    private EditText etEmail, etPassword, etConfirmarEmail, etConfirmarPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        this.etEmail = findViewById(R.id.etEmail);
        this.etConfirmarEmail = findViewById(R.id.etConfirmarEmail);
        this.etPassword = findViewById(R.id.etPassword);
        this.etConfirmarPassword = findViewById(R.id.etConfirmarPassword);

        Singleton.getInstance(getApplicationContext()).setLoginListener(this);
    }

    public void onRegistoClick(View view){
        if(!ClinicJsonParser.isConnected(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "Não tem ligação à internet", Toast.LENGTH_SHORT).show();
        }

        if(isEmailValido(etEmail.getText().toString())){
                if(emailsMatch(etEmail.getText().toString(), etConfirmarEmail.getText().toString())){
                    if(isPasswordValida(etPassword.getText().toString()) && isPasswordValida(etConfirmarPassword.getText().toString())){
                        if(passwordsMatch(etPassword.getText().toString(), etConfirmarPassword.getText().toString())){
                            Singleton.getInstance(getApplicationContext()).registarUtilizador(getApplicationContext(),
                                    etEmail.getText().toString(), etPassword.getText().toString());
                        }else{
                            Toast.makeText(getApplicationContext(), "Passwords não coincidem", Toast.LENGTH_SHORT).show();
                            etPassword.setText("");
                            etConfirmarPassword.setText("");
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password inválida (min 4 caracteres)", Toast.LENGTH_SHORT).show();
                        etPassword.setText("");
                        etConfirmarPassword.setText("");
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Emails não coincidem", Toast.LENGTH_SHORT).show();
                    etConfirmarEmail.setText("");
                }
        }else{
            Toast.makeText(getApplicationContext(), "Email inválido", Toast.LENGTH_SHORT).show();
            etEmail.setText("");
            etConfirmarEmail.setText("");
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

    @Override
    public void onValidateLogin(String token, String email) {

    }

    @Override
    public void onValidateRegisto(long id, String email) {
        if(id > 0){
            Bundle b = new Bundle();
            b.putString("mail", email);
            b.putLong("user_id", id);
            b.putString("tarefa", "adicionar");
            Intent intent = new Intent(getApplicationContext(), DetalhesPacienteActivity.class);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
    }
}