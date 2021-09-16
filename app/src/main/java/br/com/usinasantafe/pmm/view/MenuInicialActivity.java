package br.com.usinasantafe.pmm.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView listView;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pmmContext = (PMMContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if(EnvioDadosServ.getInstance().verifDadosEnvio()){
            if(connectNetwork){
                EnvioDadosServ.getInstance().envioDados();
            }
            else{
                EnvioDadosServ.status = 1;
            }
        }
        else{
            EnvioDadosServ.status = 3;
        }

        VerifDadosServ.status = 3;

        verifEnvio();

        progressBar = new ProgressDialog(this);

        if(pmmContext.getMotoMecFertCTR().verBolAberto()){
            if(pmmContext.getCheckListCTR().verCabecAberto()){
                encerrarBarra();
                pmmContext.getCheckListCTR().clearRespCabecAberto();
                pmmContext.getCheckListCTR().setPosCheckList(1);
                Intent it = new Intent(MenuInicialActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
            else{
                encerrarBarra();
                pmmContext.getConfigCTR().setPosicaoTela(8L);
                if(VerifDadosServ.getInstance().verifRecInformativo()){
                    if(connectNetwork){
                        pmmContext.getInformativoCTR().verifDadosInformativo();
                    }
                    else {
                        VerifDadosServ.status = 1;
                    }
                }

                if(PMMContext.aplic == 1){
                    Intent it = new Intent(MenuInicialActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 2){
                    if(pmmContext.getCecCTR().verPreCECAberto()){
                        pmmContext.getCecCTR().clearPreCECAberto();
                    }
                    Intent it = new Intent(MenuInicialActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 3){
                    Intent it = new Intent(MenuInicialActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
        else{
            atualizarAplic();
        }

        ArrayList<String> itens = new ArrayList<>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");
        itens.add("REENVIO DE DADOS");
        itens.add("ATUALIZAR APLICATIVO");

        AdapterList adapterList = new AdapterList(this, itens);
        listView = findViewById(R.id.listaMenuInicial);
        listView.setAdapter(adapterList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("BOLETIM")) {
                    if (pmmContext.getMotoMecFertCTR().hasElemFunc()
                            && pmmContext.getConfigCTR().hasElemConfig()
                            && (VerifDadosServ.status == 3)) {
                        pmmContext.getConfigCTR().setPosicaoTela(1L);
                        clearBD();
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuInicialActivity.this, OperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("SAIR")) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (text.equals("ATUALIZAR DADOS")) {

                    if (connectNetwork) {
                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        pmmContext.getConfigCTR().atualTodasTabelas(MenuInicialActivity.this, progressBar);

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
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
                else if (text.equals("ATUALIZAR APLICATIVO")) {
                    atualizarAplic();
                }

            }

        });

    }

    public void atualizarAplic(){
        if (connectNetwork) {
            if (pmmContext.getConfigCTR().hasElemConfig()) {
                progressBar.setCancelable(true);
                progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                progressBar.show();
                customHandler.postDelayed(updateTimerThread, 10000);
                pmmContext.getConfigCTR().verAtualAplic(pmmContext.versaoAplic, this, progressBar);
            }
            else{
                VerifDadosServ.status = 3;
                encerrarBarra();
            }
        } else {
            VerifDadosServ.status = 3;
        }
    }

    public void encerrarBarra() {
        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            verifEnvio();
            if(VerifDadosServ.status < 3) {
                VerifDadosServ.getInstance().cancel();
                encerrarBarra();
            }
            customHandler.postDelayed(this, 10000);
        }
    };

    public void verifEnvio(){
        if (pmmContext.getConfigCTR().hasElemConfig()) {
            pmmContext.getConfigCTR().setStatusRetVerif(0L);
            if (EnvioDadosServ.status == 1) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            } else if (EnvioDadosServ.status == 2) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            } else if (EnvioDadosServ.status == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
        } else {
            textViewProcesso.setTextColor(Color.RED);
            textViewProcesso.setText("Aparelho sem Equipamento");
        }
    }

    public void clearBD() {

        if(PMMContext.aplic == 1){
            pmmContext.getMotoMecFertCTR().impleMMDelAll();
            pmmContext.getConfigCTR().osDelAll();
            pmmContext.getConfigCTR().rOSAtivDelAll();
        }

    }

}
