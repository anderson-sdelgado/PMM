package br.com.usinasantafe.cmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.cmm.model.pst.Entidade;

@DatabaseTable(tableName="tbapontimplemmvar")
public class ApontImplMMBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApontImplMM;
    @DatabaseField
    private Long idApontMMFert;
    @DatabaseField
    private Long posImplMM;
    @DatabaseField
    private Long codEquipImplMM;
    @DatabaseField
    private String dthrImplMM;
    @DatabaseField
    private Long statusImplMM; //1 - Envio; 2 - Enviado

    public Long getIdApontImplMM() {
        return idApontImplMM;
    }

    public void setIdApontImplMM(Long idApontImplMM) {
        this.idApontImplMM = idApontImplMM;
    }

    public Long getIdApontMMFert() {
        return idApontMMFert;
    }

    public void setIdApontMMFert(Long idapontMM) {
        this.idApontMMFert = idapontMM;
    }

    public Long getPosImplMM() {
        return posImplMM;
    }

    public void setPosImplMM(Long posImplMM) {
        this.posImplMM = posImplMM;
    }

    public Long getCodEquipImplMM() {
        return codEquipImplMM;
    }

    public void setCodEquipImplMM(Long codEquipImplMM) {
        this.codEquipImplMM = codEquipImplMM;
    }

    public String getDthrImplMM() {
        return dthrImplMM;
    }

    public void setDthrImplMM(String dthrImplMM) {
        this.dthrImplMM = dthrImplMM;
    }

    public Long getStatusImplMM() {
        return statusImplMM;
    }

    public void setStatusImplMM(Long statusImplMM) {
        this.statusImplMM = statusImplMM;
    }
}
