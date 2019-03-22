package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbqualplantvar")
public class GrafQualPlantioTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idGrafQual;
    @DatabaseField
    private String dtGrafQual;
    @DatabaseField
    private Long valorCalibAdubDia;
    @DatabaseField
    private Long valorCalibAdubMes;
    @DatabaseField
    private Long valorCalibAdubAno;
    @DatabaseField
    private Long valorCalibInsetDia;
    @DatabaseField
    private Long valorCalibInsetMes;
    @DatabaseField
    private Long valorCalibInsetAno;
    @DatabaseField
    private Long valorGemasDia;
    @DatabaseField
    private Long valorGemasMes;
    @DatabaseField
    private Long valorGemasAno;
    @DatabaseField
    private Long valorAltCobrDia;
    @DatabaseField
    private Long valorAltCobrMes;
    @DatabaseField
    private Long valorAltCobrAno;
    @DatabaseField
    private Long valorProfSulcDia;
    @DatabaseField
    private Long valorProfSulcMes;
    @DatabaseField
    private Long valorProfSulcAno;
    @DatabaseField
    private Long valorFalhasDia;
    @DatabaseField
    private Long valorFalhasMes;
    @DatabaseField
    private Long valorFalhasAno;

    public GrafQualPlantioTO() {
    }

    public Long getIdGrafQual() {
        return idGrafQual;
    }

    public void setIdGrafQual(Long idGrafQual) {
        this.idGrafQual = idGrafQual;
    }

    public String getDtGrafQual() {
        return dtGrafQual;
    }

    public void setDtGrafQual(String dtGrafQual) {
        this.dtGrafQual = dtGrafQual;
    }

    public Long getValorCalibAdubDia() {
        return valorCalibAdubDia;
    }

    public void setValorCalibAdubDia(Long valorCalibAdubDia) {
        this.valorCalibAdubDia = valorCalibAdubDia;
    }

    public Long getValorCalibAdubMes() {
        return valorCalibAdubMes;
    }

    public void setValorCalibAdubMes(Long valorCalibAdubMes) {
        this.valorCalibAdubMes = valorCalibAdubMes;
    }

    public Long getValorCalibAdubAno() {
        return valorCalibAdubAno;
    }

    public void setValorCalibAdubAno(Long valorCalibAdubAno) {
        this.valorCalibAdubAno = valorCalibAdubAno;
    }

    public Long getValorCalibInsetDia() {
        return valorCalibInsetDia;
    }

    public void setValorCalibInsetDia(Long valorCalibInsetDia) {
        this.valorCalibInsetDia = valorCalibInsetDia;
    }

    public Long getValorCalibInsetMes() {
        return valorCalibInsetMes;
    }

    public void setValorCalibInsetMes(Long valorCalibInsetMes) {
        this.valorCalibInsetMes = valorCalibInsetMes;
    }

    public Long getValorCalibInsetAno() {
        return valorCalibInsetAno;
    }

    public void setValorCalibInsetAno(Long valorCalibInsetAno) {
        this.valorCalibInsetAno = valorCalibInsetAno;
    }

    public Long getValorGemasDia() {
        return valorGemasDia;
    }

    public void setValorGemasDia(Long valorGemasDia) {
        this.valorGemasDia = valorGemasDia;
    }

    public Long getValorGemasMes() {
        return valorGemasMes;
    }

    public void setValorGemasMes(Long valorGemasMes) {
        this.valorGemasMes = valorGemasMes;
    }

    public Long getValorGemasAno() {
        return valorGemasAno;
    }

    public void setValorGemasAno(Long valorGemasAno) {
        this.valorGemasAno = valorGemasAno;
    }

    public Long getValorAltCobrDia() {
        return valorAltCobrDia;
    }

    public void setValorAltCobrDia(Long valorAltCobrDia) {
        this.valorAltCobrDia = valorAltCobrDia;
    }

    public Long getValorAltCobrMes() {
        return valorAltCobrMes;
    }

    public void setValorAltCobrMes(Long valorAltCobrMes) {
        this.valorAltCobrMes = valorAltCobrMes;
    }

    public Long getValorAltCobrAno() {
        return valorAltCobrAno;
    }

    public void setValorAltCobrAno(Long valorAltCobrAno) {
        this.valorAltCobrAno = valorAltCobrAno;
    }

    public Long getValorProfSulcDia() {
        return valorProfSulcDia;
    }

    public void setValorProfSulcDia(Long valorProfSulcDia) {
        this.valorProfSulcDia = valorProfSulcDia;
    }

    public Long getValorProfSulcMes() {
        return valorProfSulcMes;
    }

    public void setValorProfSulcMes(Long valorProfSulcMes) {
        this.valorProfSulcMes = valorProfSulcMes;
    }

    public Long getValorProfSulcAno() {
        return valorProfSulcAno;
    }

    public void setValorProfSulcAno(Long valorProfSulcAno) {
        this.valorProfSulcAno = valorProfSulcAno;
    }

    public Long getValorFalhasDia() {
        return valorFalhasDia;
    }

    public void setValorFalhasDia(Long valorFalhasDia) {
        this.valorFalhasDia = valorFalhasDia;
    }

    public Long getValorFalhasMes() {
        return valorFalhasMes;
    }

    public void setValorFalhasMes(Long valorFalhasMes) {
        this.valorFalhasMes = valorFalhasMes;
    }

    public Long getValorFalhasAno() {
        return valorFalhasAno;
    }

    public void setValorFalhasAno(Long valorFalhasAno) {
        this.valorFalhasAno = valorFalhasAno;
    }
}
