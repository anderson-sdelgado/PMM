package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbitemmedpneuvar")
public class ItemMedPneuTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemMedPneu;
    @DatabaseField
    private Long posItemMedPneu;
    @DatabaseField
    private Long nroItemMedPneu;
    @DatabaseField
    private Long pressaoEncItemMedPneu;
    @DatabaseField
    private Long pressaoColItemMedPneu;

    public ItemMedPneuTO() {
    }

    public Long getIdItemMedPneu() {
        return idItemMedPneu;
    }

    public void setIdItemMedPneu(Long idItemMedPneu) {
        this.idItemMedPneu = idItemMedPneu;
    }

    public Long getPosItemMedPneu() {
        return posItemMedPneu;
    }

    public void setPosItemMedPneu(Long posItemMedPneu) {
        this.posItemMedPneu = posItemMedPneu;
    }

    public Long getNroItemMedPneu() {
        return nroItemMedPneu;
    }

    public void setNroItemMedPneu(Long nroItemMedPneu) {
        this.nroItemMedPneu = nroItemMedPneu;
    }

    public Long getPressaoEncItemMedPneu() {
        return pressaoEncItemMedPneu;
    }

    public void setPressaoEncItemMedPneu(Long pressaoEncItemMedPneu) {
        this.pressaoEncItemMedPneu = pressaoEncItemMedPneu;
    }

    public Long getPressaoColItemMedPneu() {
        return pressaoColItemMedPneu;
    }

    public void setPressaoColItemMedPneu(Long pressaoColItemMedPneu) {
        this.pressaoColItemMedPneu = pressaoColItemMedPneu;
    }

}
