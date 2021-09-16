package br.com.usinasantafe.pmm.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pmm.control.CECCTR;
import br.com.usinasantafe.pmm.control.CompostoCTR;
import br.com.usinasantafe.pmm.control.InformativoCTR;
import br.com.usinasantafe.pmm.view.MenuInicialActivity;
import br.com.usinasantafe.pmm.util.conHttp.PostVerGenerico;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.AtualAplicBean;
import br.com.usinasantafe.pmm.util.conHttp.UrlsConexaoHttp;

import android.os.AsyncTask;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dados;
    private String tipo;
    private MenuInicialActivity menuInicialActivity;
    private PostVerGenerico postVerGenerico;
    public static int status;

    public VerifDadosServ() {
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {

        Log.i("ECM", "RETORNO  = " + result);
        Log.i("ECM", "TIPO  = " + this.tipo);

        ConfigCTR configCTR = new ConfigCTR();
        CheckListCTR checkListCTR = new CheckListCTR();
        InformativoCTR informativoCTR = new InformativoCTR();
        CompostoCTR compostoCTR = new CompostoCTR();
        CECCTR cecCTR = new CECCTR();
        if (this.tipo.equals("Equip")) {
            configCTR.receberVerifEquip(result);
        } else if (this.tipo.equals("OS")) {
            configCTR.receberVerifOS(result);
        } else if (this.tipo.equals("Atividade")) {
            configCTR.receberVerifAtiv(result);
        } else if (this.tipo.equals("Atualiza")) {
            AtualAplicBean atualAplicBean = configCTR.recAtual(result.trim());
            status = 3;
            if (atualAplicBean.getFlagAtualApp().equals(1L)) {
                AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                atualizarAplicativo.setContext(this.menuInicialActivity);
                atualizarAplicativo.execute();
            } else {
                this.menuInicialActivity.encerrarBarra();
            }
        } else if (this.tipo.equals("CheckList")) {
            checkListCTR.receberVerifCheckList(result);
        } else if (this.tipo.equals("Informativo")) {
            informativoCTR.receberVerifInformativo(result);
        } else if(this.tipo.equals("OrdCarreg")) {
            Log.i("ECM", "CHEGOU AKI RECEBIDO 1 ");
            compostoCTR.receberVerifOrdCarreg(result);
        } else if (this.tipo.equals("CEC")) {
            cecCTR.receberVerifCEC(result);
        } else {
            status = 1;
        }

    }

    public Boolean verifRecInformativo() {
        boolean ret = false;
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElemConfig()){
            if(configCTR.getVerRecInformativo() == 1){
                ret = true;
            }
        }
        return ret;
    }

    public Boolean statusRetVerif() {
        boolean ret = false;
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElemConfig()){
            if(configCTR.getStatusRetVerif() == 1){
                ret = true;
            }
        }
        return ret;
    }

    public void verifDadosInformativo(String dados,  String tipo) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = tipo;
        this.dados = dados;

        envioVerif();

    }

    public void verifAtualAplic(String dados, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.dados = dados;
        this.menuInicialActivity = menuInicialActivity;

        envioVerif();

    }

    public void verifDados(String dados, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.tipo = tipo;
        this.dados = dados;

        envioVerif();

    }

    public void verifDados(String dados, String tipo, Context telaAtual, Class telaProx) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.tipo = tipo;
        this.dados = dados;

        envioVerif();

    }

    public void envioVerif() {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", this.dados);

        Log.i("PMM", "VERIFICA = " + this.dados);

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void reenvioVerif(){
        if(statusRetVerif()){
            envioVerif();
        }
        else {
            if (verifRecInformativo()) {
                InformativoCTR informativoCTR = new InformativoCTR();
                informativoCTR.verifDadosInformativo();
            }
        }
    }

    public void cancel() {
        status = 3;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public void pulaTela(){
        Log.i("ECM", "CHEGOU MUDANÇA TELA");
        if(status < 3){
            status = 3;
            this.progressDialog.dismiss();
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void msg(String texto){
        if(status < 3){
            status = 3;
            this.progressDialog.dismiss();
            AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage(texto);
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alerta.show();
        }
    }

    public void pulaTelaSemBarra(){
        if(status < 3){
            status = 3;
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void pulaTelaSemBarra(Class telaProx){
        if(status < 3){
            status = 3;
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
