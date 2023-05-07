package br.ufpe.cin.residencia.banco.conta;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.ufpe.cin.residencia.banco.BancoDB;

//Ver métodos anotados com
public class ContaViewModel extends AndroidViewModel {

    private ContaRepository repository;
    public LiveData<List<Conta>> contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    public LiveData<Conta> contaAtual = _contaAtual;

    public ContaViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.contas = repository.getContas();
    }

    void inserir(Conta c) {
        new Thread(() -> repository.inserir(c)).start();
    }
    void atualizar(Conta c) {

        rodarEmBackground(
                () -> this.repository.atualizar(c)
        );
    }  //implementado
    void remover(Conta c) {
        rodarEmBackground(
                () -> this.repository.remover(c)
        );

    }  //implementado
    void buscarPeloNumero(String numeroConta) {

        rodarEmBackground(
                () -> {
                    Conta conta = this.repository.buscarPeloNumero(numeroConta);
                    _contaAtual.postValue(conta);
                }
        );
    }  //implementado
    private void rodarEmBackground(Runnable r) {
        new Thread(r).start();
    }
}
