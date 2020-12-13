package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;

public class DetalhesConsultaActivity extends AppCompatActivity {

    public static final String ID_CONSULTA = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID";

    private TextView tvNumConsulta, tvDataConsulta, tvTerapeuta, tvDescricao, tvRecomendacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_consulta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNumConsulta = findViewById(R.id.tvNumConsultaDetalhe);
        tvDataConsulta = findViewById(R.id.tvDataConsultaDetalhe);
        tvTerapeuta = findViewById(R.id.tvTerapeutaDetalhe);
        tvDescricao = findViewById(R.id.tvDescricaoConsultaDetalhe);
        tvRecomendacoes = findViewById(R.id.tvRecomendacoesDetalhe);

        long id = getIntent().getLongExtra(ID_CONSULTA, -1);
        Consulta consulta = Singleton.getInstance().getConsulta(id);

        if(consulta != null){
            setTitle("Consultas");
            tvNumConsulta.setText(""+consulta.getId());
            tvDataConsulta.setText(consulta.getDataConsulta().toString());
            tvTerapeuta.setText("OsteoFla");
            tvDescricao.setText(consulta.getDescricao());
            tvRecomendacoes.setText(consulta.getRecomendacao());
        }
    }
}