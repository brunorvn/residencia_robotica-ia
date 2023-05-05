package br.ufpe.cin.residencia.banco.conta;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

//Ver anotações TODO no código


@Dao
public interface ContaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void adicionar(Conta c);

    //feito - incluir métodos para atualizar conta e remover conta
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void atualizar(Conta c);

    @Delete
    void remover(Conta c);

    @Query("SELECT * FROM contas ORDER BY numero ASC")
    LiveData<List<Conta>> contas();

    //TODO  incluir métodos para buscar pelo (1) número da conta, (2) pelo nome e (3) pelo CPF do Cliente
    @Query("SELECT * FROM contas ORDER BY nomeCliente ASC")
    LiveData<List<Conta>> nomecliente();

    @Query("SELECT * FROM contas ORDER BY cpfCliente ASC")
    LiveData<List<Conta>> cpfcliente();


}
