package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 29/03/2017.
 */

@DatabaseTable(tableName="tbitemchecklistest")
public class ItemCheckListTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idItemChecklist;
    @DatabaseField
    private Long idChecklist;
    @DatabaseField
    private Long seqItemChecklist;
    @DatabaseField
    private String descrItemChecklist;

    public ItemCheckListTO() {
    }

    public Long getIdItemChecklist() {
        return idItemChecklist;
    }

    public void setIdItemChecklist(Long idItemChecklist) {
        this.idItemChecklist = idItemChecklist;
    }

    public Long getIdChecklist() {
        return idChecklist;
    }

    public void setIdChecklist(Long idChecklist) {
        this.idChecklist = idChecklist;
    }

    public Long getSeqItemChecklist() {
        return seqItemChecklist;
    }

    public void setSeqItemChecklist(Long seqItemChecklist) {
        this.seqItemChecklist = seqItemChecklist;
    }

    public String getDescrItemChecklist() {
        return descrItemChecklist;
    }

    public void setDescrItemChecklist(String descrItemChecklist) {
        this.descrItemChecklist = descrItemChecklist;
    }
}
