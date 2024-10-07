package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.FrenteBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.PressaoBocalBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.RAtivParadaBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ApontImplMMBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CarretaBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RespItemCheckListBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextEquipConfig;
    private EditText editTextSenhaConfig;
    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonSalvarConfig =  findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        Button buttonLimparBD = findViewById(R.id.buttonLimparBD);
        editTextEquipConfig = findViewById(R.id.editTextEquipConfig);
        editTextSenhaConfig = findViewById(R.id.editTextSenhaConfig);

        cmmContext = (CMMContext) getApplication();

        if (cmmContext.getConfigCTR().hasElemConfig()) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                    "       editTextEquipConfig.setText(String.valueOf(pmmContext.getConfigCTR().getEquip().getNroEquip()));\n" +
                    "            editTextSenhaConfig.setText(pmmContext.getConfigCTR().getConfig().getSenhaConfig());", getLocalClassName());
            editTextEquipConfig.setText(String.valueOf(cmmContext.getConfigCTR().getEquip().getNroEquip()));
            editTextSenhaConfig.setText(cmmContext.getConfigCTR().getConfig().getSenhaConfig());
        }

        buttonSalvarConfig.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(!editTextEquipConfig.getText().toString().equals("") &&
                    !editTextSenhaConfig.getText().toString().equals("")){
                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextEquipConfig.getText().toString().equals(\"\") &&\n" +
                        "                        !editTextSenhaConfig.getText().toString().equals(\"\")){\n" +
                        "progressBar = new ProgressDialog(v.getContext());\n" +
                        "                    progressBar.setCancelable(true);\n" +
                        "                    progressBar.setMessage(\"Pequisando o Equipamento...\");\n" +
                        "                    progressBar.show();", getLocalClassName());
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Pequisando o Equipamento...");
                progressBar.show();

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().salvarConfig(" + editTextSenhaConfig.getText().toString() + ");\n" +
                        "                    pmmContext.getConfigCTR().verEquipConfig(" + editTextEquipConfig.getText().toString() + ", ConfigActivity.this ,TelaInicialActivity.class, progressBar);", getLocalClassName());
                cmmContext.getConfigCTR().verEquipConfig(editTextSenhaConfig.getText().toString(), BuildConfig.VERSION_NAME, editTextEquipConfig.getText().toString(), ConfigActivity.this , TelaInicialActivity.class, progressBar, getLocalClassName(), 2);

            }

        });

        buttonCancConfig.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonCancConfig.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();

        });

        buttonAtualizarBD.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n", getLocalClassName());
            if(cmmContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().hasElemConfig()) {", getLocalClassName());

                if(connectNetwork){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(connectNetwork){\n" +
                            "                    progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();", getLocalClassName());
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);", getLocalClassName());
                    cmmContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {\n" +
                            "                    });\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();
                }

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR, INSIRA O EQUIPAMENTO E SENHA ANTES DE ATUALIZAR OS DADDOS.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        });

        buttonLimparBD.setOnClickListener(v -> {

            AtividadeBean atividadeBean = new AtividadeBean();
            atividadeBean.deleteAll();

            BocalBean bocalBean = new BocalBean();
            bocalBean.deleteAll();

            EquipBean equipBean = new EquipBean();
            equipBean.deleteAll();

            EquipSegBean equipSegBean = new EquipSegBean();
            equipSegBean.deleteAll();

            FrenteBean frenteBean = new FrenteBean();
            frenteBean.deleteAll();

            FuncBean funcBean = new FuncBean();
            funcBean.deleteAll();

            ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
            itemCheckListBean.deleteAll();

            LeiraBean leiraBean = new LeiraBean();
            leiraBean.deleteAll();

            MotoMecBean motoMecBean = new MotoMecBean();
            motoMecBean.deleteAll();

            OSBean osBean = new OSBean();
            osBean.deleteAll();

            ParadaBean paradaBean = new ParadaBean();
            paradaBean.deleteAll();

            PressaoBocalBean pressaoBocalBean = new PressaoBocalBean();
            pressaoBocalBean.deleteAll();

            ProdutoBean produtoBean = new ProdutoBean();
            produtoBean.deleteAll();

            RAtivParadaBean rAtivParadaBean = new RAtivParadaBean();
            rAtivParadaBean.deleteAll();

            REquipAtivBean rEquipAtivBean = new REquipAtivBean();
            rEquipAtivBean.deleteAll();

            RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
            rFuncaoAtivParBean.deleteAll();

            ROSAtivBean rosAtivBean = new ROSAtivBean();
            rosAtivBean.deleteAll();

            TurnoBean turnoBean = new TurnoBean();
            turnoBean.deleteAll();

            ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
            apontImplMMBean.deleteAll();

            ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
            apontMMFertBean.deleteAll();

            BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
            boletimMMFertBean.deleteAll();

            CabecCheckListBean cabecCLBean = new CabecCheckListBean();
            cabecCLBean.deleteAll();

            CarregCompBean carregCompBean = new CarregCompBean();
            carregCompBean.deleteAll();

            CarretaBean carretaBean = new CarretaBean();
            carretaBean.deleteAll();

            CECBean cecBean = new CECBean();
            cecBean.deleteAll();

            ConfigBean configBean = new ConfigBean();
            configBean.deleteAll();

            InfColheitaBean infColheitaBean = new InfColheitaBean();
            infColheitaBean.deleteAll();

            InfPlantioBean infPlantioBean = new InfPlantioBean();
            infPlantioBean.deleteAll();

            LogErroBean logErroBean = new LogErroBean();
            logErroBean.deleteAll();

            MovLeiraBean movLeiraBean = new MovLeiraBean();
            movLeiraBean.deleteAll();

            PreCECBean preCECBean = new PreCECBean();
            preCECBean.deleteAll();

            RecolhFertBean recolhFertBean = new RecolhFertBean();
            recolhFertBean.deleteAll();

            RendMMBean rendMMBean = new RendMMBean();
            rendMMBean.deleteAll();

            RespItemCheckListBean respItemCLBean = new RespItemCheckListBean();
            respItemCLBean.deleteAll();

            AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("TODOS OS DADOS FORAM APAGADOS!");
            alerta.setPositiveButton("OK", (dialog, which) -> {});

            alerta.show();

        });

    }

}
