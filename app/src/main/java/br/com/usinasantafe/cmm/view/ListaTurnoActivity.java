package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.Tempo;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private List<TurnoBean> turnoList;
    private CMMContext cmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        cmmContext = (CMMContext) getApplication();

        Button buttonRetTurno = findViewById(R.id.buttonRetTurno);
        Button buttonAtualTurno = findViewById(R.id.buttonAtualTurno);

        buttonAtualTurno.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualTurno.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaTurnoActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

            AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaTurnoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "progressBar = new ProgressDialog(ListaTurnoActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                            progressBar.setProgress(0);\n" +
                            "                            progressBar.setMax(100);\n" +
                            "                            progressBar.show();", getLocalClassName());
                    progressBar = new ProgressDialog(ListaTurnoActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar, \"Turno\", 1);", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().atualDados(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar, "Turno", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder( ListaTurnoActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ListaTurnoActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {
                    });
                    alerta1.show();

                }


            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

        LogProcessoDAO.getInstance().insertLogProcesso("turnoList = pmmContext.getMotoMecFertCTR().getTurnoCodList();", getLocalClassName());
        turnoList = cmmContext.getMotoMecFertCTR().getTurnoCodList(getLocalClassName());

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for(TurnoBean turnoBean : turnoList){\n" +
                "            itens.add(turnoBean.getDescTurno());\n" +
                "        }", getLocalClassName());
        ArrayList<String> itens = new ArrayList<>();
        for(TurnoBean turnoBean : turnoList){
            itens.add(turnoBean.getDescTurno());
        }

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        turnoListView = findViewById(R.id.listaTurno);\n" +
                "        turnoListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = findViewById(R.id.listaTurno);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {" +
                    "TurnoBean turnoBean = turnoList.get(position);\n" +
                    "                turnoList.clear();", getLocalClassName());
            TurnoBean turnoBean = turnoList.get(position);
            turnoList.clear();

            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().setIdTurnoConfig(" + turnoBean.getIdTurno() + ");", getLocalClassName());
            cmmContext.getConfigCTR().setIdTurnoConfig(turnoBean.getIdTurno());

            if(Tempo.getInstance().verDthrServ(cmmContext.getConfigCTR().getConfig().getDtServConfig())){
                LogProcessoDAO.getInstance().insertLogProcesso("if(Tempo.getInstance().verDthrServ(pmmContext.getConfigCTR().getConfig().getDtServConfig())){\n" +
                        "pmmContext.getConfigCTR().setDifDthrConfig(0L);", getLocalClassName());
                cmmContext.getConfigCTR().setDifDthrConfig(0L);
                if(BuildConfig.FLAVOR.equals("ecm")){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 2){", getLocalClassName());
                    if (connectNetwork) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                "pmmContext.getConfigCTR().setStatusConConfig(1L);", getLocalClassName());
                        cmmContext.getConfigCTR().setStatusConConfig(1L);
                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "pmmContext.getConfigCTR().setStatusConConfig(0L);", getLocalClassName());
                        cmmContext.getConfigCTR().setStatusConConfig(0L);
                    }
                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ListaTurnoActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaTurnoActivity.this, ListaAtividadeActivity.class);
                    startActivity(it);
                    finish();

                } else if(BuildConfig.FLAVOR.equals("pcomp")){

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaTurnoActivity.this, ListaFuncaoCompActivity.class);
                    startActivity(it);
                    finish();

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "pmmContext.getConfigCTR().setContDataHora(1);", getLocalClassName());
                cmmContext.getConfigCTR().setContDataHora(1);
                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ListaTurnoActivity.this, MsgDataHoraActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaTurnoActivity.this, MsgDataHoraActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonRetTurno.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTurno.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
