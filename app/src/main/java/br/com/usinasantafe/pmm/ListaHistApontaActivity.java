package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;

public class ListaHistApontaActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hist_aponta);

        Button buttonRetHistorico = (Button) findViewById(R.id.buttonRetHistorico);

        BackupApontaTO backupApontaTO = new BackupApontaTO();

        ListView listaHistorico = (ListView) findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, backupApontaTO.all());
        listaHistorico.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaHistApontaActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
