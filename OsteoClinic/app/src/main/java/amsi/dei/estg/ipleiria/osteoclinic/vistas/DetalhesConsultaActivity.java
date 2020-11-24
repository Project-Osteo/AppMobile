package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class DetalhesConsultaActivity extends AppCompatActivity {

    public static final String ID = "amsi.dei.estg.ipleiria.osteoclinic.vistas.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_consulta);
    }
}