package br.com.usinasantafe.pmm.model.bean;

/**
 * Created by anderson on 24/07/2017.
 */
public class AtualAplicBean {

    private Long idEquip;
    private Long nroEquip;
    private Long idCheckList;
    private String versao;
    private Long flagAtualCheckList;
    private String dthr;
    private String token;
    private Long nroOS;
    private String codPneu;
    private String aplic;

    public AtualAplicBean() {
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getNroEquip() {
        return nroEquip;
    }

    public void setNroEquip(Long nroEquip) {
        this.nroEquip = nroEquip;
    }

    public Long getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(Long idCheckList) {
        this.idCheckList = idCheckList;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Long getFlagAtualCheckList() {
        return flagAtualCheckList;
    }

    public void setFlagAtualCheckList(Long flagAtualCheckList) {
        this.flagAtualCheckList = flagAtualCheckList;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getNroOS() {
        return nroOS;
    }

    public void setNroOS(Long nroOS) {
        this.nroOS = nroOS;
    }

    public String getCodPneu() {
        return codPneu;
    }

    public void setCodPneu(String codPneu) {
        this.codPneu = codPneu;
    }

    public String getAplic() {
        return aplic;
    }

    public void setAplic(String aplic) {
        this.aplic = aplic;
    }
}
