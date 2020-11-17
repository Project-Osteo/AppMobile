package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        

    }

    public void onClickRegisto(View view) {
        Intent intent = new Intent(this, RegistoActivity.class);
        startActivity(intent);
    }
}

//NOTA 1:perguntar ao professor como fazer com que o Login seja a atividade principal caso o utilizador não tenha sessão iniciada,
//caso o login tinha sido feito, a aplicação devia ser iniciada no menu principal

//NOTA 2: verificar o aspeto da hiperligação do registo, ponderar se deve ser aplicado um botão em vez de textview