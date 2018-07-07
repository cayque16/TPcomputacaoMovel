package br.ufop.cayque.mybabycayque;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import br.ufop.cayque.mybabycayque.add.AddMedicamentosActivity;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.DadosBebe;
import br.ufop.cayque.mybabycayque.notificacao.TelaNotificacao;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView nomeBebe;
    private NavigationView navigationView;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);

        nomeBebe = headerView.findViewById(R.id.textNavNomeBebe);

        setTitle("Home");

        DadosBebe.loadBebe(this);

        if (DadosBebe.getInstance().getBebeNull() == 1) {

        } else {
            nomeBebe.setText(DadosBebe.getInstance().getNome());
        }

        HistoricoSingleton.getInstance().loadAtividades(this);
        HistoricoSingleton.getInstance().loadOutros(this);
        HistoricoSingleton.getInstance().loadMedicamentos(this);
        HistoricoSingleton.getInstance().loadFraldas(this);
        HistoricoSingleton.getInstance().loadMamadeiras(this);
        HistoricoSingleton.getInstance().loadMamadas(this);
        HistoricoSingleton.getInstance().loadSoneca(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(
                    R.id.frame_container, new HomeFragment()).commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            this.setTitle("Home");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new HomeFragment())
                    .commit();
        } else if (id == R.id.nav_dadosBebe) {
            this.setTitle("Dados do Bebê");
            //joga o fragmento na tela
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new DadosBebeFragment())
                    .commit();
        } else if (id == R.id.nav_mamadas) {
            this.setTitle("Mamadas");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new MamadasFragment())
                    .commit();
        } else if (id == R.id.nav_mamadeiras) {
            this.setTitle("Mamadeiras");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new MamadeirasFragment())
                    .commit();
        } else if (id == R.id.nav_fralda) {
            this.setTitle("Fralda Suja");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new FraldaSujaFragment())
                    .commit();
        } else if (id == R.id.nav_tempoDormindo) {
            this.setTitle("Sonecas");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new SonecaFragment())
                    .commit();
        } else if (id == R.id.nav_medicamentos) {
            this.setTitle("Medicamentos");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new MedicamentosFragment())
                    .commit();
        } else if (id == R.id.nav_outros) {
            this.setTitle("Outros");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new OutrosFragment())
                    .commit();
        } else if (id == R.id.nav_somUtero) {
            Intent it = new Intent(this, SomUteroActivity.class);
            startActivity(it);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (TelaNotificacao.mNotificationManager != null) {
            TelaNotificacao.mNotificationManager.cancel(0);
        }
    }
}