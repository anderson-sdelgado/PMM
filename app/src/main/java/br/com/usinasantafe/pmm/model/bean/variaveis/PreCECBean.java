package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbprececvar")
public class PreCECBean extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idPreCEC;
	@DatabaseField
	private Long ativOS;
	@DatabaseField
	private Long cam;
	@DatabaseField
    private Long libCam;
	@DatabaseField
    private Long moto;
	@DatabaseField
    private Long carr1;
	@DatabaseField
    private Long libCarr1;
	@DatabaseField
    private Long carr2;
	@DatabaseField
    private Long libCarr2;
	@DatabaseField
    private Long carr3;
	@DatabaseField
    private Long libCarr3;
	@DatabaseField
	private Long carr4;
	@DatabaseField
	private Long libCarr4;
	@DatabaseField
    private String dataChegCampo;
	@DatabaseField
    private String dataSaidaCampo;
	@DatabaseField
    private String dataSaidaUsina;
	@DatabaseField
    private Long turno;
	@DatabaseField
	private Long status; //1 - Aberto; 2 - Fechado; 3 - Enviado
    
    public PreCECBean(){
    }

	public Long getIdPreCEC() {
		return idPreCEC;
	}

	public Long getCam() {
		return cam;
	}

	public void setCam(Long cam) {
		this.cam = cam;
	}

	public Long getLibCam() {
		return libCam;
	}

	public void setLibCam(Long libcam) {
		this.libCam = libcam;
	}

	public Long getMoto() {
		return moto;
	}

	public void setMoto(Long moto) {
		this.moto = moto;
	}

	public Long getCarr1() {
		return carr1;
	}

	public void setCarr1(Long carr1) {
		this.carr1 = carr1;
	}

	public Long getLibCarr1() {
		return libCarr1;
	}

	public void setLibCarr1(Long libcarr1) {
		this.libCarr1 = libcarr1;
	}

	public Long getCarr2() {
		return carr2;
	}

	public void setCarr2(Long carr2) {
		this.carr2 = carr2;
	}

	public Long getLibCarr2() {
		return libCarr2;
	}

	public void setLibCarr2(Long libcarr2) {
		this.libCarr2 = libcarr2;
	}

	public Long getCarr3() {
		return carr3;
	}

	public void setCarr3(Long carr3) {
		this.carr3 = carr3;
	}

	public Long getLibCarr3() {
		return libCarr3;
	}

	public void setLibCarr3(Long libcarr3) {
		this.libCarr3 = libcarr3;
	}

	public Long getCarr4() {
		return carr4;
	}

	public void setCarr4(Long carr4) {
		this.carr4 = carr4;
	}

	public Long getLibCarr4() {
		return libCarr4;
	}

	public void setLibCarr4(Long libCarr4) {
		this.libCarr4 = libCarr4;
	}

	public String getDataChegCampo() {
		return dataChegCampo;
	}

	public void setDataChegCampo(String dataccamp) {
		this.dataChegCampo = dataccamp;
	}

	public String getDataSaidaCampo() {
		return dataSaidaCampo;
	}

	public void setDataSaidaCampo(String datascamp) {
		this.dataSaidaCampo = datascamp;
	}

	public String getDataSaidaUsina() {
		return dataSaidaUsina;
	}

	public void setDataSaidaUsina(String datasusina) {
		this.dataSaidaUsina = datasusina;
	}

	public Long getTurno() {
		return turno;
	}

	public void setTurno(Long turno) {
		this.turno = turno;
	}

	public Long getAtivOS() {
		return ativOS;
	}

	public void setAtivOS(Long ativOS) {
		this.ativOS = ativOS;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

}
