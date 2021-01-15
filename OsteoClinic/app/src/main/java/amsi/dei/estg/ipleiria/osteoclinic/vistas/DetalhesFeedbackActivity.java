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
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.FeedbacksListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesFeedbackActivity extends AppCompatActivity implements FeedbacksListener {

    public static final String ID_FEEDBACK = "amsi.dei.estg.ipleiria.osteoclinic.vistas";

    public static final int DETALHE_ADICIONAR = 1;
    public static final int DETALHE_EDITAR = 2;
    public static final String RESPOSTA = "resposta";


    private TextView tvDataHora;
    private EditText etMensagem;
    private FloatingActionButton fab_action;
    private Feedback feedback;
    private String token;
    private long id_consulta;
    private String tarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvDataHora = findViewById(R.id.tvDataHora);
        etMensagem = findViewById(R.id.etMensagemFeedbackDetalhe);
        fab_action = findViewById(R.id.fab_action);

        long id = getIntent().getLongExtra(ID_FEEDBACK, -1);
        id_consulta = getIntent().getLongExtra("id_consulta", -1);

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

        Singleton.getInstance(getApplicationContext()).setFeedbackListener(this);
    }



    private void adicionarFeedback() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            if(dadosPreenchidos()){
                feedback = new Feedback(0, id_consulta, etMensagem.getText().toString(),
                        formatter.parse(tvDataHora.getText().toString().substring(0,10)));
                Singleton.getInstance(getApplicationContext()).adicionarFeedbackAPI(feedback, token, getApplicationContext());
                tarefa = "Adicionou";
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void alterarFeedback() {
        if(dadosPreenchidos()){
            feedback.setMensagem(etMensagem.getText().toString());
            Singleton.getInstance(getApplicationContext()).editarFeedbackAPI(feedback, token, getApplicationContext());
            tarefa = "Alterou";
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
                .setMessage("Deseja mesmo remover o livro ?")
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
        intentresposta.putExtra("id_consulta", id_consulta);
        setResult(RESULT_OK, intentresposta);
        finish();
    }
}