package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

/**
 * Created by anderson on 06/11/2017.
 */
@DatabaseTable(tableName="tbimplementovar")
public class ImplementoMMBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long posImplMM;
    @DatabaseField
    private Long codEquipImplMM;

    public ImplementoMMBean() {
    }

    public Long getCodEquipImplMM() {
        return codEquipImplMM;
    }

    public void setCodEquipImplMM(Long codEquipImplMM) {
        this.codEquipImplMM = codEquipImplMM;
    }

    public Long getPosImplMM() {
        return posImplMM;
    }

    public void setPosImplMM(Long posImplMM) {
        this.posImplMM = posImplMM;
    }

}
