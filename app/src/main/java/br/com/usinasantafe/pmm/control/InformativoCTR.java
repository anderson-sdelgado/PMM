package br.com.usinasantafe.pmm.control;

import android.content.Context;

import br.com.usinasantafe.pmm.model.dao.InformativoDAO;

public class InformativoCTR {

    public InformativoCTR() {
    }

    public void verInfor(String dados, Context telaAtual, Class telaProx){
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualVerInforConfig(0L);
        InformativoDAO informativoDAO = new InformativoDAO();
        informativoDAO.verInformativo(dados ,telaAtual, telaProx);
    }

}
