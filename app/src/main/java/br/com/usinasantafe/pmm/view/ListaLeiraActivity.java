package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;

public class ListaLeiraActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private PMMContext pmmContext;
    private AdapterListChoice adapterListChoice;
    private ListView leiraListView;
    private List leiraList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leira);

        pmmContext = (PMMContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        Button buttonRetListaLeira = (Button) findViewById(R.id.buttonRetListaLeira);
        Button buttonSalvarListaLeira = (Button) findViewById(R.id.buttonSalvarListaLeira);

        Long status;

        if(pmmContext.getTipoMovComp() == 2){
            status = 1L;
        }
        else if(pmmContext.getTipoMovComp() == 4){
            status = 2L;
        }
        else{
            status = 0L;
        }

        LeiraBean leiraBean = new LeiraBean();
        leiraList = leiraBean.getAndOrderBy("statusLeira", status, "codLeira", true);

        for (int i = 0; i < leiraList.size(); i++) {
            leiraBean = (LeiraBean) leiraList.get(i);
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(String.valueOf(leiraBean.getCodLeira()));
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        leiraListView = (ListView) findViewById(R.id.listLeira);
        leiraListView.setAdapter(adapterListChoice);

        buttonRetListaLeira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaLeiraActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonSalvarListaLeira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ArrayList<Long> leiraSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {

                    ViewHolderChoice viewHolderChoice = itens.get(i);

                    if(viewHolderChoice.isSelected()){
                        LeiraBean leiraBean = (LeiraBean) leiraList.get(i);
                        leiraSelectedList.add(leiraBean.getIdLeira());
                        if(pmmContext.getTipoMovComp() == 1){
                            leiraBean.setStatusLeira(1L);
                        }
                        else if(pmmContext.getTipoMovComp() == 3){
                            leiraBean.setStatusLeira(2L);
                        }
                        else{
                            leiraBean.setStatusLeira(0L);
                        }
                        leiraBean.update();
                    }

                }

                if(leiraSelectedList.size() > 0){

                    for (int i = 0; i < leiraSelectedList.size(); i++) {
                        pmmContext.getMotoMecFertCTR().inserirMovLeira(leiraSelectedList.get(i), pmmContext.getTipoMovComp());
                    }

                    Intent it = new Intent(ListaLeiraActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLeiraActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE A(S) LEIRA(S) QUE SERA(M) ABERTA(S).");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

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
