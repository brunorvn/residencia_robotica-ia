package br.ufpe.cin.residencia.banco.conta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.residencia.banco.BancoDB;
import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class ContasActivity extends AppCompatActivity {
    ContaAdapter adapter;
    BancoDB db;
    ContaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.rvContas);
        adapter = new ContaAdapter(getLayoutInflater());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        Button adicionarConta = findViewById(R.id.btn_Adiciona);

        // atualizar lista na tela
        viewModel.contas.observe(
                this,
                novaLista ->{
                    Log.d("BANCODEDADOS", String.valueOf(novaLista.size()));
                    List<Conta> novaListaConta = new ArrayList<>(novaLista);
                    adapter.submitList(novaListaConta);
                }
        );

        adicionarConta.setOnClickListener(
                v -> {
                    startActivity(new Intent(this, AdicionarContaActivity.class));
                }
        );
    }



    @Override
    protected void onStart() {
        super.onStart();
    }
}