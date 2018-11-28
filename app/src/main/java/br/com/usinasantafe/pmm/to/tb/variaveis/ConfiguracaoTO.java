package br.com.usinasantafe.pmm.to.tb.variaveis;

import br.com.usinasantafe.pmm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbconfigvar")
public class ConfiguracaoTO extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@DatabaseField
	private Long equipConfig;
	@DatabaseField
	private Long classeEquipConfig;
	@DatabaseField(id=true)
	private String senhaConfig;
	@DatabaseField
	private Long ultTurnoCLConfig;
	@DatabaseField
	private String dtUltApontConfig;
	@DatabaseField
	private Long osConfig;
	@DatabaseField
	private Double horimetroConfig;

	public ConfiguracaoTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getEquipConfig() {
		return equipConfig;
	}

	public void setEquipConfig(Long camconfig) {
		this.equipConfig = camconfig;
	}

	public String getSenhaConfig() {
		return senhaConfig;
	}

	public void setSenhaConfig(String senhaconfig) {
		this.senhaConfig = senhaconfig;
	}

	public Long getClasseEquipConfig() {
		return classeEquipConfig;
	}

	public void setClasseEquipConfig(Long classeEquipConfig) {
		this.classeEquipConfig = classeEquipConfig;
	}

	public Long getUltTurnoCLConfig() {
		return ultTurnoCLConfig;
	}

	public void setUltTurnoCLConfig(Long ultTurnoCLConfig) {
		this.ultTurnoCLConfig = ultTurnoCLConfig;
	}

	public String getDtUltApontConfig() {
		return dtUltApontConfig;
	}

	public void setDtUltApontConfig(String dtUltApontConfig) {
		this.dtUltApontConfig = dtUltApontConfig;
	}

	public Long getOsConfig() {
		return osConfig;
	}

	public void setOsConfig(Long osConfig) {
		this.osConfig = osConfig;
	}

	public Double getHorimetroConfig() {
		return horimetroConfig;
	}

	public void setHorimetroConfig(Double horimetroConfig) {
		this.horimetroConfig = horimetroConfig;
	}

}