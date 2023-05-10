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
        this.infoConta.setText(c.numero + " | " + "Saldo atual: " + NumberFormat.getCurrencyInstance().format(c.saldo));
        if (c.saldo < 0){ //mudar as imagens do código
            icone.setImageResource(R.drawable.delete);
        } else {
            icone.setImageResource(R.drawable.ok);
        }
        this.addListener(c.numero, c.cpfCliente, c.nomeCliente, c.saldo);
    }

    public void addListener(String numeroConta, String cpfConta, String nomeConta, Double saldoConta) {
        this.itemView.setOnClickListener(
                v -> {
                    Context c = this.itemView.getContext();
                    Intent i = new Intent(c, EditarContaActivity.class);
                    i.putExtra("numeroDaConta", numeroConta); // colocar o dados para enviar para a intent
                    i.putExtra("CPFDaConta", cpfConta);
                    i.putExtra("NomeDaConta", nomeConta);
                    i.putExtra("SaldoDaConta", saldoConta.toString());
                    c.startActivity(i);
                }
        );
    }
}
