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
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;

public class ListaLeiraActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private PMMContext pmmContext;
    private AdapterListChoice adapterListChoice;
    private ListView leiraListView;
    private List<LeiraBean> leiraList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leira);

        pmmContext = (PMMContext) getApplication();
        itens = new ArrayList<>();

        Button buttonRetListaLeira = findViewById(R.id.buttonRetListaLeira);
        Button buttonSalvarListaLeira = findViewById(R.id.buttonSalvarListaLeira);

        LogProcessoDAO.getInstance().insertLogProcesso("leiraList = pmmContext.getCompostoCTR().leiraStatusList(" + pmmContext.getCompostoCTR().getTipoMovLeira() + ");", getLocalClassName());
        leiraList = pmmContext.getCompostoCTR().leiraStatusList(pmmContext.getCompostoCTR().getTipoMovLeira());

        LogProcessoDAO.getInstance().insertLogProcesso("for (LeiraBean leiraBean : leiraList) {\n" +
                "            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                "            viewHolderChoice.setSelected(false);\n" +
                "            viewHolderChoice.setDescrCheckBox(String.valueOf(leiraBean.getCodLeira()));\n" +
                "            itens.add(viewHolderChoice);\n" +
                "        }", getLocalClassName());
        for (LeiraBean leiraBean : leiraList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(String.valueOf(leiraBean.getCodLeira()));
            itens.add(viewHolderChoice);
        }

        LogProcessoDAO.getInstance().insertLogProcesso("adapterListChoice = new AdapterListChoice(this, itens);\n" +
                "        leiraListView = (ListView) findViewById(R.id.listLeira);\n" +
                "        leiraListView.setAdapter(adapterListChoice);", getLocalClassName());
        adapterListChoice = new AdapterListChoice(this, itens);
        leiraListView = findViewById(R.id.listLeira);
        leiraListView.setAdapter(adapterListChoice);

        buttonRetListaLeira.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonRetListaLeira.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaLeiraActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaLeiraActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();

        });

        buttonSalvarListaLeira.setOnClickListener(v -> {

            ArrayList<Long> leiraSelectedList = new ArrayList<>();

            for (int i = 0; i < itens.size(); i++) {

                LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<Long> leiraSelectedList = new ArrayList<Long>();\n" +
                        "                for (int i = 0; i < itens.size(); i++) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = itens.get(i);", getLocalClassName());

                ViewHolderChoice viewHolderChoice = itens.get(i);
                if(viewHolderChoice.isSelected()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(viewHolderChoice.isSelected()){\n" +
                            "                        pmmContext.getCompostoCTR().updateLeira(leiraList.get(i), pmmContext.getCompostoCTR().getTipoMovLeira());\n" +
                            "                        leiraSelectedList.add(leiraList.get(i).getIdLeira());", getLocalClassName());
                    pmmContext.getCompostoCTR().updateLeira(leiraList.get(i), pmmContext.getCompostoCTR().getTipoMovLeira());
                    leiraSelectedList.add(leiraList.get(i).getIdLeira());
                }

            }

            if(leiraSelectedList.size() > 0){

                LogProcessoDAO.getInstance().insertLogProcesso("if(leiraSelectedList.size() > 0){", getLocalClassName());
                for (int i = 0; i < leiraSelectedList.size(); i++) {
                    LogProcessoDAO.getInstance().insertLogProcesso("for (int i = 0; i < leiraSelectedList.size(); i++) {\n" +
                            "                        pmmContext.getMotoMecFertCTR().inserirMovLeira(leiraSelectedList.get(i), pmmContext.getCompostoCTR().getTipoMovLeira());", getLocalClassName());
                    pmmContext.getMotoMecFertCTR().inserirMovLeira(leiraSelectedList.get(i), pmmContext.getCompostoCTR().getTipoMovLeira());
                }

                LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(null);\n" +
                        "                    Intent it = new Intent(ListaLeiraActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                EnvioDadosServ.getInstance().envioDados(getLocalClassName());
                Intent it = new Intent(ListaLeiraActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();

            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLeiraActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"POR FAVOR! SELECIONE A(S) LEIRA(S) QUE SERA(M) ABERTA(S).\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLeiraActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR! SELECIONE A(S) LEIRA(S) QUE SERA(M) ABERTA(S).");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();
            }

            leiraSelectedList.clear();

        });

    }

    public void onBackPressed()  {
    }

}
