package com.utfpr.APPFinanceiro.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import com.utfpr.APPFinanceiro.R;
import com.utfpr.APPFinanceiro.adapter.AdapterBalance;
import com.utfpr.APPFinanceiro.listener.RecyclerViewClickListener;
import com.utfpr.APPFinanceiro.listener.RecyclerViewTouchListener;
import com.utfpr.APPFinanceiro.model.dao.BalanceDAO;
import com.utfpr.APPFinanceiro.model.entity.Balance;

public class BalanceActivity extends AppCompatActivity {

    private RecyclerView listBalance;
    private List<Balance> lista = new ArrayList<>();
    private FloatingActionButton fab;
    AdapterBalance adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        listBalance = findViewById(R.id.recyclerBalance);
        fab = findViewById(R.id.floatingActionButton);

        geraLista();

        //adicionando listenes para on click
        listBalance.addOnItemTouchListener(new RecyclerViewTouchListener(
                getApplicationContext(), listBalance, new RecyclerViewClickListener() {

            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), BalanceEditActivity.class);
                intent.putExtra("balance", lista.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                BalanceDAO dao = new BalanceDAO(getApplicationContext());
                if(dao.remover(lista.get(position))) {
                    Toast.makeText(getApplicationContext(), lista.get(position).getDesc() + " removido com sucesso", Toast.LENGTH_SHORT).show();
                    geraLista();
                }else{
                    Toast.makeText(getApplicationContext(), "Erro ao remover registro", Toast.LENGTH_SHORT).show();
                }
            }
        }

        ));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BalanceEditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        geraLista();
        super.onStart();
    }

    public void geraLista(){

        BalanceDAO dao = new BalanceDAO(getApplicationContext());
        lista = dao.listar();

        //Configuração do adapter
        adapter = new AdapterBalance(lista);

        //Configuração do recycler view
        //layout manager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        listBalance.setLayoutManager(manager);

        //adapter
        listBalance.setAdapter(adapter);
    }
}