package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbapontmmfertvar")
public class ApontMMFertBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApontMMFert;
    @DatabaseField
    private Long idBolMMFert;
    @DatabaseField
    private Long osApontMMFert;
    @DatabaseField
    private Long ativApontMMFert;
    @DatabaseField
    private Long paradaApontMMFert;
    @DatabaseField
    private Long transbApontMMFert;
    @DatabaseField
    private Double pressaoApontMMFert;
    @DatabaseField
    private Long velocApontMMFert;
    @DatabaseField
    private Long bocalApontMMFert;
    @DatabaseField
    private String dthrApontMMFert;
    @DatabaseField
    private Double latitudeApontMMFert;
    @DatabaseField
    private Double longitudeApontMMFert;
    @DatabaseField
    private Long statusConApontMMFert;  //0 - OffLine; 1 - OnLine
    @DatabaseField
    private Long statusApontMMFert;  //1 - Enviar; 2 - Enviado

    public ApontMMFertBean() {
    }

    public Long getIdApontMMFert() {
        return idApontMMFert;
    }

    public void setIdApontMMFert(Long idApontMMFert) {
        this.idApontMMFert = idApontMMFert;
    }

    public Long getIdBolMMFert() {
        return idBolMMFert;
    }

    public void setIdBolMMFert(Long idBolMMFert) {
        this.idBolMMFert = idBolMMFert;
    }

    public Long getOsApontMMFert() {
        return osApontMMFert;
    }

    public void setOsApontMMFert(Long osApontMMFert) {
        this.osApontMMFert = osApontMMFert;
    }

    public Long getAtivApontMMFert() {
        return ativApontMMFert;
    }

    public void setAtivApontMMFert(Long ativApontMMFert) {
        this.ativApontMMFert = ativApontMMFert;
    }

    public Long getParadaApontMMFert() {
        return paradaApontMMFert;
    }

    public void setParadaApontMMFert(Long paradaApontMMFert) {
        this.paradaApontMMFert = paradaApontMMFert;
    }

    public Long getTransbApontMMFert() {
        return transbApontMMFert;
    }

    public void setTransbApontMMFert(Long transbApontMMFert) {
        this.transbApontMMFert = transbApontMMFert;
    }

    public Double getPressaoApontMMFert() {
        return pressaoApontMMFert;
    }

    public void setPressaoApontMMFert(Double pressaoApontMMFert) {
        this.pressaoApontMMFert = pressaoApontMMFert;
    }

    public Long getVelocApontMMFert() {
        return velocApontMMFert;
    }

    public void setVelocApontMMFert(Long velocApontMMFert) {
        this.velocApontMMFert = velocApontMMFert;
    }

    public Long getBocalApontMMFert() {
        return bocalApontMMFert;
    }

    public void setBocalApontMMFert(Long bocalApontMMFert) {
        this.bocalApontMMFert = bocalApontMMFert;
    }

    public String getDthrApontMMFert() {
        return dthrApontMMFert;
    }

    public void setDthrApontMMFert(String dthrApontMMFert) {
        this.dthrApontMMFert = dthrApontMMFert;
    }

    public Double getLatitudeApontMMFert() {
        return latitudeApontMMFert;
    }

    public void setLatitudeApontMMFert(Double latitudeApontMMFert) {
        this.latitudeApontMMFert = latitudeApontMMFert;
    }

    public Double getLongitudeApontMMFert() {
        return longitudeApontMMFert;
    }

    public void setLongitudeApontMMFert(Double longitudeApontMMFert) {
        this.longitudeApontMMFert = longitudeApontMMFert;
    }

    public Long getStatusConApontMMFert() {
        return statusConApontMMFert;
    }

    public void setStatusConApontMMFert(Long statusConApontMMFert) {
        this.statusConApontMMFert = statusConApontMMFert;
    }

    public Long getStatusApontMMFert() {
        return statusApontMMFert;
    }

    public void setStatusApontMMFert(Long statusApontMMFert) {
        this.statusApontMMFert = statusApontMMFert;
    }
}
