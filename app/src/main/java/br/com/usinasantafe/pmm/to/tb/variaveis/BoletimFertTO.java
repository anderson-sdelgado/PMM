package br.com.usinasantafe.pmm.to.tb.variaveis;

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
    private Long codMotoBolFert;
    @DatabaseField
    private Long codEquipBolFert;
    @DatabaseField
    private Long codEquipBombaBolFert;
    @DatabaseField
    private Long codTurnoBolFert;
    @DatabaseField
    private Double hodometroInicialBolFert;
    @DatabaseField
    private Double hodometroFinalBolFert;
    @DatabaseField
    private Long osBolFert;
    @DatabaseField
    private Long ativPrincBolFert;
    @DatabaseField
    private String dthrInicioBolFert;
    @DatabaseField
    private String dthrFimBolFert;
    @DatabaseField
    private Long statusBolFert;  //0 - Esta apontando os implementos; 1 - Aberto; 2 - Encerrado
    @DatabaseField
    private Long statusConBolFert;  //0 - OffLine; 1 - OnLine

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

    public Long getCodMotoBolFert() {
        return codMotoBolFert;
    }

    public void setCodMotoBolFert(Long codMotoBolFert) {
        this.codMotoBolFert = codMotoBolFert;
    }

    public Long getCodEquipBolFert() {
        return codEquipBolFert;
    }

    public void setCodEquipBolFert(Long codEquipBolFert) {
        this.codEquipBolFert = codEquipBolFert;
    }

    public Long getCodTurnoBolFert() {
        return codTurnoBolFert;
    }

    public void setCodTurnoBolFert(Long codTurnoBolFert) {
        this.codTurnoBolFert = codTurnoBolFert;
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

    public String getDthrInicioBolFert() {
        return dthrInicioBolFert;
    }

    public void setDthrInicioBolFert(String dthrInicioBolFert) {
        this.dthrInicioBolFert = dthrInicioBolFert;
    }

    public String getDthrFimBolFert() {
        return dthrFimBolFert;
    }

    public void setDthrFimBolFert(String dthrFimBolFert) {
        this.dthrFimBolFert = dthrFimBolFert;
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

    public Long getCodEquipBombaBolFert() {
        return codEquipBombaBolFert;
    }

    public void setCodEquipBombaBolFert(Long codEquipBombaBolFert) {
        this.codEquipBombaBolFert = codEquipBombaBolFert;
    }
}
