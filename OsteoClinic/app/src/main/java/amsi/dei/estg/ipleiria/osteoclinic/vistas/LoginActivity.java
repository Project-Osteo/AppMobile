package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.etEmail = findViewById(R.id.etEmail);
        this.etPassword = findViewById(R.id.etPassword);

    }

    public void Login(View view) {
        if(isEmailValido(etEmail.getText().toString())) {
            if(isPasswordValida(etPassword.getText().toString())) {
                Intent intentMain = new Intent(this, MenuMainActivity.class);
                intentMain.putExtra(MenuMainActivity.EMAIL, etEmail.getText().toString());
                startActivity(intentMain);
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

}

//NOTA 1:perguntar ao professor como fazer com que o Login seja a atividade principal caso o utilizador não tenha sessão iniciada,
//caso o login tinha sido feito, a aplicação devia ser iniciada no menu principal

//NOTA 2: verificar o aspeto da hiperligação do registo, ponderar se deve ser aplicado um botão em vez de textview