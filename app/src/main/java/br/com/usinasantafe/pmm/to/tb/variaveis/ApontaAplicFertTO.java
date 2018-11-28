package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 05/03/2018.
 */

@DatabaseTable(tableName="tbapontaaplicafertvar")
public class ApontaAplicFertTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApontaAplicFert;
    @DatabaseField
    private Long idBolApontaAplicFert;
    @DatabaseField
    private Long idExtBolApontaAplicFert;
    @DatabaseField
    private Long equipApontaAplicFert;
    @DatabaseField
    private Long osApontaAplicFert;
    @DatabaseField
    private Long ativApontaAplicFert;
    @DatabaseField
    private Long paradaApontaAplicFert;
    @DatabaseField
    private String dthrApontaAplicFert;
    @DatabaseField
    private Double pressaoApontaAplicFert;
    @DatabaseField
    private Long velocApontaAplicFert;
    @DatabaseField
    private Long bocalApontaAplicFert;
    @DatabaseField
    private Double raioApontaAplicFert;

    public ApontaAplicFertTO() {
    }

    public Long getIdApontaAplicFert() {
        return idApontaAplicFert;
    }

    public Long getIdBolApontaAplicFert() {
        return idBolApontaAplicFert;
    }

    public void setIdBolApontaAplicFert(Long idBolApontaAplicFert) {
        this.idBolApontaAplicFert = idBolApontaAplicFert;
    }

    public Long getIdExtBolApontaAplicFert() {
        return idExtBolApontaAplicFert;
    }

    public void setIdExtBolApontaAplicFert(Long idExtBolApontaAplicFert) {
        this.idExtBolApontaAplicFert = idExtBolApontaAplicFert;
    }

    public Long getOsApontaAplicFert() {
        return osApontaAplicFert;
    }

    public void setOsApontaAplicFert(Long osApontaAplicFert) {
        this.osApontaAplicFert = osApontaAplicFert;
    }

    public Long getAtivApontaAplicFert() {
        return ativApontaAplicFert;
    }

    public void setAtivApontaAplicFert(Long ativApontaAplicFert) {
        this.ativApontaAplicFert = ativApontaAplicFert;
    }

    public Long getParadaApontaAplicFert() {
        return paradaApontaAplicFert;
    }

    public void setParadaApontaAplicFert(Long paradaApontaAplicFert) {
        this.paradaApontaAplicFert = paradaApontaAplicFert;
    }

    public String getDthrApontaAplicFert() {
        return dthrApontaAplicFert;
    }

    public void setDthrApontaAplicFert(String dthrApontaAplicFert) {
        this.dthrApontaAplicFert = dthrApontaAplicFert;
    }

    public Double getPressaoApontaAplicFert() {
        return pressaoApontaAplicFert;
    }

    public void setPressaoApontaAplicFert(Double pressaoApontaAplicFert) {
        this.pressaoApontaAplicFert = pressaoApontaAplicFert;
    }

    public Long getVelocApontaAplicFert() {
        return velocApontaAplicFert;
    }

    public void setVelocApontaAplicFert(Long velocApontaAplicFert) {
        this.velocApontaAplicFert = velocApontaAplicFert;
    }

    public Long getBocalApontaAplicFert() {
        return bocalApontaAplicFert;
    }

    public void setBocalApontaAplicFert(Long bocalApontaAplicFert) {
        this.bocalApontaAplicFert = bocalApontaAplicFert;
    }

    public Double getRaioApontaAplicFert() {
        return raioApontaAplicFert;
    }

    public void setRaioApontaAplicFert(Double raioApontaAplicFert) {
        this.raioApontaAplicFert = raioApontaAplicFert;
    }

    public Long getEquipApontaAplicFert() {
        return equipApontaAplicFert;
    }

    public void setEquipApontaAplicFert(Long equipApontaAplicFert) {
        this.equipApontaAplicFert = equipApontaAplicFert;
    }
}
