package br.com.usinasantafe.pmm.control;

import java.util.ArrayList;
import java.util.List;

public interface ApontInterface {

    public boolean verApontAberto();

    public Long getIdApontAberto();

    public List getListAllApont(Long idBol);

    public List getListApontEnvio(ArrayList<Long> idBolList);

    public List getListApontEnvio();

    public void updateApont(String retorno);

}
