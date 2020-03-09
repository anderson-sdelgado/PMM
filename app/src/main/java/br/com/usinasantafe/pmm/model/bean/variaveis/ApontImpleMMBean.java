package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbapontimplemmvar")
public class ApontImpleMMBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApontImpleMM;
    @DatabaseField
    private Long idApontMM;
    @DatabaseField
    private Long posImpleMM;
    @DatabaseField
    private Long codEquipImpleMM;
    @DatabaseField
    private String dthrImpleMM;

    public Long getIdApontImpleMM() {
        return idApontImpleMM;
    }

    public void setIdApontImpleMM(Long idApontImpleMM) {
        this.idApontImpleMM = idApontImpleMM;
    }

    public Long getIdApontMM() {
        return idApontMM;
    }

    public void setIdApontMM(Long idapontMM) {
        this.idApontMM = idapontMM;
    }

    public Long getPosImpleMM() {
        return posImpleMM;
    }

    public void setPosImpleMM(Long posImpleMM) {
        this.posImpleMM = posImpleMM;
    }

    public Long getCodEquipImpleMM() {
        return codEquipImpleMM;
    }

    public void setCodEquipImpleMM(Long codEquipImpleMM) {
        this.codEquipImpleMM = codEquipImpleMM;
    }

    public String getDthrImpleMM() {
        return dthrImpleMM;
    }

    public void setDthrImpleMM(String dthrImpleMM) {
        this.dthrImpleMM = dthrImpleMM;
    }
}
