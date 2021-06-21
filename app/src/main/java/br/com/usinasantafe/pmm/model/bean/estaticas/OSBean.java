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

	@DatabaseField(generatedId=true)
	private Long idRegistro;
	@DatabaseField
	private Long idOS;
	@DatabaseField
    private Long nroOS;
	@DatabaseField
	private Long idLibOS;
	@DatabaseField
	private Long idProprAgr;
	@DatabaseField
	private String descrProprAgr;
	@DatabaseField
	private Double areaProgrOS;
	@DatabaseField
	private String dtInicProgr;
	@DatabaseField
	private String dtFimProgr;
	@DatabaseField
	private Long tipoOS;
	@DatabaseField
	private Long idAtiv;
	@DatabaseField
	private Long idAtivOS;

    public OSBean() {
    }

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
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

	public String getDescrProprAgr() {
		return descrProprAgr;
	}

	public void setDescrProprAgr(String descrProprAgr) {
		this.descrProprAgr = descrProprAgr;
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

	public Long getIdAtiv() {
		return idAtiv;
	}

	public void setIdAtiv(Long idAtiv) {
		this.idAtiv = idAtiv;
	}

	public Long getIdAtivOS() {
		return idAtivOS;
	}

	public void setIdAtivOS(Long idAtivOS) {
		this.idAtivOS = idAtivOS;
	}
}
