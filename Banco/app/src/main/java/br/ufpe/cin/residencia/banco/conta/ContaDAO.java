package br.ufpe.cin.residencia.banco.conta;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;



@Dao
public interface ContaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertConta(Conta c);

    //incluir métodos para atualizar conta e remover conta
    @Update
    void updateConta(Conta c);
    @Delete
    void deleteConta(Conta c);
    @Query("SELECT * FROM contas ORDER BY numero ASC")
    LiveData<List<Conta>> contas();
    // 05. incluir métodos para buscar pelo
    @Query("select * from contas where numero like :numero limit 1")
    Conta findByNumber(String numero); // buscar (1) pelo número da conta
    @Query("select * from contas where nomeCliente like :nomeCliente limit 1")
    Conta findByName(String nomeCliente);    // (2) pelo nome do cliente
    @Query("select * from contas where cpfCliente like :cpfCliente limit 1")
    Conta findByCPFCliente(String cpfCliente);    // (3) pelo CPF do cliente
    @Query("select * from contas where numero like :numero")
    List<Conta> findListByNumber(String numero); // buscar (1) pelo número da conta em lista
    @Query("select * from contas where nomeCliente like :nomeCliente")
    List<Conta> findListByName(String nomeCliente);    // (2) pelo nome do cliente em lista
    @Query("select * from contas where cpfCliente like :cpfCliente")
    List<Conta> findListByCPFCliente(String cpfCliente);    // (3) pelo CPF do cliente em lista

    // 15. Saldo Total
    @Query("SELECT SUM(saldo) FROM contas")
    Double saldoTotal();


}
