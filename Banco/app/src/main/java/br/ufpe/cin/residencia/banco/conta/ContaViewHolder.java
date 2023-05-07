package br.ufpe.cin.residencia.banco.conta;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class ContaViewHolder  extends RecyclerView.ViewHolder {
    TextView nomeCliente = null;
    TextView infoConta = null;
    ImageView icone = null;
    ImageView btnEdit;
    ImageView btnDelete;
    Context context;

    public ContaViewHolder(@NonNull View linha) {
        super(linha);
        this.nomeCliente = linha.findViewById(R.id.nomeCliente);
        this.infoConta = linha.findViewById(R.id.infoConta);
        this.icone = linha.findViewById(R.id.icone);
        this.btnEdit = linha.findViewById(R.id.btnAtualizar);
        this.btnDelete = linha.findViewById(R.id.btnRemover);
        this.context = linha.getContext();

    }

    void bindTo(Conta c) {
        this.nomeCliente.setText(c.nomeCliente);
        this.infoConta.setText(c.numero + " | " + "Saldo atual: R$" + NumberFormat.getCurrencyInstance().format(c.saldo));
        //TODO Falta atualizar a imagem de acordo com o valor do saldo atual
        this.addListener(c.numero, c.cpfCliente, c.nomeCliente, c.saldo);
    }

    public void addListener(String numeroConta, String cpfConta, String nomeConta, Double saldoConta) {
        this.itemView.setOnClickListener(
                v -> {
                    Context c = this.itemView.getContext();
                    Intent i = new Intent(c, EditarContaActivity.class);
                    //TODO Está especificando a Activity mas não está passando o número da conta pelo Intent
                    i.putExtra("numeroDaConta", numeroConta);
                    i.putExtra("CPFDaConta", cpfConta);
                    i.putExtra("NomeDaConta", nomeConta);
                    i.putExtra("SaldoDaConta", saldoConta.toString());
                    c.startActivity(i);
                }
        );
    }
}
