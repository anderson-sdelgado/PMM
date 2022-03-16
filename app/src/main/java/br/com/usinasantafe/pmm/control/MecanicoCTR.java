package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.ComponenteBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemOSMecanBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ServicoBean;
import br.com.usinasantafe.pmm.model.dao.ApontMecanDAO;
import br.com.usinasantafe.pmm.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.pmm.model.dao.ComponenteDAO;
import br.com.usinasantafe.pmm.model.dao.ItemOSMecanDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.model.dao.OSMecanDAO;
import br.com.usinasantafe.pmm.model.dao.ServicoDAO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

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
        apontMecanDAO.salvarApontMecan(seqItemOS, boletimDAO.getBolAbertoMMFert().getIdBolMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(activity);", activity);
        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public List<ItemOSMecanBean> itemOSMecanList(){
        ItemOSMecanDAO itemOSMecanDAO = new ItemOSMecanDAO();
        OSMecanDAO osMecanDAO = new OSMecanDAO();
        return itemOSMecanDAO.itemOSMecanList(osMecanDAO.getOSMecan(apontMecanDAO.getApontMecanBean().getOsApontMecan()).getIdOS());
    }

    public ServicoBean getServico(Long idServItemOS){
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.getServico(idServItemOS);
    }

    public ComponenteBean getComponente(Long idCompItemOS){
        ComponenteDAO componenteDAO = new ComponenteDAO();
        return componenteDAO.getComponente(idCompItemOS);
    }

    public void verOSMecan(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        OSMecanDAO osMecanDAO = new OSMecanDAO();
        osMecanDAO.verOSMecan(dado, telaAtual, telaProx, progressDialog, activity);
    }

    public boolean verOSMecanBD(Long nroOS){
        OSMecanDAO osMecanDAO = new OSMecanDAO();
        return osMecanDAO.verOSMecanBD(nroOS);
    }

    public boolean verApontMecanAberto(){
        BoletimMMFertDAO boletimDAO = new BoletimMMFertDAO();
        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        return apontMecanDAO.verApontAberto(boletimDAO.getBolAbertoMMFert().getIdBolMMFert());
    }

    public Boolean verApontMecanAbertoNEnviado() {
        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        return apontMecanDAO.apontMecanAbertoNEnviadoList().size() > 0;
    }

    public Boolean verApontMecanFechadoNEnviado() {
        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        return apontMecanDAO.apontMecanFechadoNEnviadoList().size() > 0;
    }

    public void finalizarApontMecan(String activity){

        LogProcessoDAO.getInstance().insertLogProcesso("BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();\n" +
                "        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();\n" +
                "        apontMecanDAO.finalizarApont(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());" +
                "", activity);
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        apontMecanDAO.finalizarApont(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(activity);", activity);
        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public void receberVerifOSMecan(String result){

        try {
            if (!result.contains("exceeded")) {

                int posicao = result.indexOf("#") + 1;
                String objPrinc = result.substring(0, result.indexOf("#"));
                String objSeg = result.substring(posicao);

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(objPrinc);

                if (jsonArray.length() > 0) {

                    OSMecanDAO osMecanDAO = new OSMecanDAO();
                    osMecanDAO.recDadosOSMecan(jsonArray);

                    jsonArray = json.jsonArray(objSeg);
                    ItemOSMecanDAO itemOSMecanDAO = new ItemOSMecanDAO();
                    itemOSMecanDAO.recDadosItemOS(jsonArray);

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
