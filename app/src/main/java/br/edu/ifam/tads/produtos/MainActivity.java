package br.edu.ifam.tads.produtos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifam.tads.produtos.dto.FornecedorDTO;
import br.edu.ifam.tads.produtos.entity.Fornecedor;
import br.edu.ifam.tads.produtos.interfaces.FornecedorAPI;
import br.edu.ifam.tads.produtos.recycler.FornecedorAdapter;
import br.edu.ifam.tads.produtos.repository.FornecedorDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FornecedorAdapter adapter;
    private List<Fornecedor> fornecedores;
    private ProgressBar progressBarMain;
    private FornecedorAPI fornecedorAPI;
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
        progressBarMain = findViewById(R.id.progressBarMain);
        progressBarMain.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        acessarApi();

    }

    @Override
    protected void onStart() {
        super.onStart();
        getForncedores();

    }
    public void addFornecedorOnClick(View view){
        Intent intent = new Intent(this, FornecedorDetalhesActivity.class);
        startActivity(intent);
    }

    private void acessarApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.50.166:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fornecedorAPI = retrofit.create(FornecedorAPI.class);

    }

    private void getForncedores(){
        Call<List<FornecedorDTO>> call = fornecedorAPI.getFornecedor();

        progressBarMain.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<FornecedorDTO>>() {
            @Override
            public void onResponse(Call<List<FornecedorDTO>> call, Response<List<FornecedorDTO>> response) {
                List<Fornecedor> fornecedores = new ArrayList<>();

                if(response.isSuccessful() && response.body() != null){
                    List<FornecedorDTO> fornecedoresDTO = response.body();
                    for(FornecedorDTO fornecedorDTO : fornecedoresDTO){
                        fornecedores.add(fornecedorDTO.getFornecedor());
                    }

                }else{
                    String codigoErro = "Erro: " + response.code();
                    Toast.makeText(getApplicationContext(), codigoErro , Toast.LENGTH_SHORT).show();
                }

                adapter = new FornecedorAdapter(fornecedores, getApplicationContext());
                recyclerView.setAdapter(adapter);

                progressBarMain.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<FornecedorDTO>> call, Throwable t) {
                String failureMessage = "Falha se acesso: " + t.getMessage();
                Toast.makeText(getApplicationContext(), failureMessage , Toast.LENGTH_LONG).show();
                progressBarMain.setVisibility(View.INVISIBLE);

            }
        });
    }
}