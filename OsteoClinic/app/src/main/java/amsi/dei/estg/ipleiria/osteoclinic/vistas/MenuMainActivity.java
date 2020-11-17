package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class MenuMainActivity extends AppCompatActivity {

    public static final String EMAIL = "amsi.dei.estg.ipleiria.osteoclinic.vistas.email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
    }
}