package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.adaptadores.ListaFeedbackAdapter;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.FeedbacksListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesConsultaActivity extends AppCompatActivity /*implements FeedbacksListener*/ {

    public static final String ID_CONSULTA = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID_CONSULTA";

    private TextView tvDataConsulta, tvDescricao, tvRecomendacoes;

    private Button btFeedback;

    private long id;

    private SharedPreferences sharedPreferences;

    //private ListView listviewfeedback;
    //private ListaFeedbackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_consulta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDataConsulta = findViewById(R.id.tvDataConsultaDetalhe);
        tvDescricao = findViewById(R.id.tvDescricaoConsultaDetalhe);
        tvRecomendacoes = findViewById(R.id.tvRecomendacoesDetalhe);

        btFeedback = findViewById(R.id.btListaFeedbackDetalheConsulta);

        id = getIntent().getLongExtra(ID_CONSULTA, -1);

        carregarConsulta(id);

        preencherListaFeedbacks();

        /*listviewfeedback = findViewById(R.id.listview_feedbackDetalheConsulta);
        Singleton gestor = Singleton.getInstance(getApplicationContext());

        try{
            adapter = new ListaFeedbackAdapter(this, gestor.getListaFeedbackBD());
        }catch (ParseException e){
            e.printStackTrace();
        }
        listviewfeedback.setAdapter(adapter);

        listviewfeedback.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetalhesFeedbackActivity.class);
                intent.putExtra(DetalhesFeedbackActivity.ID_FEEDBACK, id);
                startActivity(intent);
            }
        });

        Singleton.getInstance(this).setFeedbackListener(this);
        Singleton.getInstance(this).getAllFeedbacksAPI(this);*/

        btFeedback.getContext();

    }

    private void carregarConsulta(long id) {
        Consulta consulta = Singleton.getInstance(getApplicationContext()).getConsulta(id);

        if(consulta != null){
            setTitle("Consultas");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            tvDataConsulta.setText(formatter.format(consulta.getDataConsulta()));
            tvDescricao.setText(consulta.getDescricao_consulta());
            tvRecomendacoes.setText(consulta.getRecomendacao());
        }
    }

    public void preencherListaFeedbacks() {
        ListaFeedbackFragment listaFeedbackFragment = new ListaFeedbackFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentFeedback, listaFeedbackFragment).commit();
    }

    public long getIdConsulta(){
        return id;
    }

    /*@Override
    public void onRefreshListaFeedbacks(ArrayList<Feedback> listafeedback) {
        if(listafeedback != null) {
            listviewfeedback.setAdapter(new ListaFeedbackAdapter(this, listafeedback));
        }
    }

    @Override
    public void onRefreshDetalhes() {

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        long id = data.getLongExtra("id_consulta", -1);
        carregarConsulta(id);
    }
}