package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaFeedbackAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.FeedbacksListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesFeedbackActivity extends AppCompatActivity implements FeedbacksListener {

    public static final String ID_FEEDBACK = "amsi.dei.estg.ipleiria.osteoclinic.vistas.id_feedback";
    public static final String CONSULTA_ID = "amsi.dei.estg.ipleiria.osteoclinic.vistas.consulta_id";

    public static final int DETALHE_ADICIONAR = 1;
    public static final int DETALHE_EDITAR = 2;
    public static final String RESPOSTA = "resposta";

    private TextView tvDataHora;
    private EditText etMensagem;
    private FloatingActionButton fab_action;
    private Button btVoltar;
    private Feedback feedback;
    private long consulta_id;
    private String tarefa;
    private long id_feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_feedback);

        tvDataHora = findViewById(R.id.tvDataHoraFeedback);
        etMensagem = findViewById(R.id.etMensagemFeedbackDetalhe);
        fab_action = findViewById(R.id.fab_action);
        btVoltar = findViewById(R.id.btVoltar);

        id_feedback = getIntent().getLongExtra(ID_FEEDBACK, -1);
        consulta_id = getIntent().getLongExtra(CONSULTA_ID, -1);

        if(id_feedback == -1){
            feedback = null;
        }
        else{
            Singleton gestor = Singleton.getInstance(getApplicationContext());
            feedback = gestor.getFeedback(id_feedback);
        }

        if(feedback == null){
            setTitle("Novo Feedback");
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:MM:ss / yyyy-MM-dd");
            tvDataHora.setText(formatter.format(currentTime));
            fab_action.setImageResource(R.drawable.ic_action_add);
        }
        else{
            setTitle("Feedback");
            preencheDetalhe(feedback);
            fab_action.setImageResource(R.drawable.ic_action_save);
        }

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(DetalhesConsultaActivity.ID_CONSULTA, consulta_id);
                intent.putExtra(DetalhesFeedbackActivity.RESPOSTA, "voltar");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

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

        Singleton.getInstance(getApplicationContext()).setFeedbackListener(this);
    }



    private void adicionarFeedback() {
        if(dadosPreenchidos()){
            feedback = new Feedback(0, consulta_id, etMensagem.getText().toString());
            Singleton.getInstance(getApplicationContext()).adicionarFeedbackAPI(getApplicationContext(), feedback);
            tarefa = "Adicionou";
        }
    }

    private void alterarFeedback() {
        if(dadosPreenchidos()){
            feedback.setMensagem(etMensagem.getText().toString());
            Singleton.getInstance(getApplicationContext()).editarFeedbackAPI(getApplicationContext(),
                    feedback);
            tarefa = "Editou";
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        tvDataHora.setText(formatter.format(feedback.getDatahora()));
        etMensagem.setText(feedback.getMensagem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(feedback != null){
            MenuInflater inflater = getMenuInflater();

            inflater.inflate(R.menu.menu_detalhes_feedback, menu);

            return super.onCreateOptionsMenu(menu);
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btRemover){
            dialogRemover();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dialogRemover() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar Feedback ?")
                .setMessage("Deseja mesmo remover o feedback ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Singleton.getInstance(getApplicationContext()).removerFeedbackAPI(feedback, getApplicationContext());
                        tarefa = "Apagou";
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

    @Override
    public void onRefreshListaFeedbacks(ArrayList<Feedback> listafeedback) {

    }

    @Override
    public void onRefreshDetalhes() {
        Intent intentresposta = new Intent();
        intentresposta.putExtra(RESPOSTA, tarefa);
        intentresposta.putExtra(DetalhesConsultaActivity.ID_CONSULTA, consulta_id);
        setResult(RESULT_OK, intentresposta);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra(DetalhesConsultaActivity.ID_CONSULTA, consulta_id);
        intent.putExtra(DetalhesFeedbackActivity.RESPOSTA, "voltar");
        setResult(RESULT_OK, intent);
        finish();
    }
}