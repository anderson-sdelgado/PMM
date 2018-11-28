package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbbackupapontaaplicf")
public class BackupApontaAplicFertTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idApontaAplicFert;
	@DatabaseField
	private Long equipApontaAplicFert;
	@DatabaseField
	private Long osApontaAplicFert;
	@DatabaseField
	private Long ativApontaAplicFert;
	@DatabaseField
	private Long paradaApontaAplicFert;
	@DatabaseField
	private String dthrApontaAplicFert;
	@DatabaseField
	private Double pressaoApontaAplicFert;
	@DatabaseField
	private Long velocApontaAplicFert;
	@DatabaseField
	private Long bocalApontaAplicFert;
	@DatabaseField
	private Double raioApontaAplicFert;


	public BackupApontaAplicFertTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdApontaAplicFert() {
		return idApontaAplicFert;
	}

	public void setIdApontaAplicFert(Long idApontaAplicFert) {
		this.idApontaAplicFert = idApontaAplicFert;
	}

	public Long getOsApontaAplicFert() {
		return osApontaAplicFert;
	}

	public void setOsApontaAplicFert(Long osApontaAplicFert) {
		this.osApontaAplicFert = osApontaAplicFert;
	}

	public Long getAtivApontaAplicFert() {
		return ativApontaAplicFert;
	}

	public void setAtivApontaAplicFert(Long ativApontaAplicFert) {
		this.ativApontaAplicFert = ativApontaAplicFert;
	}

	public Long getParadaApontaAplicFert() {
		return paradaApontaAplicFert;
	}

	public void setParadaApontaAplicFert(Long paradaApontaAplicFert) {
		this.paradaApontaAplicFert = paradaApontaAplicFert;
	}

	public String getDthrApontaAplicFert() {
		return dthrApontaAplicFert;
	}

	public void setDthrApontaAplicFert(String dthrApontaAplicFert) {
		this.dthrApontaAplicFert = dthrApontaAplicFert;
	}

	public Double getPressaoApontaAplicFert() {
		return pressaoApontaAplicFert;
	}

	public void setPressaoApontaAplicFert(Double pressaoApontaAplicFert) {
		this.pressaoApontaAplicFert = pressaoApontaAplicFert;
	}

	public Long getVelocApontaAplicFert() {
		return velocApontaAplicFert;
	}

	public void setVelocApontaAplicFert(Long velocApontaAplicFert) {
		this.velocApontaAplicFert = velocApontaAplicFert;
	}

	public Long getBocalApontaAplicFert() {
		return bocalApontaAplicFert;
	}

	public void setBocalApontaAplicFert(Long bocalApontaAplicFert) {
		this.bocalApontaAplicFert = bocalApontaAplicFert;
	}

	public Double getRaioApontaAplicFert() {
		return raioApontaAplicFert;
	}

	public void setRaioApontaAplicFert(Double raioApontaAplicFert) {
		this.raioApontaAplicFert = raioApontaAplicFert;
	}

	public Long getEquipApontaAplicFert() {
		return equipApontaAplicFert;
	}

	public void setEquipApontaAplicFert(Long equipApontaAplicFert) {
		this.equipApontaAplicFert = equipApontaAplicFert;
	}
}
