package com.teca.dudu.triptogether.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.layout.DespesasFragment;
import com.teca.dudu.triptogether.layout.UsuariosFragment;
import com.teca.dudu.triptogether.layout.ViagemFragment;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.teste.ViagemTeste;
import com.teca.dudu.triptogether.util.CircleBitmap;
import com.teca.dudu.triptogether.util.RoundImage;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ViagemTeste viagem;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    UsuarioDao usuarioDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viagem = new ViagemTeste();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0); //PEGA A VIEW NO NAV_DRAWER

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.ID_file_key),Context.MODE_PRIVATE);
        int id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);

        usuarioDao = new UsuarioDao(this);

        ImageView imgPerfil = (ImageView) hView.findViewById(R.id.nav_d_image_view); //BUSCA NA VIEW DO NAV_DRAWER A IMAGEM
        TextView nomePerfil = (TextView) hView.findViewById(R.id.nav_name_tv);
        TextView emailPerfil = (TextView) hView.findViewById(R.id.nav_email_tv);

        Usuario user = usuarioDao.buscarUsuarioPorId(id_usuario);

        nomePerfil.setText(user.getNome());
        emailPerfil.setText(user.getEmail());
        if(user.getImgPerfil() != null) {
            Bitmap img = BitmapFactory.decodeByteArray(user.getImgPerfil(), 0, user.getImgPerfil().length); //Transforma o byteArray em bitmap
            CircleBitmap circle = new CircleBitmap();
            RoundImage round = new RoundImage(img);
            if (img != null && imgPerfil != null) { // se nenhum deles for nulo mostra no nav_drawer
                imgPerfil.setImageBitmap(circle.getRoundedShape(img));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_despesa) {
            Intent intent = new Intent(this, AddDespesaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_home) {
            if(MainActivity.class != this.getClass()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_add_integrante) {
            Intent intent = new Intent(this, AddIntegranteActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_lista_viagens){
            Intent  intent = new Intent(this, ViagemActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_perfil) {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            SharedPreferences sharedPref = getSharedPreferences(
                    getString(R.string.ID_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor spEditor = sharedPref.edit();
            spEditor.clear();
            spEditor.apply();


            Intent intentmain = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intentmain);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position+1);


            switch(position) {
                case 0:
                    return new UsuariosFragment().newInstance(position +1);
                case 1:
                    return new ViagemFragment().newInstance(position +1);
                case 2:
                    return new DespesasFragment().newInstance(position +1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_integrantes_str);
                case 1:
                    return getString(R.string.tab_viagem_str);
                case 2:
                    return getString(R.string.tab_atividade_str);
            }
            return null;
        }
    }
}
