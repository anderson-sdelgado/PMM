package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbqualplantvar")
public class GrafQualPlantioTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idGrafQual;
    @DatabaseField
    private Double valorCalibAdub;
    @DatabaseField
    private Double valorCalibInset;
    @DatabaseField
    private Double valorGemas;
    @DatabaseField
    private Double valorAltCobr;
    @DatabaseField
    private Double valorProfSulc;
    @DatabaseField
    private Double valorFalhas;
    @DatabaseField
    private Double valorQual;

    public GrafQualPlantioTO() {
    }

    public Long getIdGrafQual() {
        return idGrafQual;
    }

    public void setIdGrafQual(Long idGrafQual) {
        this.idGrafQual = idGrafQual;
    }

    public Double getValorCalibAdub() {
        return valorCalibAdub;
    }

    public void setValorCalibAdub(Double valorCalibAdub) {
        this.valorCalibAdub = valorCalibAdub;
    }

    public Double getValorCalibInset() {
        return valorCalibInset;
    }

    public void setValorCalibInset(Double valorCalibInset) {
        this.valorCalibInset = valorCalibInset;
    }

    public Double getValorGemas() {
        return valorGemas;
    }

    public void setValorGemas(Double valorGemas) {
        this.valorGemas = valorGemas;
    }

    public Double getValorAltCobr() {
        return valorAltCobr;
    }

    public void setValorAltCobr(Double valorAltCobr) {
        this.valorAltCobr = valorAltCobr;
    }

    public Double getValorProfSulc() {
        return valorProfSulc;
    }

    public void setValorProfSulc(Double valorProfSulc) {
        this.valorProfSulc = valorProfSulc;
    }

    public Double getValorFalhas() {
        return valorFalhas;
    }

    public void setValorFalhas(Double valorFalhas) {
        this.valorFalhas = valorFalhas;
    }

    public Double getValorQual() {
        return valorQual;
    }

    public void setValorQual(Double valorQual) {
        this.valorQual = valorQual;
    }
}
