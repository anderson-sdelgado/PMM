package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemOSMecanBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ListaItemOSMecanActivity extends ActivityGeneric {

    private ListView itemOSListView;
    private List<ItemOSMecanBean> itemOSList;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item_os_mecan);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetItemOS = findViewById(R.id.buttonRetItemOS);
        Button buttonAtualItemOS = findViewById(R.id.buttonAtualItemOS);

        LogProcessoDAO.getInstance().insertLogProcesso("itemOSList = pmmContext.getMecanicoCTR().itemOSMecanList();\n" +
                "        ArrayList<String> itens = new ArrayList<String>();", getLocalClassName());
        itemOSList = pmmContext.getMecanicoCTR().itemOSMecanList();
        ArrayList<String> itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("for(ItemOSMecanBean itemOSBean : itemOSList){\n" +
                "            itens.add(itemOSBean.getSeqItemOS() + \" - \"\n" +
                "                    + pmmContext.getMecanicoCTR().getServico(itemOSBean.getIdServItemOS()).getDescrServico() + \" - \"\n" +
                "                    + pmmContext.getMecanicoCTR().getComponente(itemOSBean.getIdCompItemOS()).getCodComponente() + \" - \"\n" +
                "                    + pmmContext.getMecanicoCTR().getComponente(itemOSBean.getIdCompItemOS()).getDescrComponente());\n" +
                "        }", getLocalClassName());
        for(ItemOSMecanBean itemOSBean : itemOSList){
            itens.add(itemOSBean.getSeqItemOS() + " - "
                    + pmmContext.getMecanicoCTR().getServico(itemOSBean.getIdServItemOS()).getDescrServico() + " - "
                    + pmmContext.getMecanicoCTR().getComponente(itemOSBean.getIdCompItemOS()).getCodComponente() + " - "
                    + pmmContext.getMecanicoCTR().getComponente(itemOSBean.getIdCompItemOS()).getDescrComponente());
        }

        buttonAtualItemOS.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualItemOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (connectNetwork) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                        "                    progressBar = new ProgressDialog(ListaItemOSMecanActivity.this);\n" +
                        "                    progressBar.setCancelable(true);\n" +
                        "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                        "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                        "                    progressBar.setProgress(0);\n" +
                        "                    progressBar.setMax(100);\n" +
                        "                    progressBar.show();", getLocalClassName());

                progressBar = new ProgressDialog(ListaItemOSMecanActivity.this);
                progressBar.setCancelable(true);
                progressBar.setMessage("ATUALIZANDO ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();

                LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread)", getLocalClassName());
                pmmContext.getMecanicoCTR().atualDados(ListaItemOSMecanActivity.this, ListaItemOSMecanActivity.class, progressBar, "ItemOSMecan", 2, getLocalClassName());

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaItemOSMecanActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaItemOSMecanActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                alerta.show();
            }
        });

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        itemOSListView = findViewById(R.id.listItemOS);\n" +
                "        itemOSListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        itemOSListView = findViewById(R.id.listItemOS);
        itemOSListView.setAdapter(adapterList);

        itemOSListView.setOnItemClickListener((l, v, position, id) -> {

            ItemOSMecanBean itemOSBean = itemOSList.get(position);
            pmmContext.getMecanicoCTR().salvarApontMecan(itemOSBean.getSeqItemOS(), getLocalClassName());

            LogProcessoDAO.getInstance().insertLogProcesso("itemOSListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                ItemOSMecanBean itemOSBean = itemOSList.get(position);\n" +
                    "                pmmContext.getMecanicoCTR().salvarApont(itemOSBean.getSeqItemOS(), getLocalClassName());\n" +
                    "                Intent it = new Intent(ListaItemOSMecanActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaItemOSMecanActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetItemOS.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetItemOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaItemOSMecanActivity.this, OSMecanActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaItemOSMecanActivity.this, OSMecanActivity.class);
            startActivity(it);
            finish();
        });
    }

    public void onBackPressed()  {
    }

}