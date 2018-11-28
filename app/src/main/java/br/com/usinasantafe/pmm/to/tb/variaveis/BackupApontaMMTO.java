package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbbackupapontammvar")
public class BackupApontaMMTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idAponta;
	@DatabaseField
	private Long osAponta;
	@DatabaseField
	private Long atividadeAponta;
	@DatabaseField
	private Long paradaAponta;
	@DatabaseField
	private Long transbordoAponta;
	@DatabaseField
	private String dthrAponta;

	public BackupApontaMMTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdAponta() {
		return idAponta;
	}

	public void setIdAponta(Long idAponta) {
		this.idAponta = idAponta;
	}

	public Long getOsAponta() {
		return osAponta;
	}

	public void setOsAponta(Long osAponta) {
		this.osAponta = osAponta;
	}

	public Long getAtividadeAponta() {
		return atividadeAponta;
	}

	public void setAtividadeAponta(Long atividadeAponta) {
		this.atividadeAponta = atividadeAponta;
	}

	public Long getParadaAponta() {
		return paradaAponta;
	}

	public void setParadaAponta(Long paradaAponta) {
		this.paradaAponta = paradaAponta;
	}

	public String getDthrAponta() {
		return dthrAponta;
	}

	public void setDthrAponta(String dthrAponta) {
		this.dthrAponta = dthrAponta;
	}

	public Long getTransbordoAponta() {
		return transbordoAponta;
	}

	public void setTransbordoAponta(Long transbordoAponta) {
		this.transbordoAponta = transbordoAponta;
	}
}
