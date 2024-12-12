package br.edu.ifam.tads.produtos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifam.tads.produtos.entity.Fornecedor;
import br.edu.ifam.tads.produtos.recycler.FornecedorAdapter;
import br.edu.ifam.tads.produtos.repository.FornecedorDAO;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FornecedorAdapter adapter;
    private List<Fornecedor> fornecedores;

    private FornecedorDAO fornecedorDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fornecedorDAO = new FornecedorDAO(this);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new FornecedorAdapter(fornecedorDAO.getFornecedor(),
                this);
        recyclerView.setAdapter(adapter);
    }
    public void addFornecedorOnClick(View view){
        Intent intent = new Intent(this, FornecedorDetalhesActivity.class);
        startActivity(intent);
    }
}