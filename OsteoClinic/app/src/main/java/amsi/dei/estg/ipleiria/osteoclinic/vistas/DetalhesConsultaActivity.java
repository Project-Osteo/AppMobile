package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;
import amsi.dei.estg.ipleiria.osteoclinic.utils.ClinicJsonParser;

public class DetalhesConsultaActivity extends AppCompatActivity {

    public static final String ID_CONSULTA = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID_CONSULTA";

    private TextView tvDataConsulta, tvDescricao, tvRecomendacoes;

    private Button btFeedback;

    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_consulta);

        tvDataConsulta = findViewById(R.id.tvDataConsultaDetalhe);
        tvDescricao = findViewById(R.id.tvDescricaoConsultaDetalhe);
        tvRecomendacoes = findViewById(R.id.tvRecomendacoesDetalhe);

        btFeedback = findViewById(R.id.btListaFeedbackDetalheConsulta);

        if(savedInstanceState != null){
            id = savedInstanceState.getLong("id_consulta");
        }else{
            id = getIntent().getLongExtra(ID_CONSULTA, -1);
        }

        carregarConsulta(id);

        btFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaFeedbackFragment listaFeedbackFragment = new ListaFeedbackFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contentFeedback, listaFeedbackFragment).commit();
            }
        });

    }


//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putLong("id_consulta", id);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        id = savedInstanceState.getLong("id_consulta");
//        carregarConsulta(id);
//    }

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


    public long getIdConsulta(){
        return id;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getIntent().getStringExtra(DetalhesFeedbackActivity.RESPOSTA);
        long id = getIntent().getLongExtra(DetalhesConsultaActivity.ID_CONSULTA, -1);
        carregarConsulta(id);
    }
}