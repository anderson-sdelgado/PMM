/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.pmm.bean.estaticas;

/**
 *
 * @author anderson
 */

import br.com.usinasantafe.pmm.pst.Entidade;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbequipest")
public class EquipTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idEquip;
    @DatabaseField
    private Long nroEquip;
    @DatabaseField
    private Long codClasseEquip;
    @DatabaseField
    private String descrClasseEquip;
    @DatabaseField
    private Long codTurno;
    @DatabaseField
    private Long idCheckList;
    @DatabaseField
    private Long tipoEquipFert;
    @DatabaseField
    private Double horimetroEquip;
    @DatabaseField
    private Double medicaoEquipFert;
    @DatabaseField
    private Long tipo;

    public EquipTO() {
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getNroEquip() {
        return nroEquip;
    }

    public void setNroEquip(Long nroEquip) {
        this.nroEquip = nroEquip;
    }

    public Long getCodClasseEquip() {
        return codClasseEquip;
    }

    public void setCodClasseEquip(Long codClasseEquip) {
        this.codClasseEquip = codClasseEquip;
    }

    public String getDescrClasseEquip() {
        return descrClasseEquip;
    }

    public void setDescrClasseEquip(String descrClasseEquip) {
        this.descrClasseEquip = descrClasseEquip;
    }

    public Long getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(Long codTurno) {
        this.codTurno = codTurno;
    }

    public Long getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(Long idCheckList) {
        this.idCheckList = idCheckList;
    }

    public Long getTipoEquipFert() {
        return tipoEquipFert;
    }

    public void setTipoEquipFert(Long tipoEquipFert) {
        this.tipoEquipFert = tipoEquipFert;
    }

    public Double getHorimetroEquip() {
        return horimetroEquip;
    }

    public void setHorimetroEquip(Double horimetroEquip) {
        this.horimetroEquip = horimetroEquip;
    }

    public Double getMedicaoEquipFert() {
        return medicaoEquipFert;
    }

    public void setMedicaoEquipFert(Double medicaoEquipFert) {
        this.medicaoEquipFert = medicaoEquipFert;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }
}
