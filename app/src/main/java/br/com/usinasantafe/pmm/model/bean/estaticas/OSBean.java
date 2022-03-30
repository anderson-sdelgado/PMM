/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.pmm.model.bean.estaticas;

/**
 *
 * @author anderson
 */
import br.com.usinasantafe.pmm.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbosest")
public class OSBean extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(id=true)
	private Long idOS;
	@DatabaseField
    private Long nroOS;
	@DatabaseField
	private Long idLibOS;
	@DatabaseField
	private Long idProprAgr;
	@DatabaseField
	private Double areaProgrOS;
	@DatabaseField
	private Long tipoOS;
	@DatabaseField
	private Long idEquip;

    public OSBean() {
    }

	public Long getIdOS() {
		return idOS;
	}

	public void setIdOS(Long idOS) {
		this.idOS = idOS;
	}

	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroOS) {
		this.nroOS = nroOS;
	}

	public Long getIdLibOS() {
		return idLibOS;
	}

	public void setIdLibOS(Long idLibOS) {
		this.idLibOS = idLibOS;
	}

	public Long getIdProprAgr() {
		return idProprAgr;
	}

	public void setIdProprAgr(Long idProprAgr) {
		this.idProprAgr = idProprAgr;
	}

	public Double getAreaProgrOS() {
		return areaProgrOS;
	}

	public void setAreaProgrOS(Double areaProgrOS) {
		this.areaProgrOS = areaProgrOS;
	}

	public Long getTipoOS() {
		return tipoOS;
	}

	public void setTipoOS(Long tipoOS) {
		this.tipoOS = tipoOS;
	}

	public Long getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(Long idEquip) {
		this.idEquip = idEquip;
	}
}
