package br.com.usinasantafe.pmm.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 24/10/2017.
 */
@DatabaseTable(tableName="tbtransbmmvar")
public class TransbMMTO extends Entidade {

    @DatabaseField(generatedId=true)
    private Long idTransbMM;
    @DatabaseField
    private Long codEquipTransbMM;

    public TransbMMTO() {
    }

    public Long getCodEquipTransbMM() {
        return codEquipTransbMM;
    }

    public void setCodEquipTransbMM(Long codEquipTransbMM) {
        this.codEquipTransbMM = codEquipTransbMM;
    }

}
