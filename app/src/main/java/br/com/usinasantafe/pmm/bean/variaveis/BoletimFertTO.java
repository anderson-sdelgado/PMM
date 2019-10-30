package br.com.usinasantafe.pmm.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbbolfertvar")
public class BoletimFertTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idBolFert;
    @DatabaseField
    private Long idExtBolFert;
    @DatabaseField
    private Long matricFuncBolFert;
    @DatabaseField
    private Long idEquipBolFert;
    @DatabaseField
    private Long idEquipBombaBolFert;
    @DatabaseField
    private Long idTurnoBolFert;
    @DatabaseField
    private Double hodometroInicialBolFert;
    @DatabaseField
    private Double hodometroFinalBolFert;
    @DatabaseField
    private Long osBolFert;
    @DatabaseField
    private Long ativPrincBolFert;
    @DatabaseField
    private String dthrInicialBolFert;
    @DatabaseField
    private String dthrFinalBolFert;
    @DatabaseField
    private Long statusBolFert;  //0 - Esta apontando os implementos; 1 - Aberto; 2 - Encerrado
    @DatabaseField
    private Long statusConBolFert;  //0 - OffLine; 1 - OnLine
    @DatabaseField
    private Long qtdeApontBolFert;
    @DatabaseField
    private Double longitudeBolFert;
    @DatabaseField
    private Double latitudeBolFert;

    public BoletimFertTO() {
    }

    public Long getIdBolFert() {
        return idBolFert;
    }

    public void setIdBolFert(Long idBolFert) {
        this.idBolFert = idBolFert;
    }

    public Long getIdExtBolFert() {
        return idExtBolFert;
    }

    public void setIdExtBolFert(Long idExtBolFert) {
        this.idExtBolFert = idExtBolFert;
    }

    public Long getMatricFuncBolFert() {
        return matricFuncBolFert;
    }

    public void setMatricFuncBolFert(Long matricFuncBolFert) {
        this.matricFuncBolFert = matricFuncBolFert;
    }

    public Long getIdEquipBolFert() {
        return idEquipBolFert;
    }

    public void setIdEquipBolFert(Long idEquipBolFert) {
        this.idEquipBolFert = idEquipBolFert;
    }

    public Long getIdTurnoBolFert() {
        return idTurnoBolFert;
    }

    public void setIdTurnoBolFert(Long idTurnoBolFert) {
        this.idTurnoBolFert = idTurnoBolFert;
    }

    public Double getHodometroInicialBolFert() {
        return hodometroInicialBolFert;
    }

    public void setHodometroInicialBolFert(Double hodometroInicialBolFert) {
        this.hodometroInicialBolFert = hodometroInicialBolFert;
    }

    public Double getHodometroFinalBolFert() {
        return hodometroFinalBolFert;
    }

    public void setHodometroFinalBolFert(Double hodometroFinalBolFert) {
        this.hodometroFinalBolFert = hodometroFinalBolFert;
    }

    public Long getOsBolFert() {
        return osBolFert;
    }

    public void setOsBolFert(Long osBolFert) {
        this.osBolFert = osBolFert;
    }

    public Long getAtivPrincBolFert() {
        return ativPrincBolFert;
    }

    public void setAtivPrincBolFert(Long ativPrincBolFert) {
        this.ativPrincBolFert = ativPrincBolFert;
    }

    public String getDthrInicialBolFert() {
        return dthrInicialBolFert;
    }

    public void setDthrInicialBolFert(String dthrInicialBolFert) {
        this.dthrInicialBolFert = dthrInicialBolFert;
    }

    public String getDthrFinalBolFert() {
        return dthrFinalBolFert;
    }

    public void setDthrFinalBolFert(String dthrFinalBolFert) {
        this.dthrFinalBolFert = dthrFinalBolFert;
    }

    public Long getStatusBolFert() {
        return statusBolFert;
    }

    public void setStatusBolFert(Long statusBolFert) {
        this.statusBolFert = statusBolFert;
    }

    public Long getStatusConBolFert() {
        return statusConBolFert;
    }

    public void setStatusConBolFert(Long statusConBolFert) {
        this.statusConBolFert = statusConBolFert;
    }

    public Long getIdEquipBombaBolFert() {
        return idEquipBombaBolFert;
    }

    public void setIdEquipBombaBolFert(Long idEquipBombaBolFert) {
        this.idEquipBombaBolFert = idEquipBombaBolFert;
    }

    public Long getQtdeApontBolFert() {
        return qtdeApontBolFert;
    }

    public void setQtdeApontBolFert(Long qtdeApontBolFert) {
        this.qtdeApontBolFert = qtdeApontBolFert;
    }

    public Double getLongitudeBolFert() {
        return longitudeBolFert;
    }

    public void setLongitudeBolFert(Double longitudeBolFert) {
        this.longitudeBolFert = longitudeBolFert;
    }

    public Double getLatitudeBolFert() {
        return latitudeBolFert;
    }

    public void setLatitudeBolFert(Double latitudeBolFert) {
        this.latitudeBolFert = latitudeBolFert;
    }
}
