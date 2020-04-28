package br.com.usinasantafe.pmm;

import android.app.Application;

import br.com.usinasantafe.pmm.control.ApontCTR;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.PneuCTR;
import br.com.usinasantafe.pmm.control.InformativoCTR;

/**
 * Created by anderson on 26/04/2017.
 */

public class PMMContext extends Application {

    private BoletimCTR boletimCTR;
    private ApontCTR apontCTR;
    private InformativoCTR informativoCTR;
    private PneuCTR pneuCTR;
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
    public static String versaoAplic = "2.04";
    private int contRend;
    private int posRend;
    private int contRecolh;
    private int posRecolh;
    private int contDataHora;
    private String verAtualCL;
    private int posCheckList;
    private int tipoMovComp;

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public BoletimCTR getBoletimCTR() {
        if (boletimCTR == null)
            boletimCTR = new BoletimCTR();
        return boletimCTR;
    }

    public ApontCTR getApontCTR(){
        if (apontCTR == null)
            apontCTR = new ApontCTR();
        return apontCTR;
    }

    public InformativoCTR getInformativoCTR(){
        if (informativoCTR == null)
            informativoCTR = new InformativoCTR();
        return informativoCTR;
    }

    public PneuCTR getPneuCTR(){
        if (pneuCTR == null)
            pneuCTR = new PneuCTR();
        return pneuCTR;
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

    public int getPosCheckList() {
        return posCheckList;
    }

    public void setPosCheckList(int posCheckList) {
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

    public int getTipoMovComp() {
        return tipoMovComp;
    }

    public void setTipoMovComp(int tipoMovComp) {
        this.tipoMovComp = tipoMovComp;
    }
}
