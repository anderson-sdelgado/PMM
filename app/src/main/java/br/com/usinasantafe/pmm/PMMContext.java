package br.com.usinasantafe.pmm;

import android.app.Application;

import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ItemMedPneuTO;

/**
 * Created by anderson on 26/04/2017.
 */

public class PMMContext extends Application {

    private BoletimMMTO boletimMMTO;
    private BoletimFertTO boletimFertTO;
    private ApontaMMTO apontaMMTO;
    private ApontaFertTO apontaFertTO;
    private ItemMedPneuTO itemMedPneuTO;
    private int verPosTela;
    //1 - Inicio do boletim;
    // 2 - Trabalhando Moto Mec;
    // 3 - Parada Moto Mec;
    // 4 - Finalizar Boletim Moto Mec;
    // 6 - Trocar Transbordo;
    // 7 - Editar Rendimento;
    // 14 - Recolhimento de Mangueira;
    // 19 - Trocar de implemento
    private int contImplemento;
    private String textoHorimetro;
    public static String versaoAplic = "2";
    private int contRendimento;
    private int posRendimento;
    private int contRecolhimento;
    private int posRecolhimento;
    private String verAtualCL;
    private Long posChecklist;
    private int tipoEquip; //1 - Tipo Motomec; 2 - Tipo Fertirrigação

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
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

    public ApontaMMTO getApontaMMTO() {
        if (apontaMMTO == null)
            apontaMMTO = new ApontaMMTO();
        return apontaMMTO;
    }

    public ApontaFertTO getApontaFertTO() {
        if (apontaFertTO == null)
            apontaFertTO = new ApontaFertTO();
        return apontaFertTO;
    }

    public ItemMedPneuTO getItemMedPneuTO() {
        if (itemMedPneuTO == null)
            itemMedPneuTO = new ItemMedPneuTO();
        return itemMedPneuTO;
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

    public int getContRecolhimento() {
        return contRecolhimento;
    }

    public void setContRecolhimento(int contRecolhimento) {
        this.contRecolhimento = contRecolhimento;
    }

    public int getPosRecolhimento() {
        return posRecolhimento;
    }

    public void setPosRecolhimento(int posRecolhimento) {
        this.posRecolhimento = posRecolhimento;
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
}
