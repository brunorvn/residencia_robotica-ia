package br.ufpe.cin.residencia.banco;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

//Ver anotações TODO no código
public class TransferirActivity extends AppCompatActivity {

    BancoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacoes);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);

        TextView tipoOperacao = findViewById(R.id.tipoOperacao);
        EditText numeroContaOrigem = findViewById(R.id.numeroContaOrigem);
        TextView labelContaDestino = findViewById(R.id.labelContaDestino);
        EditText numeroContaDestino = findViewById(R.id.numeroContaDestino);
        EditText valorOperacao = findViewById(R.id.valor);
        Button btnOperacao = findViewById(R.id.btnOperacao);

        valorOperacao.setHint(valorOperacao.getHint() + " transferido");
        tipoOperacao.setText("TRANSFERIR");
        btnOperacao.setText("Transferir");

        btnOperacao.setOnClickListener(
                v -> {

                    try {
                        String numOrigem = numeroContaOrigem.getText().toString();
                        String numDestino = numeroContaDestino.getText().toString();
                        double valor = Double.valueOf(valorOperacao.getText().toString());

                        if (numOrigem.isEmpty()) {
                            numeroContaOrigem.setError("Campo não pode estar vazio");
                            numeroContaOrigem.requestFocus();
                        }else if (numDestino.isEmpty()) {
                            numeroContaDestino.setError("Campo não pode estar vazio");
                            numeroContaDestino.requestFocus();
                        }else if (numOrigem.equals(numDestino)) {
                            numeroContaDestino.setError("Campos não podem ser iguais");
                            numeroContaDestino.requestFocus();
                        }else if (valor <= 0) {
                            valorOperacao.setError("O valor da operação deve ser maior que zero");
                            valorOperacao.requestFocus();
                        }
                            viewModel.transferir(numOrigem, numDestino, valor);
                            finish();
                    } catch (Exception exception) {
                            numeroContaOrigem.setError(exception.getMessage());
                            numeroContaOrigem.requestFocus();

                            numeroContaDestino.setError(exception.getMessage());
                            numeroContaDestino.requestFocus();

                            valorOperacao.setError(exception.getMessage());
                            valorOperacao.requestFocus();

                        }
                    }
        );

    }
}

