package br.com.usinasantafe.cmm.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.cmm.control.CECCTR;
import br.com.usinasantafe.cmm.control.CompostoCTR;
import br.com.usinasantafe.cmm.control.MecanicoCTR;
import br.com.usinasantafe.cmm.control.MotoMecFertCTR;
import br.com.usinasantafe.cmm.control.PneuCTR;
import br.com.usinasantafe.cmm.model.bean.variaveis.ItemManutPneuBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.conHttp.PostVerGenerico;
import br.com.usinasantafe.cmm.control.CheckListCTR;
import br.com.usinasantafe.cmm.control.ConfigCTR;
import br.com.usinasantafe.cmm.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.cmm.view.TelaInicialActivity;

import android.os.AsyncTask;
import android.util.Log;

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
    private String classe;
    private TelaInicialActivity telaInicialActivity;
    private PostVerGenerico postVerGenerico;
    public static int status;
    private int tipo;
    private String senha;
    private Long nroEquip;
    private ItemManutPneuBean itemManutPneuBean;

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
        CompostoCTR compostoCTR = new CompostoCTR();
        CECCTR cecCTR = new CECCTR();
        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        PneuCTR pneuCTR = new PneuCTR();
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("public void manipularDadosHttp(String result) {", activity);
        switch (this.classe) {
            case "Equip":
                LogProcessoDAO.getInstance().insertLogProcesso("if (this.tipo.equals(\"Equip\")) {\n" +
                        "            configCTR.receberVerifEquip(" + result + ");", activity);
                configCTR.receberVerifEquip(nroEquip, senha, telaAtual, telaProx, progressDialog, result, this.tipo);
                break;
            case "Carretel":
                LogProcessoDAO.getInstance().insertLogProcesso("if (this.tipo.equals(\"Carretel\")) {\n" +
                        "            motoMecFertCTR.receberVerifCarretel(" + result + ");", activity);
                motoMecFertCTR.receberVerifCarretel(result, activity);
                break;
            case "OS":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"OS\")) {\n" +
                        "            configCTR.receberVerifOS(" + result + ");", activity);
                configCTR.receberVerifOS(result);
                break;
            case "Atividade":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"Atividade\")) {\n" +
                        "            configCTR.receberVerifAtiv(" + result + ");", activity);
                configCTR.receberVerifAtiv(result);
                break;
            case "AtividadeECM":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"AtividadeECM\")) {\n" +
                        "            configCTR.receberVerifAtivECM(" + result + ");", activity);
                configCTR.receberVerifAtivECM(result);
                break;
            case "Atualiza":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"Atualiza\")) {\n" +
                        "            configCTR.recAtual(result.trim());\n" +
                        "            status = 3;", activity);
                configCTR.recAtual(result.trim());
                status = 3;
                LogProcessoDAO.getInstance().insertLogProcesso("this.telaInicialActivity.goMenuInicial();", activity);
                this.telaInicialActivity.goMenuInicial();
                break;
            case "CheckList":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"CheckList\")) {\n" +
                        "            checkListCTR.receberVerifCheckList(" + result + ");", activity);
                checkListCTR.receberVerifCheckList(result);
                break;
            case "OrdCarreg":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if(this.tipo.equals(\"OrdCarreg\")) {\n" +
                        "            compostoCTR.receberVerifOrdCarreg(" + result + ");", activity);
                compostoCTR.receberVerifOrdCarreg(result, activity);
                break;
            case "CEC":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"CEC\")) {\n" +
                        "            cecCTR.receberVerifCEC(" + result + ");", activity);
                cecCTR.receberVerifCEC(result);
                break;
            case "OSMecan":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"OS\")) {\n" +
                        "            configCTR.receberVerifOS(" + result + ");", activity);
                mecanicoCTR.receberVerifOSMecan(result);
                break;
            case "Pneu":
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"Pneu\")) {\n" +
                        "            motoMecFertCTR.receberVerifPneu(" + result + ");", activity);
                pneuCTR.receberVerifPneu(result, itemManutPneuBean, activity);
                break;
            case "LocalCarreg":
                LogProcessoDAO.getInstance().insertLogProcesso("case \"LocalCarreg\":" +
                        "            motoMecFertCTR.receberVerifLocaCarreg(" + result + ");", activity);
                motoMecFertCTR.receberVerifLocaCarreg(result, activity);
                break;
            default:
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "            status = 1;", activity);
                status = 1;
                break;
        }

    }

    public void verifAtualAplic(String dados, TelaInicialActivity telaInicialActivity, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.classe = "Atualiza";
        this.dados = dados;
        this.telaInicialActivity = telaInicialActivity;

        envioVerif(activity);

    }

    public void verifDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;

    }

    public void verifDados(String dados, String classe, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.classe = classe;
        this.dados = dados;

        envioVerif(activity);

    }

    public void verifDados(String dados, String classe, Context telaAtual, Class telaProx, ProgressDialog progressDialog, ItemManutPneuBean itemManutPneuBean, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.classe = classe;
        this.dados = dados;
        this.itemManutPneuBean = itemManutPneuBean;

        envioVerif(activity);

    }

    public void verifDados(Long nroEquip, String senha, String dados, String classe, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipo) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.classe = classe;
        this.dados = dados;
        this.tipo = tipo;
        this.senha = senha;
        this.nroEquip = nroEquip;

        envioVerif(activity);

    }

    public void verifDados(String dados, ProgressDialog progressDialog, String activity) {

        this.classe = "Carretel";
        this.dados = dados;
        this.progressDialog = progressDialog;

        envioVerif(activity);

    }

    public void envioVerif(String activity) {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(classe), activity};
        Map<String, Object> parametrosPost = new HashMap();
        parametrosPost.put("dado", this.dados);

        Log.i("PMM", "postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados, activity);
        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

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
            alerta.setPositiveButton("OK", (dialog, which) -> {
            });
            alerta.show();
        }
    }

}
