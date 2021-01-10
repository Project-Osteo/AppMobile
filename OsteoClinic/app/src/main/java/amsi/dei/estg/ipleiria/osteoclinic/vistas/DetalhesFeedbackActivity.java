package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    private String token;


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
            fab_action.setImageResource(R.drawable.ic_action_add);
        }
        else{
            setTitle("Feedback");
            preencheDetalhe(feedback);
            fab_action.setImageResource(R.drawable.ic_action_save);
        }

        fab_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedback == null){
                    adicionarFeedback();
                }
                else{
                    alterarFeedback();
                }
            }
        });
    }



    private void adicionarFeedback() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            if(dadosPreenchidos()){
                feedback = new Feedback(0, 0, 0, etMensagem.getText().toString(),
                        formatter.parse(etData.getText().toString().substring(0,10)));
                Singleton.getInstance(getApplicationContext()).adicionarFeedbackAPI(feedback, token, getApplicationContext());
        }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void alterarFeedback() {
        if(dadosPreenchidos()){
            feedback.setMensagem(etMensagem.getText().toString());
            Singleton.getInstance(getApplicationContext()).editarFeedbackAPI(feedback, token, getApplicationContext());
        }
    }

    private boolean dadosPreenchidos(){
        if(etMensagem.getText() == null || etMensagem.getText().toString().trim().equals("")){
            etMensagem.setError("Mensagem inválida");
            return false;
        }
        return true;
    }

    private void preencheDetalhe(Feedback feedback) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        etData.setText(formatter.format(feedback.getDatahora()));
        etMensagem.setText(feedback.getMensagem());
    }

}