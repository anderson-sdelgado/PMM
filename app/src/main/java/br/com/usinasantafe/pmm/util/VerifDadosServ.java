package br.com.usinasantafe.pmm.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pmm.MenuInicialActivity;
import br.com.usinasantafe.pmm.bo.AtualizarAplicativo;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.dao.EquipDAO;
import br.com.usinasantafe.pmm.dao.InfoColheitaDAO;
import br.com.usinasantafe.pmm.dao.OSDAO;
import br.com.usinasantafe.pmm.pst.GenericRecordable;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.to.variaveis.AtualAplicTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.InfoColheitaTO;

import android.os.AsyncTask;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx1;
    private Class telaProx2;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private MenuInicialActivity menuInicialActivity;
    private ConHttpPostVerGenerico conHttpPostVerGenerico;
    private boolean verTerm;

    private ConfigCTR configCTR;

    public VerifDadosServ() {
        //genericRecordable = new GenericRecordable();
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {
        if (!result.equals("")) {
            retornoVerifNormal(result);
        }
    }

    public String manipLocalClasse(String classe) {
        if (classe.contains("TO")) {
            classe = urlsConexaoHttp.localPSTEstatica + classe;
        }
        return classe;
    }

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

        AtualAplicTO atualAplicTO = new AtualAplicTO();
        atualAplicTO.setVersaoAtual(versaoAplic);

        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();

        atualAplicTO.setIdEquipAtualizacao(equipTO.getNroEquip());
        atualAplicTO.setIdCheckList(equipTO.getIdCheckList());

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicTO, atualAplicTO.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        conHttpPostVerGenerico = new ConHttpPostVerGenerico();
        conHttpPostVerGenerico.setParametrosPost(parametrosPost);
        conHttpPostVerGenerico.execute(url);

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        this.verTerm = false;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx1 = telaProx;
        this.progressDialog = progressDialog;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx1, Class telaProx2) {

        this.verTerm = false;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx1 = telaProx1;
        this.telaProx2 = telaProx2;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void verDadosPerda() {

        verTerm = true;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = "Colheita";
        ConfigCTR configCTR = new ConfigCTR();
        BoletimCTR boletimCTR = new BoletimCTR();
        if(configCTR.getEquip().getTipo() == 1){
            this.dado = String.valueOf(boletimCTR.getBolMMAberto().getMatricFuncBolMM());
        }
        else{
            this.dado = String.valueOf(boletimCTR.getBolFertAberto().getMatricFuncBolFert());
        }
        envioDados();

    }


    public void envioDados() {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", String.valueOf(dado));

        Log.i("PMM", "VERIFICA = " + String.valueOf(dado));

        conHttpPostVerGenerico = new ConHttpPostVerGenerico();
        conHttpPostVerGenerico.setParametrosPost(parametrosPost);
        conHttpPostVerGenerico.execute(url);

    }

    public void retornoVerifNormal(String result) {

        try {

            if (this.tipo.equals("Equip")) {
                EquipDAO equipDAO = new EquipDAO();
                equipDAO.recDadosEquip(result);
            } else if (this.tipo.equals("OS")) {
                OSDAO osDAO = new OSDAO();
                osDAO.recDadosOS(result);
            } else if (this.tipo.equals("Atividade")) {
                AtividadeDAO atividadeDAO = new AtividadeDAO();
                atividadeDAO.recDadosAtiv(result);
            } else if (this.tipo.equals("Atualiza")) {
                String verAtualizacao = result.trim();
                if (verAtualizacao.equals("S")) {
                    AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                    atualizarAplicativo.setContext(this.menuInicialActivity);
                    atualizarAplicativo.execute();
                } else {
                    this.menuInicialActivity.startTimer(verAtualizacao);
                }
            } else if (this.tipo.equals("CheckList")) {
                CheckListCTR checkListCTR = new CheckListCTR();
                checkListCTR.recDadosCheckList(result);
            } else if (this.tipo.equals("Colheita")) {
                InfoColheitaDAO infoColheitaDAO = new InfoColheitaDAO();
                infoColheitaDAO.recColheita(result);
            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void cancelVer() {
        verTerm = true;
        if (conHttpPostVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            conHttpPostVerGenerico.cancel(true);
        }
    }

    public void pulaTelaSemTerm(){
        this.progressDialog.dismiss();
        Intent it = new Intent(telaAtual, telaProx1);
        telaAtual.startActivity(it);
    }

    public void msg(String texto){
        this.progressDialog.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        alerta.show();
    }

    public void pulaTelaComTerm(){
        if(!verTerm){
            this.progressDialog.dismiss();
            this.verTerm = true;
            Intent it = new Intent(telaAtual, telaProx1);
            telaAtual.startActivity(it);
        }
    }

    public void pulaTelaComTermSemBarra(){
        if(!verTerm){
            this.verTerm = true;
            Intent it = new Intent(telaAtual, telaProx1);
            telaAtual.startActivity(it);
        }
    }

    public void pulaTelaDadosColheita(){
        if(!verTerm){
            this.progressDialog.dismiss();
            this.verTerm = true;
            Intent it = new Intent(telaAtual, telaProx2);
            telaAtual.startActivity(it);
        }
    }

    public boolean isVerTerm() {
        return verTerm;
    }


}
