package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.pmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pmm.model.dao.CECDAO;
import br.com.usinasantafe.pmm.model.dao.CarretaDAO;
import br.com.usinasantafe.pmm.model.dao.EquipDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.dao.PreCECDAO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class CECCTR {

    public CECCTR() {
    }

    ///////////////////////////////////// CABECALHO //////////////////////////////////////////////

    public void salvarPrecCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.abrirPreCEC();
    }

    public void clearPreCECAberto(){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.delCarreta();
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.clearPreCECAberto();
    }

    public void fechaPreCEC(){

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        ConfigCTR configCTR = new ConfigCTR();
        PreCECDAO preCECDAO = new PreCECDAO();

        preCECDAO.fechaPreCEC(motoMecFertCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert()
                , motoMecFertCTR.getTurnoId(motoMecFertCTR.getBoletimMMFertAberto().getIdTurnoBolMMFert()).getCodTurno()
                , configCTR.getEquip().getNroEquip());
        EnvioDadosServ.getInstance().envioDados(null);
    }

    public String dadosEnvioPreCEC(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.dadosEnvioPreCEC();
    }

    public void updPreCEC(String result, String activity){

        try{

            int pos1 = result.indexOf("_") + 1;
            String objPrinc = result.substring(pos1);

            PreCECDAO preCECDAO = new PreCECDAO();
            preCECDAO.atualPreCEC(objPrinc);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void receberVerifCEC(String result){

        try{

            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                PreCECDAO preCECDAO = new PreCECDAO();
                preCECDAO.atualPreCEC(retorno[0]);

                CECDAO cecDAO = new CECDAO();
                cecDAO.recDadosCEC(retorno[1]);

                ConfigCTR configCTR = new ConfigCTR();
                configCTR.setStatusRetVerif(0L);

                VerifDadosServ.getInstance().pulaTela();

            } else {
                VerifDadosServ.status = 1;
            }

        }
        catch(Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void delPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.delPreCECAberto();
    }

    public void delCEC(){
        CECDAO cecDAO = new CECDAO();
        cecDAO.delCEC();
    }

    ////////////////////////////////////////////////////////////////////////////

    /////////////////////////////VERIFICAR DADOS////////////////////////////////

    public boolean verPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verPreCECAberto();
    }

    public boolean verPreCECFechado(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verPreCECFechado();
    }

    public boolean verDataPreCEC(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verDataPreCEC();
    }

    public boolean hasElemCEC(){
        CECDAO cecDAO = new CECDAO();
        return cecDAO.verCEC();
    }

    public void verCEC(Context telaAtual, Class telaProx, ProgressDialog progressDialog){

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setStatusRetVerif(1L);

        CECDAO cecDAO = new CECDAO();
        EquipDAO equipDAO = new EquipDAO();
        PreCECDAO preCECDAO = new PreCECDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();

        String dados = equipDAO.dadosEnvioEquip() + "_" + preCECDAO.dadosEnvioPreCEC() + "_" + atualAplicDAO.getAtualBDToken();
        cecDAO.verCEC(dados, telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////SET DADOS////////////////////////////////

    public void setDataChegCampo(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.setDataChegCampo();
    }

    public void setLib(Long libCam){
        PreCECDAO preCECDAO = new PreCECDAO();
        ConfigCTR configCTR = new ConfigCTR();
        CarretaDAO carretaDAO = new CarretaDAO();
        if(configCTR.getConfig().getCarretaConfig() > 0L){
            carretaDAO.insCarreta(configCTR.getConfig().getCarretaConfig());
        }
        preCECDAO.setLib(configCTR.getConfig().getCarretaConfig(), libCam, carretaDAO.getQtdeCarreta());
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////GET DADOS/////////////////////////////////

    public String getDataChegCampo(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getDataChegCampo();
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return osDAO.getOS(configCTR.getConfig().getNroOSConfig());
    }

    public boolean hasPreCEC(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.hasPreCEC();
    }

    public boolean verPreCECTerminadoList(){
        PreCECDAO preCECDAO = new PreCECDAO();
        List<PreCECBean> preCECTerminadoList = preCECDAO.preCECListTerminado();
        boolean verTerminado = preCECTerminadoList.size() > 0;
        preCECTerminadoList.clear();
        return verTerminado;
    }

    public List<PreCECBean> preCECTerminadoList(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.preCECListTerminado();
    }

    public CECBean getCEC(){
        CECDAO cecDAO = new CECDAO();
        return cecDAO.getCEC();
    }

    public List<CECBean> cecListDesc(){
        CECDAO cecDAO = new CECDAO();
        return cecDAO.cecListDesc();
    }

    public String getDataSaidaUlt(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getDataSaidaUlt();
    }

    /////////////////////////////////////////////////////////////////////////////

}
