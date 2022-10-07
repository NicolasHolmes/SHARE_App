package com.example.financasapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financasapp.databinding.ActivityPrincipalBinding;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterMovimentacao;
import model.Movimentacao;
import model.Usuario;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPrincipalBinding binding;
    private TextView textViewUsuario;
    private Usuario usuario;
    private List<Movimentacao> listaMovimentacoes = new ArrayList();
    private AdapterMovimentacao adapterMovimentacao;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        textViewUsuario = findViewById(R.id.textViewUsuario);
        recyclerView = findViewById(R.id.recyclerMovimentos);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        carregaResumo();
        carregaMovimentacoes();

        binding.fabAdicionarDoacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaDoacao(view);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void carregaResumo(){

        textViewUsuario.setText("Olá " + usuario.getNome() + " !");

        Movimentacao m1 = new Movimentacao();
        m1.setCategoria("Ganhos Mensais");
        m1.setDescricao("Salário");
        m1.setValor(5000.00);
        m1.setTipo("r");

        listaMovimentacoes.add(m1);

        Movimentacao m2 = new Movimentacao();
        m2.setCategoria("Gasto Fixo");
        m2.setDescricao("Energia Elétrica");
        m2.setValor(250.00);
        m2.setTipo("d");

        listaMovimentacoes.add(m2);

    }

    public void abrirTelaDoacao(View view){
        startActivity(new Intent(this, DoacaoActivity.class));
    }

    public void carregaMovimentacoes(){

        adapterMovimentacao = new AdapterMovimentacao(listaMovimentacoes, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapterMovimentacao);
    }
}