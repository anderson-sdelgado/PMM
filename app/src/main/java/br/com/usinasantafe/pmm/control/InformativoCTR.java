package br.com.usinasantafe.pmm.control;

import android.content.Context;

import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.model.dao.InformativoDAO;

public class InformativoCTR {

    public InformativoCTR() {
    }

    public InfColheitaBean getInfColheita(){
        InformativoDAO informativoDAO = new InformativoDAO();
        return informativoDAO.getInfColheita();
    }

    public InfPlantioBean getInfPlantio(){
        InformativoDAO informativoDAO = new InformativoDAO();
        return informativoDAO.getInfPlantio();
    }

    public void verInfor(String dados, Context telaAtual, Class telaProx){
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualVerInforConfig(0L);
        InformativoDAO informativoDAO = new InformativoDAO();
        informativoDAO.verInformativo(dados ,telaAtual, telaProx);
    }

    public void recInfor(String retorno){
        InformativoDAO informativoDAO = new InformativoDAO();
        informativoDAO.recInfor(retorno);
    }

}
