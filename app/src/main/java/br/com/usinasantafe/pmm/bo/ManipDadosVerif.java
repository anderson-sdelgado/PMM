package br.com.usinasantafe.pmm.bo;

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
import br.com.usinasantafe.pmm.conWEB.ConHttpPostVerGenerico;
import br.com.usinasantafe.pmm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pmm.pst.GenericRecordable;
import br.com.usinasantafe.pmm.to.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafDispEquipPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafPlanRealPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafProdPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafQualPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.OSTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.PneuTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.REquipAtivTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.REquipPneuTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ROSAtivTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.AtualAplicTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.PerdaTO;

import android.os.AsyncTask;

/**
 * Created by anderson on 16/11/2015.
 */
public class ManipDadosVerif {

    private static ManipDadosVerif instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx1;
    private Class telaProx2;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private AtualAplicTO atualAplicTO;
    private MenuInicialActivity menuInicialActivity;
    private ConHttpPostVerGenerico conHttpPostVerGenerico;
    private boolean verTerm;
    private String senha;
    private int verTelaAtualPerda = 0;

    public ManipDadosVerif() {
        //genericRecordable = new GenericRecordable();
    }

    public static ManipDadosVerif getInstance() {
        if (instance == null)
            instance = new ManipDadosVerif();
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

    public void verAtualizacao(AtualAplicTO atualAplicTO, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.atualAplicTO = atualAplicTO;
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

        envioAtualAplic();

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        verTerm = false;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx1 = telaProx;
        this.progressDialog = progressDialog;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx1 = telaProx;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx1, Class telaProx2) {

        verTerm = false;
        verTelaAtualPerda = 1;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx1 = telaProx1;
        this.telaProx2 = telaProx2;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void verDadosPerda() {

        verTerm = true;
        verTelaAtualPerda = 2;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = "Perda";

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimList = boletimMMTO.get("statusBoletim", 1L);
        boletimMMTO = (BoletimMMTO) boletimList.get(0);
        boletimList.clear();

        this.dado = String.valueOf(boletimMMTO.getCodMotoBoletim());

        envioDados();

    }

    public void envioAtualAplic() {

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

    public void envioDados() {

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
                recDadosEquip(result);
            } else if (this.tipo.equals("OS")) {
                recDadosOS(result);
            } else if (this.tipo.equals("Atividade")) {
                recDadosAtiv(result);
            } else if (this.tipo.equals("AtualParada")) {
                recDadosParada(result);
            } else if (this.tipo.equals("Atualiza")) {

                String verAtualizacao = result.trim();

                if (verAtualizacao.equals("S")) {
                    AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                    atualizarAplicativo.setContext(this.menuInicialActivity);
                    atualizarAplicativo.execute();
                } else {
                    this.menuInicialActivity.startTimer(verAtualizacao);
                }

            } else if (this.tipo.equals("Operador")) {
                recDadosGenerico(result, "MotoristaTO");
            } else if (this.tipo.equals("Turno")) {
                recDadosGenerico(result, "TurnoTO");
            } else if (this.tipo.equals("EquipSeg")) {
                recDadosGenerico(result, "EquipSegTO");
            } else if (this.tipo.equals("CheckList")) {
                recDadosCheckList(result);
            } else if (this.tipo.equals("GrafPlantio")) {
                recDadosGrafico(result);
            } else if (this.tipo.equals("Pneu")) {
                recDadosPneu(result);
            } else if (this.tipo.equals("Bocal")) {
                recDadosGenerico(result, "BocalTO");
            } else if (this.tipo.equals("PressaoBocal")) {
                recDadosGenerico(result, "PressaoBocalTO");
            } else if (this.tipo.equals("Perda")) {
                recPerda(result);
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

    public void cabecCheckList(String data) {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimList = boletimMMTO.get("statusBoletim", 1L);
        boletimMMTO = (BoletimMMTO) boletimList.get(0);

        EquipTO equipTO = new EquipTO();
        List equipList = equipTO.get("idEquip", boletimMMTO.getCodEquipBoletim());
        equipTO = (EquipTO) equipList.get(0);
        equipList.clear();

        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
        List itemCheckList = itemCheckListTO.get("idChecklist", equipTO.getIdChecklist());
        Long qtde = (long) itemCheckList.size();
        itemCheckList.clear();

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        cabecCheckListTO.setDtCab(Tempo.getInstance().datahora());
        cabecCheckListTO.setEquipCab(equipTO.getNroEquip());
        cabecCheckListTO.setFuncCab(boletimMMTO.getCodMotoBoletim());
        cabecCheckListTO.setTurnoCab(boletimMMTO.getCodTurnoBoletim());
        cabecCheckListTO.setQtdeItemCab(qtde);
        cabecCheckListTO.setStatusCab(1L);
        cabecCheckListTO.setDtAtualCab(data);
        cabecCheckListTO.insert();

    }

    public void recDadosEquip(String result) {

        try {

            int pos1 = result.indexOf("#") + 1;
            int pos2 = result.indexOf("|") + 1;
            String objPrinc = result.substring(0, (pos1 - 1));
            String objSeg = result.substring(pos1, (pos2 - 1));
            String objTerc = result.substring(pos2);

            JSONObject jObj = new JSONObject(objPrinc);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                EquipTO equipTO = new EquipTO();
                equipTO.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    equipTO = gson.fromJson(objeto.toString(), EquipTO.class);
                    equipTO.insert();

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                REquipAtivTO rEquipAtivTO = new REquipAtivTO();
                rEquipAtivTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    REquipAtivTO rEquipAtiv = gson.fromJson(objeto.toString(), REquipAtivTO.class);
                    rEquipAtiv.insert();

                }

                jObj = new JSONObject(objTerc);
                jsonArray = jObj.getJSONArray("dados");

                REquipPneuTO rEquipPneuTO = new REquipPneuTO();
                rEquipPneuTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    rEquipPneuTO = gson.fromJson(objeto.toString(), REquipPneuTO.class);
                    rEquipPneuTO.insert();

                }

                ConfigTO configTO = new ConfigTO();
                configTO.deleteAll();
                configTO.setEquipConfig(equipTO.getIdEquip());
                configTO.setClasseEquipConfig(equipTO.getCodClasseEquip());
                configTO.setHorimetroConfig(equipTO.getHorimetroEquip());
                configTO.setUltTurnoCLConfig(0L);
                configTO.setDtUltCLConfig("");
                configTO.setDtUltApontConfig("");
                configTO.setDtServConfig("");
                configTO.setDifDthrConfig(0L);
                configTO.setSenhaConfig(this.senha);
                configTO.setVisDadosConfig(0L);
                configTO.insert();
                configTO.commit();

                this.progressDialog.dismiss();

                Intent it = new Intent(telaAtual, telaProx1);
                telaAtual.startActivity(it);

            } else {

                this.progressDialog.dismiss();

                AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("EQUIPAMENTO INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");

                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
                alerta.show();

            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recDadosOS(String result) {

        try {

            if (!result.contains("exceeded")) {

                int posicao = result.indexOf("#") + 1;
                String objPrinc = result.substring(0, result.indexOf("#"));
                String objSeg = result.substring(posicao);

                JSONObject jObj = new JSONObject(objPrinc);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        OSTO osTO = gson.fromJson(objeto.toString(), OSTO.class);
                        osTO.insert();

                    }

                    jObj = new JSONObject(objSeg);
                    jsonArray = jObj.getJSONArray("dados");

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        ROSAtivTO rosAtivTO = gson.fromJson(objeto.toString(), ROSAtivTO.class);
                        rosAtivTO.insert();

                    }

                    ConfigTO configTO = new ConfigTO();
                    List configList = configTO.all();
                    configTO = (ConfigTO) configList.get(0);
                    configTO.setOsConfig(Long.parseLong(this.dado));
                    configTO.setStatusConConfig(1L);
                    configTO.update();

                    if(!verTerm){

                        verTerm = true;
                        Intent it = new Intent(telaAtual, telaProx1);
                        telaAtual.startActivity(it);

                    }

                } else {

                    if(!verTerm) {

                        verTerm = true;
                        this.progressDialog.dismiss();

                        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OS INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                        alerta.show();

                    }

                }

            } else {

                ConfigTO configTO = new ConfigTO();
                List configList = configTO.all();
                configTO = (ConfigTO) configList.get(0);
                configTO.setOsConfig(Long.parseLong(this.dado));
                configTO.setStatusConConfig(0L);
                configTO.update();

                if(!verTerm) {
                    this.progressDialog.dismiss();

                    AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();
                }

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recDadosAtiv(String result) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                int pos2 = result.indexOf("|") + 1;
                String objPrim = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1, (pos2 - 1));
                String objTerc = result.substring(pos2);

                JSONObject jObj = new JSONObject(objPrim);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                REquipAtivTO rEquipAtivTO = new REquipAtivTO();
                rEquipAtivTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    REquipAtivTO rEquipAtiv = gson.fromJson(objeto.toString(), REquipAtivTO.class);
                    rEquipAtiv.insert();

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    ROSAtivTO rosAtivTO = new ROSAtivTO();
                    rosAtivTO.deleteAll();

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        ROSAtivTO rosAtiv = gson.fromJson(objeto.toString(), ROSAtivTO.class);
                        rosAtiv.insert();

                    }

                }

                jObj = new JSONObject(objTerc);
                jsonArray = jObj.getJSONArray("dados");

                AtividadeTO atividadeTO = new AtividadeTO();
                atividadeTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    AtividadeTO atividade = gson.fromJson(objeto.toString(), AtividadeTO.class);
                    atividade.insert();

                }

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx1);
                telaAtual.startActivity(it);

            } else {

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx1);
                telaAtual.startActivity(it);

            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recDadosGenerico(String result, String c) {

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");
                Class classe = Class.forName(urlsConexaoHttp.localPSTEstatica + c);

                if (jsonArray.length() > 0) {

                    genericRecordable = new GenericRecordable();
                    genericRecordable.deleteAll(classe);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

                    }

                    this.progressDialog.dismiss();

                } else {

                    this.progressDialog.dismiss();

                }

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recDadosParada(String result) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                String objPrinc = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1);

                JSONObject jObj = new JSONObject(objPrinc);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                RAtivParadaTO rAtivParadaTO = new RAtivParadaTO();
                rAtivParadaTO.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    RAtivParadaTO rAtivParada = gson.fromJson(objeto.toString(), RAtivParadaTO.class);
                    rAtivParada.insert();

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                ParadaTO paradaTO = new ParadaTO();
                paradaTO.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    ParadaTO parada = gson.fromJson(objeto.toString(), ParadaTO.class);
                    parada.insert();

                }

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx1);
                telaAtual.startActivity(it);

            } else {

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx1);
                telaAtual.startActivity(it);

            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recDadosCheckList(String result) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                String objPrinc = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1);

                JSONObject jObj = new JSONObject(objPrinc);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                EquipTO equipTO = new EquipTO();
                equipTO.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    equipTO = gson.fromJson(objeto.toString(), EquipTO.class);
                    equipTO.insert();

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                itemCheckListTO.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    ItemCheckListTO itemCheckList = gson.fromJson(objeto.toString(), ItemCheckListTO.class);
                    itemCheckList.insert();

                }

                cabecCheckList(Tempo.getInstance().datahora());
                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx1);
                telaAtual.startActivity(it);

            } else {

                cabecCheckList("0");
                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx1);
                telaAtual.startActivity(it);

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recDadosGrafico(String result) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("#") + 1;
                int pos2 = result.indexOf("|") + 1;
                int pos3 = result.indexOf("?") + 1;
                String objPrinc = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1, (pos2 - 1));
                String objTerc = result.substring(pos2, (pos3 - 1));
                String objQuar = result.substring(pos3);

                JSONObject jObj = new JSONObject(objPrinc);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    GrafProdPlantioTO grafProdPlantioTO = new GrafProdPlantioTO();
                    grafProdPlantioTO.deleteAll();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        grafProdPlantioTO = gson.fromJson(objeto.toString(), GrafProdPlantioTO.class);
                        grafProdPlantioTO.insert();

                    }

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    GrafPlanRealPlantioTO grafPlanRealPlantioTO = new GrafPlanRealPlantioTO();
                    grafPlanRealPlantioTO.deleteAll();

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        grafPlanRealPlantioTO = gson.fromJson(objeto.toString(), GrafPlanRealPlantioTO.class);
                        grafPlanRealPlantioTO.insert();

                    }

                }

                jObj = new JSONObject(objTerc);
                jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    genericRecordable = new GenericRecordable();
                    GrafDispEquipPlantioTO grafDispEquipPlantioTO = new GrafDispEquipPlantioTO();
                    grafDispEquipPlantioTO.deleteAll();

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        grafDispEquipPlantioTO = gson.fromJson(objeto.toString(), GrafDispEquipPlantioTO.class);
                        grafDispEquipPlantioTO.insert();

                    }

                }

                jObj = new JSONObject(objQuar);
                jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    genericRecordable = new GenericRecordable();
                    GrafQualPlantioTO grafQualPlantioTO = new GrafQualPlantioTO();
                    grafQualPlantioTO.deleteAll();

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        grafQualPlantioTO = gson.fromJson(objeto.toString(), GrafQualPlantioTO.class);
                        grafQualPlantioTO.insert();

                    }

                }

            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recDadosPneu(String result) {

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    PneuTO pneuTO = new PneuTO();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        pneuTO = gson.fromJson(objeto.toString(), PneuTO.class);
                        pneuTO.insert();

                    }

                    if(!verTerm) {
                        verTerm = true;
                        Intent it = new Intent(telaAtual, telaProx1);
                        telaAtual.startActivity(it);
                    }

                } else {

                    if(!verTerm) {
                        verTerm = true;
                        this.progressDialog.dismiss();

                        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("PNEU INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                        alerta.show();
                    }

                }

            } else {

                if(!verTerm) {
                    this.progressDialog.dismiss();

                    AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();
                }

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void recPerda(String result) {

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    PerdaTO perdaTO = new PerdaTO();
                    perdaTO.deleteAll();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        perdaTO = gson.fromJson(objeto.toString(), PerdaTO.class);
                        perdaTO.insert();

                    }

                    ConfigTO configTO = new ConfigTO();
                    List configList = configTO.all();
                    configTO = (ConfigTO) configList.get(0);
                    configList.clear();
                    configTO.setVisDadosConfig(1L);
                    configTO.update();

                    if((!verTerm) && (verTelaAtualPerda == 1)){
                        verTerm = true;
                        verTelaAtualPerda = 3;
                        Intent it = new Intent(telaAtual, telaProx1);
                        telaAtual.startActivity(it);
                    }

                    verTelaAtualPerda = 3;

                } else {

                    ConfigTO configTO = new ConfigTO();
                    List configList = configTO.all();
                    configTO = (ConfigTO) configList.get(0);
                    configList.clear();
                    configTO.setVisDadosConfig(2L);
                    configTO.update();

                    if((!verTerm) && (verTelaAtualPerda == 1)){
                        verTerm = true;
                        verTelaAtualPerda = 4;
                        Intent it = new Intent(telaAtual, telaProx2);
                        telaAtual.startActivity(it);
                    }

                    verTelaAtualPerda = 4;

                }

            } else {

                ConfigTO configTO = new ConfigTO();
                List configList = configTO.all();
                configTO = (ConfigTO) configList.get(0);
                configList.clear();
                configTO.setVisDadosConfig(0L);
                configTO.update();

                if((!verTerm) && (verTelaAtualPerda == 1)){
                    verTerm = true;
                    verTelaAtualPerda = 2;
                    Intent it = new Intent(telaAtual, telaProx2);
                    telaAtual.startActivity(it);
                }

                verTelaAtualPerda = 2;

            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public boolean isVerTerm() {
        return verTerm;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getVerTelaAtualPerda() {
        return verTelaAtualPerda;
    }

    public void setVerTelaAtualPerda(int verTelaAtualPerda) {
        this.verTelaAtualPerda = verTelaAtualPerda;
    }
}
