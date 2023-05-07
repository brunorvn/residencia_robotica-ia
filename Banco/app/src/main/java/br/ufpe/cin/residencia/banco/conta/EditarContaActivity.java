package br.ufpe.cin.residencia.banco.conta;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class EditarContaActivity extends AppCompatActivity {

    public static final String KEY_NUMERO_CONTA = "numeroDaConta";
    public static final String KEY_CPF_CONTA = "CPFDaConta";
    public static final String KEY_NOME_CONTA = "NomeDaConta";
    public static final String KEY_SALDO_CONTA = "SaldoDaConta";
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
        campoNumero.setEnabled(false);

        Intent i = getIntent();
        String numeroConta = i.getStringExtra(KEY_NUMERO_CONTA);
        String cpfConta = i.getStringExtra(KEY_CPF_CONTA);
        String nomeConta = i.getStringExtra(KEY_NOME_CONTA);
        String saldosConta = i.getStringExtra(KEY_SALDO_CONTA);

        //10
        campoNumero.setText(numeroConta);
        campoNome.setText(nomeConta);
        campoSaldo.setText(saldosConta);
        campoCPF.setText(cpfConta);

        btnAtualizar.setText("Editar");
        btnAtualizar.setOnClickListener(
                v -> {
                    String nomeCliente = campoNome.getText().toString();
                    String cpfCliente = campoCPF.getText().toString();
                    String saldoConta = campoSaldo.getText().toString();
                    //TODO: Incluir validações aqui, antes de criar um objeto Conta. Se todas as validações passarem, aí sim monta um objeto Conta.
                    //TODO: chamar o método que vai atualizar a conta no Banco de Dados
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
                            Conta c = new Conta(numeroConta, Double.valueOf(saldoConta), nomeCliente, cpfCliente);
                            viewModel.atualizar(c);
                        } catch (Exception exception) {
                            String errorMessage = "Erro ao criar conta: " + exception.getMessage();
                            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                            Log.e(TAG, errorMessage, exception);

                        }
                        finish();

                    }
                }
        );

        btnRemover.setOnClickListener(v -> {
            //TODO implementar remoção da conta

            String nomeCliente = campoNome.getText().toString();
            String cpfCliente = campoCPF.getText().toString();
            String saldoConta = campoSaldo.getText().toString();

            try {
                Conta conta = new Conta(numeroConta, Double.valueOf(saldoConta), nomeCliente, cpfCliente);
                viewModel.remover(conta);
            } catch (Exception exception){
                campoCPF.setError("Campo invalido");
                campoCPF.requestFocus();

                campoNome.setError("Campo invalido");
                campoNome.requestFocus();

                campoSaldo.setError("Campo invalido");
                campoSaldo.requestFocus();
            }
            finish();
        }
    );
}
}