package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class DetalhesFeedbackActivity extends AppCompatActivity {

    public static final String ID_FEEDBACK = "amsi.dei.estg.ipleiria.osteoclinic.vistas";

    public static final int DETALHE_ADICIONAR = 1;
    public static final int DETALHE_EDITAR = 2;
    public static final String RESPOSTA = "resposta";

    private EditText etData, etMensagem;

    private FloatingActionButton fab_action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etData = findViewById(R.id.etDataFeedbackDetalhe);
        etMensagem = findViewById(R.id.etMensagemFeedbackDetalhe);
        fab_action = findViewById(R.id.fab_action);

        
    }
}