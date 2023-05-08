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
        //TODO implementar transferência entre contas (lembrar de salvar no BD os objetos Conta modificados)
        rodarEmBackground(
                () -> {
                    Conta contaOrigem = BancoDB.getDB(getApplication()).contaDAO().findByNumber(numeroContaOrigem);
                    Conta contaDestino = BancoDB.getDB(getApplication()).contaDAO().findByNumber(numeroContaDestino);
//                    if (contaOrigem.saldo < valor) {
//                        throw new IllegalArgumentException("Saldo insuficiente para realizar essa transação.");
//                    }
                    contaOrigem.transferir(contaDestino,valor);

                    rodarEmBackground(()-> repository.atualizar(contaOrigem)); // atualizar as duas contas no BD
                    rodarEmBackground(()-> repository.atualizar(contaDestino));
                }
        );

    }
    void creditar(String numeroConta, double valor) {
        rodarEmBackground(
                () -> {
                    Conta conta = BancoDB.getDB(getApplication()).contaDAO().findByNumber(numeroConta);
                    conta.creditar(valor);
                    // executa a operação e depois atualiza o objeto Conta modificado
                    rodarEmBackground(() -> repository.atualizar(conta));
                });
    }

    void debitar(String numeroConta, double valor) {
        //TODO implementar debitar em conta (lembrar de salvar no BD o objeto Conta modificado)
        rodarEmBackground(
                () -> {
                    Conta conta = BancoDB.getDB(getApplication()).contaDAO().findByNumber(numeroConta);
                    conta.debitar(valor);
                    // executa a operação e depois atualiza o objeto Conta modificado
                    rodarEmBackground(() -> repository.atualizar(conta));

                });
    }
    void buscarPeloNome(String nomeCliente) {
        rodarEmBackground(
                () -> this.repository.buscarPeloNome(nomeCliente));
    }
    void buscarPeloCPF(String cpfCliente) {
        //TODO implementar busca pelo CPF do Cliente
        rodarEmBackground(
                () -> this.repository.buscarPeloCPF(cpfCliente));
    }
    void buscarPeloNumero(String numeroConta) {
        //TODO implementar busca pelo número da Conta
        rodarEmBackground(
                () -> this.repository.buscarPeloNumero(numeroConta));
    }
    void bancoSaldoTotal(){
        rodarEmBackground(
                ()-> saldoTotal = BancoDB.getDB(getApplication()).contaDAO().saldoTotal());
    }

    public double getSaldoTotal() {
        bancoSaldoTotal();
        return saldoTotal;
    }
    //código da aula - chamando o metodo rodarEmBackgroud para não precisar ficar repetindo códigos
    private void rodarEmBackground(Runnable r) {
        new Thread(r).start();
    }
}

