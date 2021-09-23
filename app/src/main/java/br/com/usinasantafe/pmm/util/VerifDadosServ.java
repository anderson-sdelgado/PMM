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
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
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

    public void manipularDadosHttp(String result, String activity) {

        ConfigCTR configCTR = new ConfigCTR();
        CheckListCTR checkListCTR = new CheckListCTR();
        InformativoCTR informativoCTR = new InformativoCTR();
        CompostoCTR compostoCTR = new CompostoCTR();
        CECCTR cecCTR = new CECCTR();
        LogProcessoDAO.getInstance().insert("public void manipularDadosHttp(String result) {", activity);
        if (this.tipo.equals("Equip")) {
            LogProcessoDAO.getInstance().insert("if (this.tipo.equals(\"Equip\")) {\n" +
                    "            configCTR.receberVerifEquip(" + result + ");", activity);
            configCTR.receberVerifEquip(result);
        } else if (this.tipo.equals("OS")) {
            LogProcessoDAO.getInstance().insert("} else if (this.tipo.equals(\"OS\")) {\n" +
                    "            configCTR.receberVerifOS(" + result + ");", activity);
            configCTR.receberVerifOS(result);
        } else if (this.tipo.equals("Atividade")) {
            LogProcessoDAO.getInstance().insert("} else if (this.tipo.equals(\"Atividade\")) {\n" +
                    "            configCTR.receberVerifAtiv(" + result + ");", activity);
            configCTR.receberVerifAtiv(result);
        } else if (this.tipo.equals("Atualiza")) {
            LogProcessoDAO.getInstance().insert("} else if (this.tipo.equals(\"Atualiza\")) {\n" +
                    "            AtualAplicBean atualAplicBean = configCTR.recAtual(result.trim());\n" +
                    "            status = 3;", activity);
            AtualAplicBean atualAplicBean = configCTR.recAtual(result.trim());
            status = 3;
            if (atualAplicBean.getFlagAtualApp().equals(1L)) {
                LogProcessoDAO.getInstance().insert("if (atualAplicBean.getFlagAtualApp().equals(1L)) {\n" +
                        "                AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();\n" +
                        "                atualizarAplicativo.setContext(this.menuInicialActivity);\n" +
                        "                atualizarAplicativo.execute();", activity);
                AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                atualizarAplicativo.setContext(this.menuInicialActivity);
                atualizarAplicativo.execute();
            } else {
                LogProcessoDAO.getInstance().insert("} else {\n" +
                        "                this.menuInicialActivity.encerrarBarra();", activity);
                this.menuInicialActivity.encerrarBarra();
            }
        } else if (this.tipo.equals("CheckList")) {
            LogProcessoDAO.getInstance().insert("} else if (this.tipo.equals(\"CheckList\")) {\n" +
                    "            checkListCTR.receberVerifCheckList(" + result + ");", activity);
            checkListCTR.receberVerifCheckList(result);
        } else if (this.tipo.equals("Informativo")) {
            LogProcessoDAO.getInstance().insert("} else if (this.tipo.equals(\"Informativo\")) {\n" +
                    "            informativoCTR.receberVerifInformativo(" + result + ");", activity);
            informativoCTR.receberVerifInformativo(result);
        } else if(this.tipo.equals("OrdCarreg")) {
            LogProcessoDAO.getInstance().insert("} else if(this.tipo.equals(\"OrdCarreg\")) {\n" +
                    "            compostoCTR.receberVerifOrdCarreg(" + result + ");", activity);
            compostoCTR.receberVerifOrdCarreg(result);
        } else if (this.tipo.equals("CEC")) {
            LogProcessoDAO.getInstance().insert("} else if (this.tipo.equals(\"CEC\")) {\n" +
                    "            cecCTR.receberVerifCEC(" + result + ");", activity);
            cecCTR.receberVerifCEC(result);
        } else {
            LogProcessoDAO.getInstance().insert("} else {\n" +
                    "            status = 1;", activity);
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

    public void verifDadosInformativo(String dados,  String tipo, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = tipo;
        this.dados = dados;

        envioVerif(activity);

    }

    public void verifAtualAplic(String dados, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.dados = dados;
        this.menuInicialActivity = menuInicialActivity;

        envioVerif(activity);

    }

    public void verifDados(String dados, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.tipo = tipo;
        this.dados = dados;

        envioVerif(activity);

    }

    public void verifDados(String dados, String tipo, Context telaAtual, Class telaProx, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.tipo = tipo;
        this.dados = dados;

        envioVerif(activity);

    }

    public void envioVerif(String activity) {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo), activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", this.dados);

        LogProcessoDAO.getInstance().insert("postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(tipo) + "'); - Dados de Envio = " + this.dados, activity);
        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void reenvioVerif(String activity){
        LogProcessoDAO.getInstance().insert("statusRetVerif()", activity);
        if(statusRetVerif()){
            LogProcessoDAO.getInstance().insert("envioVerif()", activity);
            envioVerif(activity);
        }
        else {
            LogProcessoDAO.getInstance().insert("verifRecInformativo()", activity);
            if (verifRecInformativo()) {
                InformativoCTR informativoCTR = new InformativoCTR();
                LogProcessoDAO.getInstance().insert("informativoCTR.verifDadosInformativo()", activity);
                informativoCTR.verifDadosInformativo(activity);
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
