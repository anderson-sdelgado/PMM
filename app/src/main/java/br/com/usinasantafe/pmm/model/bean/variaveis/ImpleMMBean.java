package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

/**
 * Created by anderson on 06/11/2017.
 */
@DatabaseTable(tableName="tbimplementovar")
public class ImpleMMBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long posImpleMM;
    @DatabaseField
    private Long codEquipImpleMM;

    public ImpleMMBean() {
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

}
