package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;


@DatabaseTable(tableName="tbcecvar")
public class CECBean extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idCEC;
	@DatabaseField
    private Long idCECBD;
	@DatabaseField
    private Long caminhaoCEC;
	@DatabaseField
    private Long possuiSorteioCEC;
	@DatabaseField
    private Long cecPaiCEC;
	@DatabaseField
    private Long codFrenteCEC;
	@DatabaseField
    private String dthrEntradaCEC;
	@DatabaseField
    private Long cecSorteado1CEC;
	@DatabaseField
    private Long unidadeSorteada1CEC;
	@DatabaseField
    private Long cecSorteado2CEC;
	@DatabaseField
    private Long unidadeSorteada2CEC;
	@DatabaseField
    private Long cecSorteado3CEC;
	@DatabaseField
    private Long unidadeSorteada3CEC;
	@DatabaseField
	private Long cecSorteado4CEC;
	@DatabaseField
	private Long unidadeSorteada4CEC;
	@DatabaseField
    private Double pesoLiquidoCEC;
	
	public CECBean() {
	}

	public Long getIdCEC() {
		return idCEC;
	}

	public void setIdCEC(Long idCEC) {
		this.idCEC = idCEC;
	}

	public Long getIdCECBD() {
		return idCECBD;
	}

	public void setIdCECBD(Long idCECBD) {
		this.idCECBD = idCECBD;
	}

	public Long getCaminhaoCEC() {
		return caminhaoCEC;
	}

	public void setCaminhaoCEC(Long caminhaoCEC) {
		this.caminhaoCEC = caminhaoCEC;
	}

	public Long getPossuiSorteioCEC() {
		return possuiSorteioCEC;
	}

	public void setPossuiSorteioCEC(Long possuiSorteioCEC) {
		this.possuiSorteioCEC = possuiSorteioCEC;
	}

	public Long getCecPaiCEC() {
		return cecPaiCEC;
	}

	public void setCecPaiCEC(Long cecPaiCEC) {
		this.cecPaiCEC = cecPaiCEC;
	}

	public Long getCodFrenteCEC() {
		return codFrenteCEC;
	}

	public void setCodFrenteCEC(Long codFrenteCEC) {
		this.codFrenteCEC = codFrenteCEC;
	}

	public String getDthrEntradaCEC() {
		return dthrEntradaCEC;
	}

	public void setDthrEntradaCEC(String dthrEntradaCEC) {
		this.dthrEntradaCEC = dthrEntradaCEC;
	}

	public Long getCecSorteado1CEC() {
		return cecSorteado1CEC;
	}

	public void setCecSorteado1CEC(Long cecSorteado1CEC) {
		this.cecSorteado1CEC = cecSorteado1CEC;
	}

	public Long getUnidadeSorteada1CEC() {
		return unidadeSorteada1CEC;
	}

	public void setUnidadeSorteada1CEC(Long unidadeSorteada1CEC) {
		this.unidadeSorteada1CEC = unidadeSorteada1CEC;
	}

	public Long getCecSorteado2CEC() {
		return cecSorteado2CEC;
	}

	public void setCecSorteado2CEC(Long cecSorteado2CEC) {
		this.cecSorteado2CEC = cecSorteado2CEC;
	}

	public Long getUnidadeSorteada2CEC() {
		return unidadeSorteada2CEC;
	}

	public void setUnidadeSorteada2CEC(Long unidadeSorteada2CEC) {
		this.unidadeSorteada2CEC = unidadeSorteada2CEC;
	}

	public Long getCecSorteado3CEC() {
		return cecSorteado3CEC;
	}

	public void setCecSorteado3CEC(Long cecSorteado3CEC) {
		this.cecSorteado3CEC = cecSorteado3CEC;
	}

	public Long getUnidadeSorteada3CEC() {
		return unidadeSorteada3CEC;
	}

	public void setUnidadeSorteada3CEC(Long unidadeSorteada3CEC) {
		this.unidadeSorteada3CEC = unidadeSorteada3CEC;
	}

	public Double getPesoLiquidoCEC() {
		return pesoLiquidoCEC;
	}

	public void setPesoLiquidoCEC(Double pesoLiquidoCEC) {
		this.pesoLiquidoCEC = pesoLiquidoCEC;
	}

	public Long getCecSorteado4CEC() {
		return cecSorteado4CEC;
	}

	public void setCecSorteado4CEC(Long cecSorteado4CEC) {
		this.cecSorteado4CEC = cecSorteado4CEC;
	}

	public Long getUnidadeSorteada4CEC() {
		return unidadeSorteada4CEC;
	}

	public void setUnidadeSorteada4CEC(Long unidadeSorteada4CEC) {
		this.unidadeSorteada4CEC = unidadeSorteada4CEC;
	}
}
