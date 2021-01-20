package br.com.usinasantafe.pmm.model.bean.variaveis;

import br.com.usinasantafe.pmm.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {
	
	private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
	@DatabaseField
	private Long equipConfig;
    @DatabaseField
	private String senhaConfig;
	@DatabaseField
	private Long ultTurnoCLConfig;
    @DatabaseField
    private String dtUltCLConfig;
	@DatabaseField
	private String dtUltApontConfig;
	@DatabaseField
	private String dtServConfig;
	@DatabaseField
	private Long difDthrConfig;
	@DatabaseField
	private Long osConfig;
	@DatabaseField
	private Long ativConfig;
	@DatabaseField
	private Double horimetroConfig;
	@DatabaseField
	private Long verInforConfig; //0 - Verificar Dados; 1- Dados Recebidos; 2 - Dados Visualizados
	@DatabaseField
	private Long statusConConfig;  //0 - Offline; 1 - Online
	@DatabaseField
	private Long flagLogEnvio;
	@DatabaseField
	private Long flagLogErro;
	@DatabaseField
	private Long atualCheckList;

	public ConfigBean() {
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

    public String getDtUltCLConfig() {
        return dtUltCLConfig;
    }

    public void setDtUltCLConfig(String dtUltCLConfig) {
        this.dtUltCLConfig = dtUltCLConfig;
    }

	public Long getVerInforConfig() {
		return verInforConfig;
	}

	public void setVerInforConfig(Long visDadosConfig) {
		this.verInforConfig = visDadosConfig;
	}

	public Long getStatusConConfig() {
		return statusConConfig;
	}

	public void setStatusConConfig(Long statusConConfig) {
		this.statusConConfig = statusConConfig;
	}

	public String getDtServConfig() {
		return dtServConfig;
	}

	public void setDtServConfig(String dtServConfig) {
		this.dtServConfig = dtServConfig;
	}

	public Long getDifDthrConfig() {
		return difDthrConfig;
	}

	public void setDifDthrConfig(Long difDthrConfig) {
		this.difDthrConfig = difDthrConfig;
	}

	public Long getAtivConfig() {
		return ativConfig;
	}

	public void setAtivConfig(Long ativConfig) {
		this.ativConfig = ativConfig;
	}

	public Long getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(Long idConfig) {
		this.idConfig = idConfig;
	}

	public Long getFlagLogEnvio() {
		return flagLogEnvio;
	}

	public void setFlagLogEnvio(Long flagLogEnvio) {
		this.flagLogEnvio = flagLogEnvio;
	}

	public Long getFlagLogErro() {
		return flagLogErro;
	}

	public void setFlagLogErro(Long flagLogErro) {
		this.flagLogErro = flagLogErro;
	}

	public Long getAtualCheckList() {
		return atualCheckList;
	}

	public void setAtualCheckList(Long atualCheckList) {
		this.atualCheckList = atualCheckList;
	}
}