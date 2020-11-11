package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}

//perguntar ao professor como fazer com que o Login seja a atividade principal caso o utilizador não tenha sessão iniciada,
//caso o login tinha sido feito, a aplicação devia ser iniciada no menu principal