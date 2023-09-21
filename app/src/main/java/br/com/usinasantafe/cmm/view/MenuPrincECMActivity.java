package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.Tempo;

public class MenuPrincECMActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private CMMContext cmmContext;
    private TextView textViewMotorista;
    private TextView textViewCarreta;
    private TextView textViewUltimaViagem;
    private TextView textViewPropriedade;
    private TextView textViewStatus;
    private ProgressDialog progressBar;
    private List<MotoMecBean> motoMecList;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_ecm);

        cmmContext = (CMMContext) getApplication();

        Button buttonRetMotoMec = findViewById(R.id.buttonRetMotoMec);
        Button buttonParadaMotoMec = findViewById(R.id.buttonParadaMotoMec);
        Button buttonLogMotoMec = findViewById(R.id.buttonLogMotoMec);
        textViewMotorista = findViewById(R.id.textViewMotorista);
        textViewCarreta = findViewById(R.id.textViewCarreta);
        textViewUltimaViagem = findViewById(R.id.textViewUltimaViagem);
        textViewPropriedade = findViewById(R.id.textViewPropriedade);
        textViewStatus = findViewById(R.id.textViewStatus);

        textViewMotorista.setText(cmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc() + " - " + cmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());
        textViewCarreta.setText(cmmContext.getMotoMecFertCTR().getDescrCarreta());
        textViewUltimaViagem.setText(cmmContext.getCecCTR().getDataSaidaUlt());
        textViewPropriedade.setText(cmmContext.getConfigCTR().getMsgPropriedade());
        textViewStatus.setText(cmmContext.getMotoMecFertCTR().getUltApont());

        if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){\n" +
                    "            pmmContext.getMotoMecFertCTR().inserirApontBolAnterior(getLocalClassName());", getLocalClassName());
            cmmContext.getMotoMecFertCTR().inserirApontBolAnterior(getLocalClassName());
        }

        if (Tempo.getInstance().verDthrServ(cmmContext.getConfigCTR().getConfig().getDtServConfig())) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (Tempo.getInstance().verDthrServ(pmmContext.getConfigCTR().getConfig().getDtServConfig())) {\n" +
                    "            pmmContext.getConfigCTR().setDifDthrConfig(0L);", getLocalClassName());
            cmmContext.getConfigCTR().setDifDthrConfig(0L);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
            if ((cmmContext.getConfigCTR().getConfig().getDifDthrConfig() == 0) && (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 8L)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if ((pmmContext.getConfigCTR().getConfig().getDifDthrConfig() == 0) && (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 8L)) {\n" +
                        "                pmmContext.getConfigCTR().setContDataHora(1);\n" +
                        "                pmmContext.getConfigCTR().setPosicaoTela(5L);\n" +
                        "                Intent it = new Intent(MenuPrincECMActivity.this, MsgDataHoraActivity.class);", getLocalClassName());
                cmmContext.getConfigCTR().setContDataHora(1);
                cmmContext.getConfigCTR().setPosicaoTela(5L);
                Intent it = new Intent(MenuPrincECMActivity.this, MsgDataHoraActivity.class);
                startActivity(it);
                finish();
            }
        }

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> motoMecArrayList = new ArrayList<String>();\n" +
                "        motoMecList = pmmContext.getMotoMecFertCTR().motoMecList();\n" +
                "        for (MotoMecBean motoMecBean : motoMecList) {\n" +
                "            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, motoMecArrayList);\n" +
                "        motoMecListView = findViewById(R.id.motoMecListView);\n" +
                "        motoMecListView.setAdapter(adapterList);", getLocalClassName());
        ArrayList<String> motoMecArrayList = new ArrayList<>();
        motoMecList = cmmContext.getMotoMecFertCTR().motoMecList();
        for (MotoMecBean motoMecBean : motoMecList) {
            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, motoMecArrayList);
        motoMecListView = findViewById(R.id.motoMecListView);
        motoMecListView.setAdapter(adapterList);

        motoMecListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                posicao = position;\n" +
                    "                MotoMecBean motoMecBean = motoMecList.get(position);\n" +
                    "                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);", getLocalClassName());
            posicao = position;
            MotoMecBean motoMecBean = motoMecList.get(position);
            cmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);

            if (cmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                        "                    Toast.makeText(MenuPrincECMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                        "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                Toast.makeText(MenuPrincECMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                        Toast.LENGTH_LONG).show();
            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
                if (cmmContext.getMotoMecFertCTR().verifBackupApont()
                    && (motoMecBean.getCodFuncaoOperMotoMec() != 6)) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 11)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 12)) {  // ATIVIDADES NORMAIS

                        LogProcessoDAO.getInstance().insertLogProcesso("if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)\n" +
                                "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)\n" +
                                "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 12)) {  // ATIVIDADES NORMAIS\n" +
                                "                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(\"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                        alerta.setPositiveButton("OK", (dialog, which) -> {

                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    ConnectNetwork connectNetwork = new ConnectNetwork();\n" +
                                    "                                    if (connectNetwork) {\n" +
                                    "                                        pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                    "                                    } else {\n" +
                                    "                                        pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                    "                                    }", getLocalClassName());
                            if (connectNetwork) {
                                cmmContext.getConfigCTR().setStatusConConfig(1L);
                            } else {
                                cmmContext.getConfigCTR().setStatusConConfig(0L);
                            }

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                    "                                    motoMecListView.setSelection(posicao + 1);\n" +
                                    "textViewStatus.setText(pmmContext.getMotoMecFertCTR().getUltApont());", getLocalClassName());
                            cmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                            motoMecListView.setSelection(posicao + 1);

                            textViewStatus.setText(cmmContext.getMotoMecFertCTR().getUltApont());

                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) { // CERTIFICADO

                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) { // CERTIFICADO\n" +
                                "                            pmmContext.getConfigCTR().setPosicaoTela(16L);\n" +
                                "                            Intent it = new Intent(MenuPrincECMActivity.this, MenuCertifActivity.class);", getLocalClassName());
                        cmmContext.getConfigCTR().setPosicaoTela(16L);
                        Intent it = new Intent(MenuPrincECMActivity.this, MenuCertifActivity.class);
                        startActivity(it);
                        finish();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) { // SAIDA DA USINA

                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) { // SAIDA DA USINA", getLocalClassName());
                        if (cmmContext.getCecCTR().verPreCECAberto()) {

                            String mensagem = "O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. " +
                                    "POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";

                            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCecCTR().verPreCECAberto()) {\n" +
                                    "                                String mensagem = \"O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. \" +\n" +
                                    "                                        \"POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.\";\n" +
                                    "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(mensagem);", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage(mensagem);
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(mensagem);\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                        motoMecListView.setSelection(posicao + 1);", getLocalClassName());
                                motoMecListView.setSelection(posicao + 1);
                            });
                            alerta.show();

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                                pmmContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                    "                                Intent it = new Intent(MenuPrincECMActivity.this, FrenteActivity.class);", getLocalClassName());
                            cmmContext.getConfigCTR().setPosicaoTela(2L);
                            Intent it = new Intent(MenuPrincECMActivity.this, FrenteActivity.class);
                            startActivity(it);
                            finish();

                        }

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) { // CHEGADA CAMPO
                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) { // CHEGADA CAMPO\n" +
                                "                            String mensagem = \"\";", getLocalClassName());
                        String mensagem = "";
                        if (!cmmContext.getCecCTR().verPreCECAberto()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (!pmmContext.getCecCTR().verPreCECAberto()) {\n" +
                                    "                                mensagem = \"É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.\";", getLocalClassName());
                            mensagem = "É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.";
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                            if (cmmContext.getCecCTR().getDataChegCampo().equals("")) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCecCTR().getDataChegCampo().equals(\"\")) {\n" +
                                        "                                    mensagem = \"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec();", getLocalClassName());
                                mensagem = "INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec();
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                    mensagem = \"O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. \" +\n" +
                                        "                                            \"POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.\";", getLocalClassName());
                                mensagem = "O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. " +
                                        "POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";
                            }
                        }

                        LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(mensagem);", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(mensagem);
                        alerta.setPositiveButton("OK", (dialog, which) -> {

                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                            if (cmmContext.getCecCTR().verPreCECAberto()) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCecCTR().verPreCECAberto()) {", getLocalClassName());
                                if (cmmContext.getCecCTR().getDataChegCampo().equals("")) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCecCTR().getDataChegCampo().equals(\"\")) {\n" +
                                            "                                            pmmContext.getCecCTR().setDataChegCampo();\n" +
                                            "                                            if (connectNetwork) {\n" +
                                            "                                                pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                            "                                            } else {\n" +
                                            "                                                pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                            "                                            }", getLocalClassName());
                                    cmmContext.getCecCTR().setDataChegCampo();
                                    if (connectNetwork) {
                                        cmmContext.getConfigCTR().setStatusConConfig(1L);
                                    } else {
                                        cmmContext.getConfigCTR().setStatusConConfig(0L);
                                    }
                                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                            "textViewStatus.setText(pmmContext.getMotoMecFertCTR().getUltApont());", getLocalClassName());
                                    cmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                                    textViewStatus.setText(cmmContext.getMotoMecFertCTR().getUltApont());
                                }
                                motoMecListView.setSelection(posicao + 1);
                            }
                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 5) { // RETORNO PRA USINA

                        LogProcessoDAO.getInstance().insertLogProcesso("if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)\n" +
                                "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)\n" +
                                "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 12)) {  // ATIVIDADES NORMAIS\n" +
                                "                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(\"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                        alerta.setPositiveButton("OK", (dialog, which) -> {

                            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                                    "if (connectNetwork) {\n" +
                                    "                        pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                    "                    }\n" +
                                    "                    else{\n" +
                                    "                        pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                    "                    }", getLocalClassName());
                            if (connectNetwork) {
                                cmmContext.getConfigCTR().setStatusConConfig(1L);
                            }
                            else{
                                cmmContext.getConfigCTR().setStatusConConfig(0L);
                            }

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCecCTR().delPreCECAberto();\n" +
                                    "                                    pmmContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                    "                                    Intent it = new Intent(MenuPrincECMActivity.this, OSActivity.class);", getLocalClassName());
                            cmmContext.getCecCTR().delPreCECAberto();
                            cmmContext.getConfigCTR().setPosicaoTela(2L);
                            Intent it = new Intent(MenuPrincECMActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();

                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) { // PESAGEM

                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) { ", getLocalClassName());
                        if(!cmmContext.getMotoMecFertCTR().verifBackupApont()){

                            LogProcessoDAO.getInstance().insertLogProcesso("if(!pmmContext.getMotoMecFertCTR().verifBackupApont()){\n" +
                                    "                            if (connectNetwork) {\n" +
                                    "                                pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                    "                            } else {\n" +
                                    "                                pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                    "                            }", getLocalClassName());
                            if (connectNetwork) {
                                cmmContext.getConfigCTR().setStatusConConfig(1L);
                            } else {
                                cmmContext.getConfigCTR().setStatusConConfig(0L);
                            }

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                    "textViewStatus.setText(pmmContext.getMotoMecFertCTR().getUltApont());", getLocalClassName());
                            cmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                            textViewStatus.setText(cmmContext.getMotoMecFertCTR().getUltApont());

                        }

                        LogProcessoDAO.getInstance().insertLogProcesso("progressBar = new ProgressDialog(v.getContext());\n" +
                                "                            progressBar.setCancelable(true);\n" +
                                "                            progressBar.setMessage(\"BUSCANDO BOLETIM...\");\n" +
                                "                            progressBar.show();\n" +
                                "                            pmmContext.getCecCTR().delPreCECAberto();\n" +
                                "                            pmmContext.getCecCTR().verCEC(MenuPrincECMActivity.this, CECActivity.class, progressBar);", getLocalClassName());
                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("BUSCANDO BOLETIM...");
                        progressBar.show();

                        cmmContext.getCecCTR().delPreCECAberto();
                        cmmContext.getCecCTR().verCEC(MenuPrincECMActivity.this, CECActivity.class, progressBar);

                    } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 8)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 19)) { // DESENGATE

                        LogProcessoDAO.getInstance().insertLogProcesso("} else if ((motoMecBean.getCodFuncaoOperMotoMec() == 8)\n" +
                                "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 19)) { // DESENGATE", getLocalClassName());
                        if (cmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().hasElemCarreta()) {\n" +
                                    "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"DESEJA REALMENTE DESENGATAR AS CARRETAS?\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");
                            alerta.setPositiveButton("SIM", (dialog, which) -> {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                        pmmContext.getConfigCTR().setPosicaoTela(19L);\n" +
                                        "                                        Intent it = new Intent(MenuPrincECMActivity.this, DesengCarretaActivity.class);", getLocalClassName());
                                cmmContext.getConfigCTR().setPosicaoTela(19L);
                                Intent it = new Intent(MenuPrincECMActivity.this, DesengCarretaActivity.class);
                                startActivity(it);
                                finish();
                            });

                            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                            alerta.show();

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"POR FAVOR! ENGATE CARRETA(S) PARA DEPOIS DESENGATÁ-LAS.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR! ENGATE CARRETA(S) PARA DEPOIS DESENGATÁ-LAS.");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();

                        }

                    } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 9)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 20)) { // ENGATE

                        LogProcessoDAO.getInstance().insertLogProcesso("} else if ((motoMecBean.getCodFuncaoOperMotoMec() == 9)\n" +
                                "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 20)) {", getLocalClassName());
                        if (!cmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (!pmmContext.getMotoMecFertCTR().hasElemCarreta()) {\n" +
                                    "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"DESEJA REALMENTE ENGATAR AS CARRETAS?\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");
                            alerta.setPositiveButton("SIM", (dialog, which) -> {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                        pmmContext.getConfigCTR().setPosicaoTela(20L);\n" +
                                        "                                        Intent it = new Intent(MenuPrincECMActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                                cmmContext.getConfigCTR().setPosicaoTela(20L);
                                Intent it = new Intent(MenuPrincECMActivity.this, MsgNumCarretaActivity.class);
                                startActivity(it);
                                finish();
                            });

                            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                            alerta.show();

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"POR FAVOR! DESENGATE CARRETA(S) PARA DEPOIS ENGATÁ-LAS.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR! DESENGATE CARRETA(S) PARA DEPOIS ENGATÁ-LAS.");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();

                        }

                    }

                }

            }

        });

        buttonRetMotoMec.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!cmmContext.getMotoMecFertCTR().hasApontBolAberto()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (!pmmContext.getMotoMecFertCTR().hasApontBolAberto()) {\n" +
                        "                    Toast.makeText(MenuPrincECMActivity.this, \"POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!\",\n" +
                        "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                Toast.makeText(MenuPrincECMActivity.this, "POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!",
                        Toast.LENGTH_LONG).show();
            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"DESEJA REALMENTE ENCERRA O BOLETIM?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ENCERRA O BOLETIM?");
                alerta.setPositiveButton("SIM", (dialog, which) -> {
                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            pmmContext.getConfigCTR().setPosicaoTela(4L);\n" +
                            "                            Intent it = new Intent(MenuPrincECMActivity.this, HorimetroActivity.class);", getLocalClassName());
                    cmmContext.getConfigCTR().setPosicaoTela(4L);
                    Intent it = new Intent(MenuPrincECMActivity.this, HorimetroActivity.class);
                    startActivity(it);
                    finish();
                });

                alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                alerta.show();

            }

        });

        buttonParadaMotoMec.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(MenuPrincECMActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
            Intent it = new Intent(MenuPrincECMActivity.this, ListaParadaECMActivity.class);
            startActivity(it);
            finish();
        });

        buttonLogMotoMec.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonLogMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pmmContext.getConfigCTR().setPosicaoTela(24L);\n" +
                    "                Intent it = new Intent(MenuPrincECMActivity.this, SenhaActivity.class);", getLocalClassName());
            cmmContext.getConfigCTR().setPosicaoTela(24L);
            Intent it = new Intent(MenuPrincECMActivity.this, SenhaActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}