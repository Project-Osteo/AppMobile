package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import java.text.SimpleDateFormat;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Singleton;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Treino;

public class DetalhesTreinoActivity extends AppCompatActivity {

    public static final String ID_TREINO = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID";

    private TextView tvTipoTreino, tvDataTreino, tvDescricaoTreino, tvRecomendacoesTreino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_treino);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvTipoTreino = findViewById(R.id.tvTipoTreinoDetalhe);
        tvDataTreino = findViewById(R.id.tvDataTreinoDetalhe);
        tvDescricaoTreino = findViewById(R.id.tvDescricaoTreinoDetalhe);
        tvRecomendacoesTreino = findViewById(R.id.tvRecomendacoesTreinoDetalhe);

        long id = getIntent().getLongExtra(ID_TREINO, -1);
        Treino treino = Singleton.getInstance(getApplicationContext()).getTreino(id);

        if(treino != null){
            setTitle("Treinos");
            tvTipoTreino.setText(treino.getTipoTreino());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            tvDataTreino.setText(formatter.format(treino.getDataTreino()));
            tvDescricaoTreino.setText(treino.getDescricao_treino());
            tvRecomendacoesTreino.setText(treino.getObs_treino());
        }

    }
}