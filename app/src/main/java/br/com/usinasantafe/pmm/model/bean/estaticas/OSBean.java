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
    private Long nroOS;
	@DatabaseField
	private Double areaProgrOS;
	@DatabaseField
	private String dtInicProgr;
	@DatabaseField
	private String dtFimProgr;
	@DatabaseField
	private Long tipoOS;

    public OSBean() {
    }

	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroOS) {
		this.nroOS = nroOS;
	}

	public Double getAreaProgrOS() {
		return areaProgrOS;
	}

	public void setAreaProgrOS(Double areaProgrOS) {
		this.areaProgrOS = areaProgrOS;
	}

	public String getDtInicProgr() {
		return dtInicProgr;
	}

	public void setDtInicProgr(String dtInicProgr) {
		this.dtInicProgr = dtInicProgr;
	}

	public String getDtFimProgr() {
		return dtFimProgr;
	}

	public void setDtFimProgr(String dtFimProgr) {
		this.dtFimProgr = dtFimProgr;
	}

	public Long getTipoOS() {
		return tipoOS;
	}

	public void setTipoOS(Long tipoOS) {
		this.tipoOS = tipoOS;
	}
}
