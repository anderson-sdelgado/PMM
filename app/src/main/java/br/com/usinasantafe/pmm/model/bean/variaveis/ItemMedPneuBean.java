package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbitemmedpneuvar")
public class ItemMedPneuBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemMedPneu;
    @DatabaseField
    private Long idBolItemMedPneu;
    @DatabaseField
    private Long idPosConfPneu;
    @DatabaseField
    private String nroPneuItemMedPneu;
    @DatabaseField
    private Long pressaoEncItemMedPneu;
    @DatabaseField
    private Long pressaoColItemMedPneu;
    @DatabaseField
    private String dthrItemMedPneu;

    public ItemMedPneuBean() {
    }

    public Long getIdItemMedPneu() {
        return idItemMedPneu;
    }

    public void setIdItemMedPneu(Long idItemMedPneu) {
        this.idItemMedPneu = idItemMedPneu;
    }

    public Long getIdBolItemMedPneu() {
        return idBolItemMedPneu;
    }

    public void setIdBolItemMedPneu(Long idBolItemMedPneu) {
        this.idBolItemMedPneu = idBolItemMedPneu;
    }

    public Long getIdPosConfPneu() {
        return idPosConfPneu;
    }

    public void setIdPosConfPneu(Long idPosConfPneu) {
        this.idPosConfPneu = idPosConfPneu;
    }

    public String getNroPneuItemMedPneu() {
        return nroPneuItemMedPneu;
    }

    public void setNroPneuItemMedPneu(String nroPneuItemMedPneu) {
        this.nroPneuItemMedPneu = nroPneuItemMedPneu;
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

    public String getDthrItemMedPneu() {
        return dthrItemMedPneu;
    }

    public void setDthrItemMedPneu(String dthrItemMedPneu) {
        this.dthrItemMedPneu = dthrItemMedPneu;
    }
}