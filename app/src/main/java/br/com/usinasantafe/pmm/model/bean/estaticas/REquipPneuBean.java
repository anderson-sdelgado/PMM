package br.com.usinasantafe.pmm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbrequippneuest")
public class REquipPneuBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idPosConfPneu;
    @DatabaseField
    private String posPneu;
    @DatabaseField
    private Long statusPneu;

    public REquipPneuBean() {
    }

    public Long getIdPosConfPneu() {
        return idPosConfPneu;
    }

    public void setIdPosConfPneu(Long idPosConfPneu) {
        this.idPosConfPneu = idPosConfPneu;
    }

    public String getPosPneu() {
        return posPneu;
    }

    public void setPosPneu(String posPneu) {
        this.posPneu = posPneu;
    }

    public Long getStatusPneu() {
        return statusPneu;
    }

    public void setStatusPneu(Long statusPneu) {
        this.statusPneu = statusPneu;
    }
}
