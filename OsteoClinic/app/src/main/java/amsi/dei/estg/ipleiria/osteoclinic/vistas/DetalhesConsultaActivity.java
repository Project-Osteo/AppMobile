package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesConsultaActivity extends AppCompatActivity {

    public static final String ID_CONSULTA = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID";

    private TextView tvDataConsulta, tvDescricao, tvRecomendacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_consulta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDataConsulta = findViewById(R.id.tvDataConsultaDetalhe);
        tvDescricao = findViewById(R.id.tvDescricaoConsultaDetalhe);
        tvRecomendacoes = findViewById(R.id.tvRecomendacoesDetalhe);

        long id = getIntent().getLongExtra(ID_CONSULTA, -1);
        Consulta consulta = Singleton.getInstance(getApplicationContext()).getConsulta(id);

        if(consulta != null){
            setTitle("Consultas");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            tvDataConsulta.setText(formatter.format(consulta.getDataConsulta()));
            tvDescricao.setText(consulta.getDescricao_consulta());
            tvRecomendacoes.setText(consulta.getRecomendacao());
        }
    }
}