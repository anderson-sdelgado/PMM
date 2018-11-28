package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbturnoest")
public class TurnoTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long idTurno;
	@DatabaseField
	private Long codTurno;
	@DatabaseField
	private Long nroTurno;
	@DatabaseField
	private String descTurno;
	
	public TurnoTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getIdTurno() {
		return idTurno;
	}
	
	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public Long getCodTurno() {
		return codTurno;
	}

	public void setCodTurno(Long codTurno) {
		this.codTurno = codTurno;
	}

	public Long getNroTurno() {
		return nroTurno;
	}

	public void setNroTurno(Long nroTurno) {
		this.nroTurno = nroTurno;
	}

	public String getDescTurno() {
		return descTurno;
	}
	
	public void setDescTurno(String descTurno) {
		this.descTurno = descTurno;
	}
	
}
