package br.com.usinasantafe.cmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import br.com.usinasantafe.cmm.model.pst.Entidade;

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
    private Long statusEnviarMMFert;  //1 - Enviar; 2 - Enviado
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
    private List<ApontMMFertBean> apontMMFertList;
    private List<BoletimPneuBean> boletimPneuList;
    private List<ApontMecanBean> apontMecanList;
    private List<RendMMBean> rendMMList;
    private List<RecolhFertBean> recolhFertList;

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

    public List<ApontMMFertBean> getApontMMFertList() {
        return apontMMFertList;
    }

    public void setApontMMFertList(List<ApontMMFertBean> apontMMFertList) {
        this.apontMMFertList = apontMMFertList;
    }

    public List<BoletimPneuBean> getBoletimPneuList() {
        return boletimPneuList;
    }

    public void setBoletimPneuList(List<BoletimPneuBean> boletimPneuList) {
        this.boletimPneuList = boletimPneuList;
    }

    public List<ApontMecanBean> getApontMecanList() {
        return apontMecanList;
    }

    public void setApontMecanList(List<ApontMecanBean> apontMecanList) {
        this.apontMecanList = apontMecanList;
    }

    public List<RendMMBean> getRendMMList() {
        return rendMMList;
    }

    public void setRendMMList(List<RendMMBean> rendMMList) {
        this.rendMMList = rendMMList;
    }

    public List<RecolhFertBean> getRecolhFertList() {
        return recolhFertList;
    }

    public void setRecolhFertList(List<RecolhFertBean> recolhFertList) {
        this.recolhFertList = recolhFertList;
    }

    public Long getStatusEnviarMMFert() {
        return statusEnviarMMFert;
    }

    public void setStatusEnviarMMFert(Long statusEnviarMMFert) {
        this.statusEnviarMMFert = statusEnviarMMFert;
    }
}
