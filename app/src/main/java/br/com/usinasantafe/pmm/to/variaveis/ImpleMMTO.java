package br.com.usinasantafe.pmm.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 06/11/2017.
 */
@DatabaseTable(tableName="tbimplemmvar")
public class ImpleMMTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idImpleMM;
    @DatabaseField
    private Long idApontImpleMM;
    @DatabaseField
    private Long posImpleMM;
    @DatabaseField
    private Long codEquipImpleMM;
    @DatabaseField
    private String dthrImpleMM;

    public ImpleMMTO() {
    }

    public Long getIdImpleMM() {
        return idImpleMM;
    }

    public void setIdImpleMM(Long idImpleMM) {
        this.idImpleMM = idImpleMM;
    }

    public Long getIdApontImpleMM() {
        return idApontImpleMM;
    }

    public void setIdApontImpleMM(Long idApontImpleMM) {
        this.idApontImpleMM = idApontImpleMM;
    }

    public Long getCodEquipImpleMM() {
        return codEquipImpleMM;
    }

    public void setCodEquipImpleMM(Long codEquipImpleMM) {
        this.codEquipImpleMM = codEquipImpleMM;
    }

    public Long getPosImpleMM() {
        return posImpleMM;
    }

    public void setPosImpleMM(Long posImpleMM) {
        this.posImpleMM = posImpleMM;
    }

    public String getDthrImpleMM() {
        return dthrImpleMM;
    }

    public void setDthrImpleMM(String dthrImpleMM) {
        this.dthrImpleMM = dthrImpleMM;
    }
}
