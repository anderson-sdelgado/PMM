package br.com.usinasantafe.pmm.to.tb.variaveis;

import br.com.usinasantafe.pmm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbapontammvar")
public class ApontaMMTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idAponta;
	@DatabaseField
	private Long idBolAponta;
	@DatabaseField
	private Long idExtBolAponta;
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
	@DatabaseField
	private Double latitudeAponta;
	@DatabaseField
	private Double longitudeAponta;
	@DatabaseField
	private Long statusConAponta;  //0 - OffLine; 1 - OnLine
	@DatabaseField
	private Long statusAponta;  //1 - Aberto; 2 - Encerrado

	public ApontaMMTO() {
	}

	public Long getIdAponta() {
		return idAponta;
	}

	public Long getIdBolAponta() {
		return idBolAponta;
	}

	public void setIdBolAponta(Long idBolAponta) {
		this.idBolAponta = idBolAponta;
	}

	public Long getIdExtBolAponta() {
		return idExtBolAponta;
	}

	public void setIdExtBolAponta(Long idExtBolAponta) {
		this.idExtBolAponta = idExtBolAponta;
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

	public Double getLatitudeAponta() {
		return latitudeAponta;
	}

	public void setLatitudeAponta(Double latitudeAponta) {
		this.latitudeAponta = latitudeAponta;
	}

	public Double getLongitudeAponta() {
		return longitudeAponta;
	}

	public void setLongitudeAponta(Double longitudeAponta) {
		this.longitudeAponta = longitudeAponta;
	}

	public Long getStatusConAponta() {
		return statusConAponta;
	}

	public void setStatusConAponta(Long statusConAponta) {
		this.statusConAponta = statusConAponta;
	}

	public Long getStatusAponta() {
		return statusAponta;
	}

	public void setStatusAponta(Long statusAponta) {
		this.statusAponta = statusAponta;
	}
}
