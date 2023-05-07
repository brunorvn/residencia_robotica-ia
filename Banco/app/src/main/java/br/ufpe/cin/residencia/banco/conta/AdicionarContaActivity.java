package br.ufpe.cin.residencia.banco.conta;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class AdicionarContaActivity extends AppCompatActivity {

    ContaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_conta);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);

        Button btnAtualizar = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        EditText campoNome = findViewById(R.id.nome);
        EditText campoNumero = findViewById(R.id.numero);
        EditText campoCPF = findViewById(R.id.cpf);
        EditText campoSaldo = findViewById(R.id.saldo);

        btnAtualizar.setText("Inserir");
        btnRemover.setVisibility(View.GONE);

        btnAtualizar.setOnClickListener(
                v -> {
                    String nomeCliente = campoNome.getText().toString();
                    String cpfCliente = campoCPF.getText().toString();
                    String numeroConta = campoNumero.getText().toString();
                    String saldoConta = campoSaldo.getText().toString();

                    if (nomeCliente.trim().isEmpty()) {
                        campoNome.setError("Nome não pode estar em branco");
                        campoNome.requestFocus();
                    } else if (cpfCliente.trim().isEmpty()) {
                        campoCPF.setError("CPF não pode estar em branco");
                        campoCPF.requestFocus();
                    } else if (numeroConta.trim().isEmpty()) {
                        campoNumero.setError("Número da conta não pode estar em branco");
                        campoNumero.requestFocus();
                    } else if (saldoConta.trim().isEmpty()) {
                        campoSaldo.setError("Saldo não pode estar em branco");
                        campoSaldo.requestFocus();
                    } else if (nomeCliente.length() < 5) {
                        campoNome.setError("Nome deve ter 5 caracteres ou mais");
                        campoNome.requestFocus();
                    } else if (cpfCliente.length() != 11) {
                        campoCPF.setError("CPF deve ter 11 dígitos");
                        campoCPF.requestFocus();
                    } else if (!saldoConta.matches("-?\\d+(\\.\\d+)?")) {
                        campoSaldo.setError("Saldo deve ser um número válido");
                        campoSaldo.requestFocus();
                    }  else {
                        try {
                            Conta conta = new Conta(numeroConta, Double.parseDouble(saldoConta), nomeCliente, cpfCliente);
                            viewModel.inserir(conta);

                        } catch(Exception exception) {
                            String errorMessage = "Erro ao criar conta: " + exception.getMessage();
                            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                            Log.e(TAG, errorMessage, exception);
                        }
                        finish();
                    }

                }
        );

    }
    }