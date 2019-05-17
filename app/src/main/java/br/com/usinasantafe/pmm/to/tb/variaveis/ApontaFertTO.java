package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 05/03/2018.
 */

@DatabaseTable(tableName="tbapontafertvar")
public class ApontaFertTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApontaFert;
    @DatabaseField
    private Long idBolApontaFert;
    @DatabaseField
    private Long idExtBolApontaFert;
    @DatabaseField
    private Long osApontaFert;
    @DatabaseField
    private Long ativApontaFert;
    @DatabaseField
    private Long paradaApontaFert;
    @DatabaseField
    private String dthrApontaFert;
    @DatabaseField
    private Double pressaoApontaFert;
    @DatabaseField
    private Long velocApontaFert;
    @DatabaseField
    private Long bocalApontaFert;
    @DatabaseField
    private Double latitudeApontaFert;
    @DatabaseField
    private Double longitudeApontaFert;
    @DatabaseField
    private Long statusConApontaFert;  //0 - OffLine; 1 - OnLine

    public ApontaFertTO() {
    }

    public Long getIdApontaFert() {
        return idApontaFert;
    }

    public Long getIdBolApontaFert() {
        return idBolApontaFert;
    }

    public void setIdBolApontaFert(Long idBolApontaFert) {
        this.idBolApontaFert = idBolApontaFert;
    }

    public Long getIdExtBolApontaFert() {
        return idExtBolApontaFert;
    }

    public void setIdExtBolApontaFert(Long idExtBolApontaFert) {
        this.idExtBolApontaFert = idExtBolApontaFert;
    }

    public Long getOsApontaFert() {
        return osApontaFert;
    }

    public void setOsApontaFert(Long osApontaFert) {
        this.osApontaFert = osApontaFert;
    }

    public Long getAtivApontaFert() {
        return ativApontaFert;
    }

    public void setAtivApontaFert(Long ativApontaFert) {
        this.ativApontaFert = ativApontaFert;
    }

    public Long getParadaApontaFert() {
        return paradaApontaFert;
    }

    public void setParadaApontaFert(Long paradaApontaFert) {
        this.paradaApontaFert = paradaApontaFert;
    }

    public String getDthrApontaFert() {
        return dthrApontaFert;
    }

    public void setDthrApontaFert(String dthrApontaFert) {
        this.dthrApontaFert = dthrApontaFert;
    }

    public Double getPressaoApontaFert() {
        return pressaoApontaFert;
    }

    public void setPressaoApontaFert(Double pressaoApontaFert) {
        this.pressaoApontaFert = pressaoApontaFert;
    }

    public Long getVelocApontaFert() {
        return velocApontaFert;
    }

    public void setVelocApontaFert(Long velocApontaFert) {
        this.velocApontaFert = velocApontaFert;
    }

    public Long getBocalApontaFert() {
        return bocalApontaFert;
    }

    public void setBocalApontaFert(Long bocalApontaFert) {
        this.bocalApontaFert = bocalApontaFert;
    }

    public Long getStatusConApontaFert() {
        return statusConApontaFert;
    }

    public void setStatusConApontaFert(Long statusConApontaFert) {
        this.statusConApontaFert = statusConApontaFert;
    }

    public Double getLatitudeApontaFert() {
        return latitudeApontaFert;
    }

    public void setLatitudeApontaFert(Double latitudeApontaFert) {
        this.latitudeApontaFert = latitudeApontaFert;
    }

    public Double getLongitudeApontaFert() {
        return longitudeApontaFert;
    }

    public void setLongitudeApontaFert(Double longitudeApontaFert) {
        this.longitudeApontaFert = longitudeApontaFert;
    }
}
