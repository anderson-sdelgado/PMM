package br.com.usinasantafe.pmm.control;

import android.content.Context;

import br.com.usinasantafe.pmm.dao.InfoColheitaDAO;

public class ColheitaCTR {

    public ColheitaCTR() {
    }

    public void verInfoColheitaEquip(String dados,Context telaAtual, Class telaProx1, Class telaProx2){
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualVisDadosColhConfig(0L);
        InfoColheitaDAO infoColheitaDAO = new InfoColheitaDAO();
        infoColheitaDAO.verInfoColheitaEquip(dados ,telaAtual, telaProx1, telaProx2);
    }

}
