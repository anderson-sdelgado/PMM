package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 24/10/2017.
 */
@DatabaseTable(tableName="tbtransbordovar")
public class TransbordoTO extends Entidade {

    @DatabaseField(generatedId=true)
    private Long idTransbordo;
    @DatabaseField
    private Long codEquipTransbordo;

    public TransbordoTO() {
    }

    public Long getCodEquipTransbordo() {
        return codEquipTransbordo;
    }

    public void setCodEquipTransbordo(Long codEquipTransbordo) {
        this.codEquipTransbordo = codEquipTransbordo;
    }


}
