package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesFeedbackActivity extends AppCompatActivity {

    public static final String ID_FEEDBACK = "amsi.dei.estg.ipleiria.osteoclinic.vistas";

    public static final int DETALHE_ADICIONAR = 1;
    public static final int DETALHE_EDITAR = 2;
    public static final String RESPOSTA = "resposta";

    private EditText etData, etMensagem;

    private FloatingActionButton fab_action;

    private Feedback feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etData = findViewById(R.id.etDataFeedbackDetalhe);
        etMensagem = findViewById(R.id.etMensagemFeedbackDetalhe);
        fab_action = findViewById(R.id.fab_action);

        int id = (int)getIntent().getLongExtra(ID_FEEDBACK, -1);

        if(id == -1){
            feedback = null;
        }
        else{
            Singleton gestor = Singleton.getInstance(getApplicationContext());
            feedback = gestor.getFeedback(id);
        }

        if(feedback == null){
            setTitle("Novo Feedback");
            fab_action.setImageResource(R.drawable.ic_action_add_foreground);
        }
    }
}