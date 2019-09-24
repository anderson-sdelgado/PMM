package br.com.usinasantafe.pmm.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 05/03/2018.
 */

@DatabaseTable(tableName="tbapontfertvar")
public class ApontFertTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApontFert;
    @DatabaseField
    private Long idBolApontFert;
    @DatabaseField
    private Long idExtBolApontFert;
    @DatabaseField
    private Long osApontFert;
    @DatabaseField
    private Long ativApontFert;
    @DatabaseField
    private Long paradaApontFert;
    @DatabaseField
    private String dthrApontFert;
    @DatabaseField
    private Double pressaoApontFert;
    @DatabaseField
    private Long velocApontFert;
    @DatabaseField
    private Long bocalApontFert;
    @DatabaseField
    private Double latitudeApontFert;
    @DatabaseField
    private Double longitudeApontFert;
    @DatabaseField
    private Long statusConApontFert;  //0 - OffLine; 1 - OnLine
    @DatabaseField
    private Long statusApontFert;  //1 - Enviar; 2 - Enviado

    public ApontFertTO() {
    }

    public Long getIdApontFert() {
        return idApontFert;
    }

    public Long getIdBolApontFert() {
        return idBolApontFert;
    }

    public void setIdBolApontFert(Long idBolApontFert) {
        this.idBolApontFert = idBolApontFert;
    }

    public Long getIdExtBolApontFert() {
        return idExtBolApontFert;
    }

    public void setIdExtBolApontFert(Long idExtBolApontFert) {
        this.idExtBolApontFert = idExtBolApontFert;
    }

    public Long getOsApontFert() {
        return osApontFert;
    }

    public void setOsApontFert(Long osApontFert) {
        this.osApontFert = osApontFert;
    }

    public Long getAtivApontFert() {
        return ativApontFert;
    }

    public void setAtivApontFert(Long ativApontFert) {
        this.ativApontFert = ativApontFert;
    }

    public Long getParadaApontFert() {
        return paradaApontFert;
    }

    public void setParadaApontFert(Long paradaApontFert) {
        this.paradaApontFert = paradaApontFert;
    }

    public String getDthrApontFert() {
        return dthrApontFert;
    }

    public void setDthrApontFert(String dthrApontFert) {
        this.dthrApontFert = dthrApontFert;
    }

    public Double getPressaoApontFert() {
        return pressaoApontFert;
    }

    public void setPressaoApontFert(Double pressaoApontFert) {
        this.pressaoApontFert = pressaoApontFert;
    }

    public Long getVelocApontFert() {
        return velocApontFert;
    }

    public void setVelocApontFert(Long velocApontFert) {
        this.velocApontFert = velocApontFert;
    }

    public Long getBocalApontFert() {
        return bocalApontFert;
    }

    public void setBocalApontFert(Long bocalApontFert) {
        this.bocalApontFert = bocalApontFert;
    }

    public Long getStatusConApontFert() {
        return statusConApontFert;
    }

    public void setStatusConApontFert(Long statusConApontFert) {
        this.statusConApontFert = statusConApontFert;
    }

    public Double getLatitudeApontFert() {
        return latitudeApontFert;
    }

    public void setLatitudeApontFert(Double latitudeApontFert) {
        this.latitudeApontFert = latitudeApontFert;
    }

    public Double getLongitudeApontFert() {
        return longitudeApontFert;
    }

    public void setLongitudeApontFert(Double longitudeApontFert) {
        this.longitudeApontFert = longitudeApontFert;
    }

    public Long getStatusApontFert() {
        return statusApontFert;
    }

    public void setStatusApontFert(Long statusApontFert) {
        this.statusApontFert = statusApontFert;
    }
}
