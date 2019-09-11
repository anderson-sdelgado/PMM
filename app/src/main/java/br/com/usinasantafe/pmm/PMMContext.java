package br.com.usinasantafe.pmm;

import android.app.Application;

import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;

/**
 * Created by anderson on 26/04/2017.
 */

public class PMMContext extends Application {

    private BoletimMMTO boletimMMTO;
    private BoletimFertTO boletimFertTO;
    private ApontMMTO apontMMTO;
    private ApontFertTO apontFertTO;
    private int verPosTela;
    //1 - Inicio do boletim;
    // 2 - Trabalhando Moto Mec;
    // 3 - Parada Moto Mec;
    // 4 - Finalizar Boletim Moto Mec;
    // 5 - Digitar Data e Hora
    // 6 - Trocar Transbordo;
    // 7 - Editar Rendimento;
    // 14 - Recolhimento de Mangueira;
    // 19 - Trocar de implemento
    private int contImplemento;
    private String textoHorimetro;
    public static String versaoAplic = "2.01";
    private int contRend;
    private int posRend;
    private int contRecolh;
    private int posRecolh;
    private int contDataHora;
    private String verAtualCL;
    private Long posCheckList;
    private int tipoEquip; //1 - Tipo Motomec; 2 - Tipo Fertirrigação

    private boolean verVisDados;

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public BoletimMMTO getBoletimMMTO() {
        if (boletimMMTO == null)
            boletimMMTO = new BoletimMMTO();
        return boletimMMTO;
    }

    public BoletimFertTO getBoletimFertTO() {
        if (boletimFertTO == null)
            boletimFertTO = new BoletimFertTO();
        return boletimFertTO;
    }

    public ApontMMTO getApontMMTO() {
        if (apontMMTO == null)
            apontMMTO = new ApontMMTO();
        return apontMMTO;
    }

    public ApontFertTO getApontFertTO() {
        if (apontFertTO == null)
            apontFertTO = new ApontFertTO();
        return apontFertTO;
    }


    public void setBoletimMMTO(BoletimMMTO boletimMMTO){
        this.boletimMMTO = boletimMMTO;
    }

    public void setBoletimFertTO(BoletimFertTO boletimFertTO) {
        this.boletimFertTO = boletimFertTO;
    }

    public int getVerPosTela() {
        return verPosTela;
    }

    public void setVerPosTela(int verPosTela) {
        this.verPosTela = verPosTela;
    }

    public int getContImplemento() {
        return contImplemento;
    }

    public void setContImplemento(int contImplemento) {
        this.contImplemento = contImplemento;
    }

    public Long getPosCheckList() {
        return posCheckList;
    }

    public void setPosCheckList(Long posCheckList) {
        this.posCheckList = posCheckList;
    }

    public String getTextoHorimetro() {
        return textoHorimetro;
    }

    public void setTextoHorimetro(String textoHorimetro) {
        this.textoHorimetro = textoHorimetro;
    }

    public int getContRend() {
        return contRend;
    }

    public void setContRend(int contRend) {
        this.contRend = contRend;
    }

    public int getPosRend() {
        return posRend;
    }

    public void setPosRend(int posRend) {
        this.posRend = posRend;
    }

    public int getContRecolh() {
        return contRecolh;
    }

    public void setContRecolh(int contRecolh) {
        this.contRecolh = contRecolh;
    }

    public int getPosRecolh() {
        return posRecolh;
    }

    public void setPosRecolh(int posRecolh) {
        this.posRecolh = posRecolh;
    }

    public String getVerAtualCL() {
        return verAtualCL;
    }

    public void setVerAtualCL(String verAtualCL) {
        this.verAtualCL = verAtualCL;
    }

    public int getTipoEquip() {
        return tipoEquip;
    }

    public void setTipoEquip(int tipoEquip) {
        this.tipoEquip = tipoEquip;
    }

    public int getContDataHora() {
        return contDataHora;
    }

    public void setContDataHora(int contDataHora) {
        this.contDataHora = contDataHora;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public boolean isVerVisDados() {
        return verVisDados;
    }

    public void setVerVisDados(boolean verVisDados) {
        this.verVisDados = verVisDados;
    }
}
