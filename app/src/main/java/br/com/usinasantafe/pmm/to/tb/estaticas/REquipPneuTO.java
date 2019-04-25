package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbrequippneuest")
public class REquipPneuTO {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idPosConfPneu;
    @DatabaseField
    private String posPneu;

    public REquipPneuTO() {
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

}
