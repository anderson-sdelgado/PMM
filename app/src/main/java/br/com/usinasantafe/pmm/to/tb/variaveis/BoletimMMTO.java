package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbboletimvar")
public class BoletimMMTO extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idBoletim;
	@DatabaseField
	private Long idExtBoletim;
	@DatabaseField
    private Long codMotoBoletim;
	@DatabaseField
	private Long codEquipBoletim;
	@DatabaseField
    private Long codTurnoBoletim;
	@DatabaseField
    private Double hodometroInicialBoletim;
	@DatabaseField
	private Double hodometroFinalBoletim;
	@DatabaseField
	private Long osBoletim;
	@DatabaseField
	private Long ativPrincBoletim;
	@DatabaseField
	private String dthrInicioBoletim;
	@DatabaseField
	private String dthrFimBoletim;
	@DatabaseField
	private Long statusBoletim;  //0 - Esta apontando os implementos; 1 - Aberto; 2 - Encerrado

    public BoletimMMTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdBoletim() {
		return idBoletim;
	}

	public Long getIdExtBoletim() {
		return idExtBoletim;
	}

	public void setIdExtBoletim(Long idExtBoletim) {
		this.idExtBoletim = idExtBoletim;
	}

	public Long getCodMotoBoletim() {
		return codMotoBoletim;
	}

	public void setCodMotoBoletim(Long codMotoBoletim) {
		this.codMotoBoletim = codMotoBoletim;
	}

	public Long getCodEquipBoletim() {
		return codEquipBoletim;
	}

	public void setCodEquipBoletim(Long codEquipBoletim) {
		this.codEquipBoletim = codEquipBoletim;
	}

	public Long getCodTurnoBoletim() {
		return codTurnoBoletim;
	}

	public void setCodTurnoBoletim(Long codTurnoBoletim) {
		this.codTurnoBoletim = codTurnoBoletim;
	}

	public Double getHodometroInicialBoletim() {
		return hodometroInicialBoletim;
	}

	public void setHodometroInicialBoletim(Double hodometroInicialBoletim) {
		this.hodometroInicialBoletim = hodometroInicialBoletim;
	}

	public Double getHodometroFinalBoletim() {
		return hodometroFinalBoletim;
	}

	public void setHodometroFinalBoletim(Double hodometroFinalBoletim) {
		this.hodometroFinalBoletim = hodometroFinalBoletim;
	}

	public Long getOsBoletim() {
		return osBoletim;
	}

	public void setOsBoletim(Long osBoletim) {
		this.osBoletim = osBoletim;
	}

	public Long getAtivPrincBoletim() {
		return ativPrincBoletim;
	}

	public void setAtivPrincBoletim(Long ativPrincBoletim) {
		this.ativPrincBoletim = ativPrincBoletim;
	}

	public String getDthrInicioBoletim() {
		return dthrInicioBoletim;
	}

	public void setDthrInicioBoletim(String dthrInicioBoletim) {
		this.dthrInicioBoletim = dthrInicioBoletim;
	}

	public Long getStatusBoletim() {
		return statusBoletim;
	}

	public void setStatusBoletim(Long statusBoletim) {
		this.statusBoletim = statusBoletim;
	}

	public String getDthrFimBoletim() {
		return dthrFimBoletim;
	}

	public void setDthrFimBoletim(String dthrFimBoletim) {
		this.dthrFimBoletim = dthrFimBoletim;
	}


}
