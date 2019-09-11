package br.com.usinasantafe.pmm.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbbackupapontavar")
public class BackupApontaTO extends Entidade  {

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
	private Long transbAponta;
	@DatabaseField
	private Double pressaoAponta;
	@DatabaseField
	private Long velocAponta;
	@DatabaseField
	private Long bocalAponta;
	@DatabaseField
	private String dthrAponta;

	public BackupApontaTO() {
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

	public Long getTransbAponta() {
		return transbAponta;
	}

	public void setTransbAponta(Long transbAponta) {
		this.transbAponta = transbAponta;
	}

	public Double getPressaoAponta() {
		return pressaoAponta;
	}

	public void setPressaoAponta(Double pressaoAponta) {
		this.pressaoAponta = pressaoAponta;
	}

	public Long getVelocAponta() {
		return velocAponta;
	}

	public void setVelocAponta(Long velocAponta) {
		this.velocAponta = velocAponta;
	}

	public Long getBocalAponta() {
		return bocalAponta;
	}

	public void setBocalAponta(Long bocalAponta) {
		this.bocalAponta = bocalAponta;
	}
}
