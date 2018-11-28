package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 01/03/2018.
 */

@DatabaseTable(tableName="tbcarretelvar")
public class AlocaCarretelTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEquipCarretel;
    @DatabaseField
    private Long codEquipCarretel;

    public AlocaCarretelTO() {
    }

    public Long getIdEquipCarretel() {
        return idEquipCarretel;
    }

    public void setIdEquipCarretel(Long idEquipCarretel) {
        this.idEquipCarretel = idEquipCarretel;
    }

    public Long getCodEquipCarretel() {
        return codEquipCarretel;
    }

    public void setCodEquipCarretel(Long codEquipCarretel) {
        this.codEquipCarretel = codEquipCarretel;
    }

}
