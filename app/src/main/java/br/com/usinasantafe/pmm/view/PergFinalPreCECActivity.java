package br.com.usinasantafe.pmm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class PergFinalPreCECActivity extends ActivityGeneric {

    private ListView finalizarListView;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg_final_pre_cec);

        pmmContext = (PMMContext) getApplication();

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FINALIZAR CERTIFICADO");
        itens.add("DESFAZER CERTIFICADO");

        AdapterList adapterList = new AdapterList(this, itens);
        finalizarListView = findViewById(R.id.listViewFinalizaApont);
        finalizarListView.setAdapter(adapterList);

        finalizarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("FINALIZAR CERTIFICADO")) {
                    Intent it = new Intent(PergFinalPreCECActivity.this, MsgSaidaCampoActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (text.equals("DESFAZER CERTIFICADO")) {
                    pmmContext.getCecCTR().clearPreCECAberto();
                    Intent it = new Intent(PergFinalPreCECActivity.this, MenuCertifActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void onBackPressed()  {
    }

}