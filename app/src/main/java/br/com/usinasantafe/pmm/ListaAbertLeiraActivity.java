package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bean.estaticas.LeiraTO;

public class ListaAbertLeiraActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private PMMContext pmmContext;
    private AdapterListChoice adapterListChoice;
    private ListView leiraListView;
    private List leiraList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_abert_leira);

        pmmContext = (PMMContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        Button buttonRetListaLeira = (Button) findViewById(R.id.buttonRetListaLeira);
        Button buttonSalvarListaLeira = (Button) findViewById(R.id.buttonSalvarListaLeira);

        LeiraTO leiraTO = new LeiraTO();
        leiraList = leiraTO.orderBy("codLeira", true);

        for (int i = 0; i < leiraList.size(); i++) {
            leiraTO = (LeiraTO) leiraList.get(i);
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(String.valueOf(leiraTO.getCodLeira()));
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        leiraListView = (ListView) findViewById(R.id.listLeira);
        leiraListView.setAdapter(adapterListChoice);

        buttonRetListaLeira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(ListaAbertLeiraActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonSalvarListaLeira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ArrayList<Long> leiraSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {

                    ViewHolderChoice viewHolderChoice = itens.get(i);

                    if(viewHolderChoice.isSelected()){
                        LeiraTO leiraTO = (LeiraTO) leiraList.get(i);
                        leiraSelectedList.add(leiraTO.getIdLeira());
                    }

                }

                if(leiraSelectedList.size() > 0){

                    for (int i = 0; i < leiraSelectedList.size(); i++) {
                        pmmContext.getBoletimCTR().insMovLeiraAberta(leiraSelectedList.get(i));
                    }

                    Intent it = new Intent(ListaAbertLeiraActivity.this, MenuPrincNormalActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAbertLeiraActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE A(S) LEIRA(S) QUE SERA(M) ABERTA(S).");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

                leiraSelectedList.clear();

            }
        });

    }

    public void onBackPressed()  {
    }

}
