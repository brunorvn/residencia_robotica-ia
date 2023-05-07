package br.ufpe.cin.residencia.banco;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.ufpe.cin.residencia.banco.conta.Conta;
import br.ufpe.cin.residencia.banco.conta.ContaRepository;

//Ver anotações TODO no código
public class BancoViewModel extends AndroidViewModel {
    private ContaRepository repository;
    Double saldoTotal;

    public LiveData<List<Conta>> contas;

    public BancoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
    }

    void transferir(String numeroContaOrigem, String numeroContaDestino, double valor) {
        rodarEmBackground(
                () -> {
                    Conta contaOrigem = BancoDB.getDB(getApplication()).contaDAO().findByNumber(numeroContaOrigem);
                    Conta contaDestino = BancoDB.getDB(getApplication()).contaDAO().findByNumber(numeroContaDestino);
                    if (contaOrigem.saldo < valor) {
                        throw new IllegalArgumentException("Saldo insuficiente para realizar essa transação.");
                    }
                    contaOrigem.transferir(contaDestino,valor);

                    rodarEmBackground(()-> repository.atualizar(contaOrigem)); // atualizar as duas contas.
                    rodarEmBackground(()-> repository.atualizar(contaDestino));
                }
        );
        //TODO implementar transferência entre contas (lembrar de salvar no BD os objetos Conta modificados)
    }

    void creditar(String numeroConta, double valor) {
        //TODO implementar creditar em conta (lembrar de salvar no BD o objeto Conta modificado)
    }

    void debitar(String numeroConta, double valor) {
        //TODO implementar debitar em conta (lembrar de salvar no BD o objeto Conta modificado)
    }

    void buscarPeloNome(String nomeCliente) {
        rodarEmBackground(
                () -> this.repository.buscarPeloNome(nomeCliente));
    }

    void buscarPeloCPF(String cpfCliente) {
        //TODO implementar busca pelo CPF do Cliente
    }

    void buscarPeloNumero(String numeroConta) {
        //TODO implementar busca pelo número da Conta
    }

    void bancoSaldoTotal(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                saldoTotal = BancoDB.getDB(getApplication()).contaDAO().saldoTotal();
            }
        });
        thread.start();
    }

    public double getSaldoTotal() {
        bancoSaldoTotal();
        return saldoTotal;
    }

    private void rodarEmBackground(Runnable r) {
        new Thread(r).start();
    }
}

