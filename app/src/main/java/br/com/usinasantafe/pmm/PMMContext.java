package br.com.usinasantafe.pmm;

import android.app.Application;

import java.util.ArrayList;

import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaAplicFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RespItemCheckListTO;

/**
 * Created by anderson on 26/04/2017.
 */

public class PMMContext extends Application {

    private BoletimMMTO boletimMMTO;
    private ApontaMMTO apontaMMTO;
    private ApontaAplicFertTO apontaAplicFertTO;
    private int verPosTela;
    //1 - Inicio do boletim;
    // 2 - Trabalhando Moto Mec;
    // 3 - Parada Moto Mec;
    // 4 - Finalisar Boletim Moto Mec;
    // 5 - Trabalhando Fert;
    // 6 - Trocar Transbordo;
    // 7 - Editar Rendimento;
    // 8 - Apontar Tralhando Motobomba e Canhão de Fertirrigação;
    // 9 - Apontar Tralhando Carretel e Rolão de Fertirrigação;
    // 10 - Finalizar Turno Fert;
    // 11 - Parada Fert;
    // 12 - Apontar Parada Motobomba e Canhão de Fertirrigação;
    // 13 - Apontar Carretel e Rolão de Fertirrigação;
    // 14 - Recolhimento de Mangueira;
    // 15 - Trabalhando Rolão;
    // 16 - Parada Rolão;
    // 17 - Acoplar Carretel Trabalhando
    // 18 - Acoplar Carretel Parado
    // 19 - Trocar de implemento
    private int contImplemento;
    private String textoHorimetro;
    public static String versaoAplic = "1.23";
    private int contRendimento;
    private int posRendimento;
    private int contRecolMangFert;
    private int posRecolMangFert;
    private String verAtualCL;
    private Long posChecklist;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public ApontaMMTO getApontaMMTO() {
        if (apontaMMTO == null)
            apontaMMTO = new ApontaMMTO();
        return apontaMMTO;
    }

    public BoletimMMTO getBoletimMMTO() {
        if (boletimMMTO == null)
            boletimMMTO = new BoletimMMTO();
        return boletimMMTO;
    }

    public ApontaAplicFertTO getApontaAplicFertTO() {
        if (apontaAplicFertTO == null)
            apontaAplicFertTO = new ApontaAplicFertTO();
        return apontaAplicFertTO;
    }

    public void setBoletimMMTO(BoletimMMTO boletimMMTO){
        this.boletimMMTO = boletimMMTO;
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

    public Long getPosChecklist() {
        return posChecklist;
    }

    public void setPosChecklist(Long posChecklist) {
        this.posChecklist = posChecklist;
    }

    public String getTextoHorimetro() {
        return textoHorimetro;
    }

    public void setTextoHorimetro(String textoHorimetro) {
        this.textoHorimetro = textoHorimetro;
    }

    public int getContRendimento() {
        return contRendimento;
    }

    public void setContRendimento(int contRendimento) {
        this.contRendimento = contRendimento;
    }

    public int getPosRendimento() {
        return posRendimento;
    }

    public void setPosRendimento(int posRendimento) {
        this.posRendimento = posRendimento;
    }

    public int getContRecolMangFert() {
        return contRecolMangFert;
    }

    public void setContRecolMangFert(int contRecolMangFert) {
        this.contRecolMangFert = contRecolMangFert;
    }

    public int getPosRecolMangFert() {
        return posRecolMangFert;
    }

    public void setPosRecolMangFert(int posRecolMangFert) {
        this.posRecolMangFert = posRecolMangFert;
    }

    public String getVerAtualCL() {
        return verAtualCL;
    }

    public void setVerAtualCL(String verAtualCL) {
        this.verAtualCL = verAtualCL;
    }
}
