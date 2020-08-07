package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextEquipConfig;
    private EditText editTextSenhaConfig;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button btOkConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button btCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button btAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        editTextEquipConfig = (EditText)  findViewById(R.id.editTextEquipConfig);
        editTextSenhaConfig = (EditText)  findViewById(R.id.editTextSenhaConfig);

        pmmContext = (PMMContext) getApplication();

        if (pmmContext.getConfigCTR().hasElements()) {
            editTextEquipConfig.setText(String.valueOf(pmmContext.getConfigCTR().getEquip().getNroEquip()));
            editTextSenhaConfig.setText(pmmContext.getConfigCTR().getConfig().getSenhaConfig());
        }

        btOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextEquipConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("Pequisando o Equipamento...");
                        progressBar.show();

                        pmmContext.getConfigCTR().salvarConfig(editTextSenhaConfig.getText().toString());
                        pmmContext.getConfigCTR().verEquipConfig(editTextEquipConfig.getText().toString(), ConfigActivity.this ,MenuInicialActivity.class, progressBar);

                }

            }
        });

        btCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        btAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfigActivity.this)){
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pmmContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();
                }
            }
        });

    }
}
