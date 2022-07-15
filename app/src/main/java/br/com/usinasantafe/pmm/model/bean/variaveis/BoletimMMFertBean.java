package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbboletimmmfertvar")
public class BoletimMMFertBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idBolMMFert;
    @DatabaseField
    private Long idExtBolMMFert;
    @DatabaseField
    private Long matricFuncBolMMFert;
    @DatabaseField
    private Long idEquipBolMMFert;
    @DatabaseField
    private Long idEquipBombaBolMMFert;
    @DatabaseField
    private Long idTurnoBolMMFert;
    @DatabaseField
    private Double hodometroInicialBolMMFert;
    @DatabaseField
    private Double hodometroFinalBolMMFert;
    @DatabaseField
    private Long osBolMMFert;
    @DatabaseField
    private Long ativPrincBolMMFert;
    @DatabaseField
    private String dthrInicialBolMMFert;
    @DatabaseField
    private String dthrFinalBolMMFert;
    @DatabaseField
    private Long statusBolMMFert;  //0 - Esta apontando os implementos; 1 - Aberto; 2 - Encerrado; 3 - Enviado
    @DatabaseField
    private Long statusConBolMMFert;  //0 - OffLine; 1 - OnLine
    @DatabaseField
    private Double longitudeBolMMFert;
    @DatabaseField
    private Double latitudeBolMMFert;
    @DatabaseField
    private Long tipoBolMMFert; //1 - Moto Mec; 2 - Fertirrigacao
    @DatabaseField
    private Long dthrLongFinalBolMMFert;

    public BoletimMMFertBean() {
    }

    public Long getIdBolMMFert() {
        return idBolMMFert;
    }

    public void setIdBolMMFert(Long idBolMMFert) {
        this.idBolMMFert = idBolMMFert;
    }

    public Long getIdExtBolMMFert() {
        return idExtBolMMFert;
    }

    public void setIdExtBolMMFert(Long idExtBolMMFert) {
        this.idExtBolMMFert = idExtBolMMFert;
    }

    public Long getMatricFuncBolMMFert() {
        return matricFuncBolMMFert;
    }

    public void setMatricFuncBolMMFert(Long matricFuncBolMMFert) {
        this.matricFuncBolMMFert = matricFuncBolMMFert;
    }

    public Long getIdEquipBolMMFert() {
        return idEquipBolMMFert;
    }

    public void setIdEquipBolMMFert(Long idEquipBolMMFert) {
        this.idEquipBolMMFert = idEquipBolMMFert;
    }

    public Long getIdEquipBombaBolMMFert() {
        return idEquipBombaBolMMFert;
    }

    public void setIdEquipBombaBolMMFert(Long idEquipBombaBolMMFert) {
        this.idEquipBombaBolMMFert = idEquipBombaBolMMFert;
    }

    public Long getIdTurnoBolMMFert() {
        return idTurnoBolMMFert;
    }

    public void setIdTurnoBolMMFert(Long idTurnoBolMMFert) {
        this.idTurnoBolMMFert = idTurnoBolMMFert;
    }

    public Double getHodometroInicialBolMMFert() {
        return hodometroInicialBolMMFert;
    }

    public void setHodometroInicialBol(Double hodometroInicialBol, Double longitudeBol, Double latitudeBol) {
        this.hodometroInicialBolMMFert = hodometroInicialBol;
        this.longitudeBolMMFert = longitudeBol;
        this.latitudeBolMMFert = latitudeBol;
    }

    public Double getHodometroFinalBolMMFert() {
        return hodometroFinalBolMMFert;
    }

    public void setHodometroFinalBolMMFert(Double hodometroFinalBolMMFert) {
        this.hodometroFinalBolMMFert = hodometroFinalBolMMFert;
    }

    public Long getOsBolMMFert() {
        return osBolMMFert;
    }

    public void setOsBolMMFert(Long osBolMMFert) {
        this.osBolMMFert = osBolMMFert;
    }

    public Long getAtivPrincBolMMFert() {
        return ativPrincBolMMFert;
    }

    public void setAtivPrincBolMMFert(Long ativPrincBolMMFert) {
        this.ativPrincBolMMFert = ativPrincBolMMFert;
    }

    public String getDthrInicialBolMMFert() {
        return dthrInicialBolMMFert;
    }

    public void setDthrInicialBolMMFert(String dthrInicialBolMMFert) {
        this.dthrInicialBolMMFert = dthrInicialBolMMFert;
    }

    public String getDthrFinalBolMMFert() {
        return dthrFinalBolMMFert;
    }

    public void setDthrFinalBolMMFert(String dthrFinalBolMMFert) {
        this.dthrFinalBolMMFert = dthrFinalBolMMFert;
    }

    public Long getStatusBolMMFert() {
        return statusBolMMFert;
    }

    public void setStatusBolMMFert(Long statusBolMMFert) {
        this.statusBolMMFert = statusBolMMFert;
    }

    public Long getStatusConBolMMFert() {
        return statusConBolMMFert;
    }

    public void setStatusConBolMMFert(Long statusConBolMMFert) {
        this.statusConBolMMFert = statusConBolMMFert;
    }

    public Double getLongitudeBolMMFert() {
        return longitudeBolMMFert;
    }

    public Double getLatitudeBolMMFert() {
        return latitudeBolMMFert;
    }

    public Long getTipoBolMMFert() {
        return tipoBolMMFert;
    }

    public void setTipoBolMMFert(Long tipoBolMMFert) {
        this.tipoBolMMFert = tipoBolMMFert;
    }

    public Long getDthrLongFinalBolMMFert() {
        return dthrLongFinalBolMMFert;
    }

    public void setDthrLongFinalBolMMFert(Long dthrLongFinalBolMMFert) {
        this.dthrLongFinalBolMMFert = dthrLongFinalBolMMFert;
    }
}
