package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbprodplantvar")
public class GrafProdPlantioTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idGrafProdFrente;
    @DatabaseField
    private Double prodFrenteMetaDia;
    @DatabaseField
    private Double prodFrenteRealDia;
    @DatabaseField
    private Double prodFrenteMetaMes;
    @DatabaseField
    private Double prodFrenteRealMes;
    @DatabaseField
    private Double prodFrenteMetaAno;
    @DatabaseField
    private Double prodFrenteRealAno;
    @DatabaseField
    private Double mediaPlantMetaDia;
    @DatabaseField
    private Double mediaPlantRealDia;
    @DatabaseField
    private Double mediaPlantMetaMes;
    @DatabaseField
    private Double mediaPlantRealMes;
    @DatabaseField
    private Double mediaPlantMetaAno;
    @DatabaseField
    private Double mediaPlantRealAno;

    public GrafProdPlantioTO() {
    }

    public Long getIdGrafProdFrente() {
        return idGrafProdFrente;
    }

    public void setIdGrafProdFrente(Long idGrafProdFrente) {
        this.idGrafProdFrente = idGrafProdFrente;
    }

    public Double getProdFrenteMetaDia() {
        return prodFrenteMetaDia;
    }

    public void setProdFrenteMetaDia(Double prodFrenteMetaDia) {
        this.prodFrenteMetaDia = prodFrenteMetaDia;
    }

    public Double getProdFrenteRealDia() {
        return prodFrenteRealDia;
    }

    public void setProdFrenteRealDia(Double prodFrenteRealDia) {
        this.prodFrenteRealDia = prodFrenteRealDia;
    }

    public Double getProdFrenteMetaMes() {
        return prodFrenteMetaMes;
    }

    public void setProdFrenteMetaMes(Double prodFrenteMetaMes) {
        this.prodFrenteMetaMes = prodFrenteMetaMes;
    }

    public Double getProdFrenteRealMes() {
        return prodFrenteRealMes;
    }

    public void setProdFrenteRealMes(Double prodFrenteRealMes) {
        this.prodFrenteRealMes = prodFrenteRealMes;
    }

    public Double getProdFrenteMetaAno() {
        return prodFrenteMetaAno;
    }

    public void setProdFrenteMetaAno(Double prodFrenteMetaAno) {
        this.prodFrenteMetaAno = prodFrenteMetaAno;
    }

    public Double getProdFrenteRealAno() {
        return prodFrenteRealAno;
    }

    public void setProdFrenteRealAno(Double prodFrenteRealAno) {
        this.prodFrenteRealAno = prodFrenteRealAno;
    }

    public Double getMediaPlantMetaDia() {
        return mediaPlantMetaDia;
    }

    public void setMediaPlantMetaDia(Double mediaPlantMetaDia) {
        this.mediaPlantMetaDia = mediaPlantMetaDia;
    }

    public Double getMediaPlantRealDia() {
        return mediaPlantRealDia;
    }

    public void setMediaPlantRealDia(Double mediaPlantRealDia) {
        this.mediaPlantRealDia = mediaPlantRealDia;
    }

    public Double getMediaPlantMetaMes() {
        return mediaPlantMetaMes;
    }

    public void setMediaPlantMetaMes(Double mediaPlantMetaMes) {
        this.mediaPlantMetaMes = mediaPlantMetaMes;
    }

    public Double getMediaPlantRealMes() {
        return mediaPlantRealMes;
    }

    public void setMediaPlantRealMes(Double mediaPlantRealMes) {
        this.mediaPlantRealMes = mediaPlantRealMes;
    }

    public Double getMediaPlantMetaAno() {
        return mediaPlantMetaAno;
    }

    public void setMediaPlantMetaAno(Double mediaPlantMetaAno) {
        this.mediaPlantMetaAno = mediaPlantMetaAno;
    }

    public Double getMediaPlantRealAno() {
        return mediaPlantRealAno;
    }

    public void setMediaPlantRealAno(Double mediaPlantRealAno) {
        this.mediaPlantRealAno = mediaPlantRealAno;
    }

}
