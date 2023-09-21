package br.com.usinasantafe.cmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.ComponenteBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ItemOSMecanBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ServicoBean;
import br.com.usinasantafe.cmm.model.dao.ApontMecanDAO;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.cmm.model.dao.ComponenteDAO;
import br.com.usinasantafe.cmm.model.dao.ItemOSMecanDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.model.dao.OSDAO;
import br.com.usinasantafe.cmm.model.dao.ServicoDAO;
import br.com.usinasantafe.cmm.util.AtualDadosServ;
import br.com.usinasantafe.cmm.util.EnvioDadosServ;
import br.com.usinasantafe.cmm.util.Json;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class MecanicoCTR {

    private ApontMecanDAO apontMecanDAO;

    public MecanicoCTR() {
    }

    public ApontMecanDAO getApontMecanDAO(){
        if (apontMecanDAO == null)
            apontMecanDAO = new ApontMecanDAO();
        return apontMecanDAO;
    }

    public void salvarApontMecan(Long seqItemOS, String activity){

        LogProcessoDAO.getInstance().insertLogProcesso("BoletimMMFertDAO boletimDAO = new BoletimMMFertDAO();\n" +
                "        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();\n" +
                "        apontMecanDAO.salvarApontMecan(seqItemOS, boletimDAO.getBolAbertoMMFert().getIdBolMMFert());", activity);
        BoletimMMFertDAO boletimDAO = new BoletimMMFertDAO();
        apontMecanDAO.salvarApontMecan(seqItemOS, boletimDAO.getBoletimMMFertAberto().getIdBolMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(activity);", activity);
        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public List<ItemOSMecanBean> itemOSMecanList(){
        ItemOSMecanDAO itemOSMecanDAO = new ItemOSMecanDAO();
        OSDAO osDAO = new OSDAO();
        return itemOSMecanDAO.itemOSMecanList(osDAO.getOS(apontMecanDAO.getApontMecanBean().getOsApontMecan()).getIdOS());
    }

    public ServicoBean getServico(Long idServItemOS){
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.getServico(idServItemOS);
    }

    public ComponenteBean getComponente(Long idCompItemOS){
        ComponenteDAO componenteDAO = new ComponenteDAO();
        return componenteDAO.getComponente(idCompItemOS);
    }

    public boolean verOSMecanBD(Long nroOS){
        ConfigCTR configCTR = new ConfigCTR();
        OSDAO osDAO = new OSDAO();
        return osDAO.verOSMecan(nroOS, configCTR.getEquip().getIdEquip());
    }

    public void verOSMecan(String nroOS, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        OSDAO osDAO = new OSDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        osDAO.verOSMecan(atualAplicDAO.getAtualNroOSIdEquip(Long.parseLong(nroOS), configCTR.getEquip().getIdEquip()), telaAtual, telaProx, progressDialog);
    }

    public boolean verApontMecanAberto(){
        BoletimMMFertDAO boletimDAO = new BoletimMMFertDAO();
        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        return apontMecanDAO.verApontAberto(boletimDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    public boolean verApontMecanNEnviado() {
        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        int qtde = apontMecanDAO.apontMecanNEnviadoList().size();
        return qtde > 0;
    }

    public void finalizarApontMecan(String activity){

        LogProcessoDAO.getInstance().insertLogProcesso("BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();\n" +
                "        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();\n" +
                "        apontMecanDAO.finalizarApont(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());" +
                "", activity);
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        apontMecanDAO.finalizarApont(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(activity);", activity);
        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public void receberVerifOSMecan(String result){

        try {
            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(retorno[0]);

                if (jsonArray.length() > 0) {

                    ConfigCTR configCTR = new ConfigCTR();
                    OSDAO osDAO = new OSDAO();
                    osDAO.recDadosOSMecan(jsonArray, configCTR.getEquip().getIdEquip());

                    jsonArray = json.jsonArray(retorno[1]);
                    ItemOSMecanDAO itemOSMecanDAO = new ItemOSMecanDAO();
                    itemOSMecanDAO.recDadosItemOSMecan(jsonArray);

                    VerifDadosServ.getInstance().pulaTela();

                } else {
                    VerifDadosServ.getInstance().msg("OS INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
                }

            }
            else{
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        ArrayList classeArrayList = new ArrayList();
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = new ArrayList();\n" +
                "        switch (" + tipoAtual + ") {", activity);
        switch (tipoAtual) {
            case "ItemOSMecan":
                classeArrayList.add("ComponenteBean");
                classeArrayList.add("ServicoBean");
                break;
        }
        LogProcessoDAO.getInstance().insertLogProcesso("AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);
    }


}
