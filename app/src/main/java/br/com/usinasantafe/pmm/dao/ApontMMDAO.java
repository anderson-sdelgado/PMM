package br.com.usinasantafe.pmm.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.estaticas.ImpleMMTO;

public class ApontMMDAO {

    public ApontMMDAO() {
    }

    public void salvarApont(ApontMMTO apontMMTO, BoletimMMTO boletimMMTO){

        apontMMTO.setLatitudeApontMM(0D);
        apontMMTO.setLongitudeApontMM(0D);

        String datahora = Tempo.getInstance().dataComHora();
        apontMMTO.setDthrApontMM(datahora);

        apontMMTO.setIdBolApontMM(boletimMMTO.getIdBolMM());
        apontMMTO.setIdExtBolApontMM(boletimMMTO.getIdExtBolMM());
        apontMMTO.setStatusApontMM(1L);
        apontMMTO.insert();

        ImpleMMTO impleMMTO = new ImpleMMTO();
        List impleList = impleMMTO.get("idApontImpleMM", 0L);

        List apontaList = apontMMTO.get("dthrApontMM", datahora);
        apontMMTO = (ApontMMTO) apontaList.get(0);

        for (int i = 0; i < impleList.size(); i++) {
            impleMMTO = (ImpleMMTO) impleList.get(i);
            ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
            apontImpleMMTO.setIdApontImpleMM(apontMMTO.getIdApontMM());
            apontImpleMMTO.setCodEquipImpleMM(impleMMTO.getCodEquipImpleMM());
            apontImpleMMTO.setPosImpleMM(impleMMTO.getPosImpleMM());
            apontImpleMMTO.setDthrImpleMM(datahora);
            apontImpleMMTO.insert();
        }

    }

    public List apontList(Long idBolMM){
        ApontMMTO apontMMTO = new ApontMMTO();
        return apontMMTO.getAndOrderBy("idBolApontMM", idBolMM, "idApontMM", true);
    }

    public int verTransbordo(String data) {

        int retorno = 0;

        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        List apontMMList = apontList(boletimMMDAO.getIdBolMMAberto());

        if (apontMMList.size() > 0) {
            ApontMMTO apontaMMTO = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);
            if (apontaMMTO.getParadaApontMM() != 0L) {
                retorno = 1;
            }
        }

        if (data.isEmpty()) {
            retorno = 1;
        }

        if (retorno == 0) {

            String dtStr = data;

            String diaStr = dtStr.substring(0, 2);
            String mesStr = dtStr.substring(3, 5);
            String anoStr = dtStr.substring(6, 10);
            String horaStr = dtStr.substring(11, 13);
            String minutoStr = dtStr.substring(14, 16);

            Calendar calBase = Calendar.getInstance();
            calBase.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            calBase.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            calBase.set(Calendar.YEAR, Integer.parseInt(anoStr));
            calBase.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            calBase.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            calBase.add(Calendar.MINUTE, +9);

            dtStr = Tempo.getInstance().dataComHora();

            diaStr = dtStr.substring(0, 2);
            mesStr = dtStr.substring(3, 5);
            anoStr = dtStr.substring(6, 10);
            horaStr = dtStr.substring(11, 13);
            minutoStr = dtStr.substring(14, 16);

            Calendar calTimer = Calendar.getInstance();
            calTimer.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            calTimer.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            calTimer.set(Calendar.YEAR, Integer.parseInt(anoStr));
            calTimer.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            calTimer.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            if (calBase.after(calTimer)) {
                retorno = 2;
            }

        }
        return retorno;
    }


    public void salvarParadaImple(BoletimMMTO boletimMMTO){

        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        List apontMMList = apontList(boletimMMDAO.getIdBolMMAberto());
        ApontMMTO apontMMTOBD = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);

        AtividadeDAO atividadeDAO = new AtividadeDAO();

        ApontMMTO apontMMTO = new ApontMMTO();
        apontMMTO.setOsApontMM(apontMMTOBD.getOsApontMM());
        apontMMTO.setAtivApontMM(apontMMTOBD.getAtivApontMM());
        apontMMTO.setParadaApontMM(atividadeDAO.idParadaImplemento());
        apontMMTO.setTransbApontMM(apontMMTOBD.getTransbApontMM());
        salvarApont(apontMMTO, boletimMMTO);

    }

    public List apontAbertoList(Long idBolMM){

        ApontMMTO apontMMTO = new ApontMMTO();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idBolApontMM");
        pesquisa2.setValor(idBolMM);
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        return apontMMTO.get(pesqArrayList);

    }

    public List apontAbertoMMList() {
        ApontMMTO apontMMTO = new ApontMMTO();
        return apontMMTO.get("statusApontMM", 1L);
    }

}
