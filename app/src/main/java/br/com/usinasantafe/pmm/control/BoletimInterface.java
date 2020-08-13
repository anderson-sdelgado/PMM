package br.com.usinasantafe.pmm.control;

import java.util.List;

public interface BoletimInterface {

    public boolean verBolAberto();

    public Long getIdBolAberto();

    public void atualQtdeApontBol();

    public List boletimFechadoList();

    public String dadosEnvioBolFechado();

    public void updateBolAberto(String retorno);

    public void deleteBolFechado(String retorno);

}
