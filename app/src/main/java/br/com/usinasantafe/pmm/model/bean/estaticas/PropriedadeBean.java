package br.com.usinasantafe.pmm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbpropriedadeest")
public class PropriedadeBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idPropriedade;
    @DatabaseField
    private String descrPropriedade;

    public PropriedadeBean() {
    }

    public Long getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(Long idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public String getDescrPropriedade() {
        return descrPropriedade;
    }

    public void setDescrPropriedade(String descrPropriedade) {
        this.descrPropriedade = descrPropriedade;
    }
}
