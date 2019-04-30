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
    private Long idBolItemMedPneu;
    @DatabaseField
    private Long posItemMedPneu;
    @DatabaseField
    private Long idPneuItemMedPneu;
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

    public Long getIdPneuItemMedPneu() {
        return idPneuItemMedPneu;
    }

    public void setIdPneuItemMedPneu(Long idPneuItemMedPneu) {
        this.idPneuItemMedPneu = idPneuItemMedPneu;
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

    public Long getIdBolItemMedPneu() {
        return idBolItemMedPneu;
    }

    public void setIdBolItemMedPneu(Long idBolItemMedPneu) {
        this.idBolItemMedPneu = idBolItemMedPneu;
    }
}
