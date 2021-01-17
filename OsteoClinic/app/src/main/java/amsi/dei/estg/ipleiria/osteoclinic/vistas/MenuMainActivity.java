package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import amsi.dei.estg.ipleiria.osteoclinic.R;

public class MenuMainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    public static final String PREF_USER = "amsi.dei.estg.ipleiria.osteoclinic.vistas.PREF_USER";
    public static final String EMAIL = "amsi.dei.estg.ipleiria.osteoclinic.vistas.email";
    public static final String TOKEN = "amsi.dei.estg.ipleiria.osteoclinic.vistas.token";
    public static final String ID_USER = "amsi.dei.estg.ipleiria.osteoclinic.vistas.id_user";
    public static final String ID_PACIENTE = "amsi.dei.estg.ipleiria.osteoclinic.vistas.id_paciente";

    private NavigationView navigationView;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.ndOpen, R.string.ndClose);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        carregarFragmentoInicial();

        navigationView.setNavigationItemSelectedListener(this);


    }

    private void carregarFragmentoInicial() {
        Fragment fragmento = new InicialFragment();
        if(fragmento != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragmento).commit();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragmento = null;

        switch(item.getItemId()){
            case R.id.nav_inicio:
                fragmento = new InicialFragment();
                break;
            case R.id.nav_consultas:
                fragmento = new ListaConsultasFragment();
                break;
            case R.id.nav_treinos:
                fragmento = new ListaTreinosFragment();
                break;
            case R.id.nav_user_profile:
                Bundle b = new Bundle();
                b.putString("tarefa", "editar");
                Intent intent = new Intent(getApplicationContext(), DetalhesPacienteActivity.class);
                intent.putExtras(b);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

        if(fragmento != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragmento).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}