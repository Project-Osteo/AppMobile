package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class DetalhesTreinoActivity extends AppCompatActivity {

    private static final String ID_TREINO = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID";

    private TextView tvTipoTreino, tvDataTreino, tvTerapeutaTreino, tvDescricaoTreino, tvRecomendacoesTreino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_treino);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTipoTreino = findViewById(R.id.tvTipoTreinoDetalhe);
        tvDataTreino = findViewById(R.id.tvDataTreinoDetalhe);
        tvTerapeutaTreino = findViewById(R.id.tvTerapeutaDetalheTreino);
        tvDescricaoTreino = findViewById(R.id.tvDescricaoTreinoDetalhe);
        tvRecomendacoesTreino = findViewById(R.id.tvRecomendacoesTreinoDetalhe);
        
    }
}