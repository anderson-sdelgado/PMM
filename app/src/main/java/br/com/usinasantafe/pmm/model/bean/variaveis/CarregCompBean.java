package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

/**
 * Created by anderson on 16/11/2016.
 */
@DatabaseTable(tableName="tbapontcarregvar")
public class CarregCompBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCarreg;
    @DatabaseField
    private Long idApontCarreg;
    @DatabaseField
    private Long equipCarreg;
    @DatabaseField
    private Long motoCarreg;
    @DatabaseField
    private Long prodCarreg;
    @DatabaseField
    private String dthrCarreg;
    @DatabaseField
    private Long dthrCarregLong;
    @DatabaseField
    private Long tipoCarreg;
                // 1 - INSUMO;
                // 2 - COMPOSTO;
    @DatabaseField
    private Long osCarreg;
    @DatabaseField
    private Long idLeiraCarreg;
    @DatabaseField
    private Long idRegCompostoCarreg;
    @DatabaseField
    private Long idOrdCarreg;
    @DatabaseField
    private Double pesoEntradaCarreg;
    @DatabaseField
    private Double pesoSaidaCarreg;
    @DatabaseField
    private Double pesoLiquidoCarreg;
    @DatabaseField
    private Long statusCarreg;
                // 1 - Envio Carreg de Insumo e Composto com Leira;
                // 2 - Recebe Retorno Envio de Insumo e Composto com Leira;
                // 3 - Recebe Ord Carregamento;
                // 4 - Enviar Leira Descarregamento Composto;
                // 5 - Atual Carreg Composto e Descarregamento Leira;

    public CarregCompBean() {
    }

    public Long getIdCarreg() {
        return idCarreg;
    }

    public void setIdCarreg(Long idCarreg) {
        this.idCarreg = idCarreg;
    }

    public Long getIdApontCarreg() {
        return idApontCarreg;
    }

    public void setIdApontCarreg(Long idApontCarreg) {
        this.idApontCarreg = idApontCarreg;
    }

    public Long getEquipCarreg() {
        return equipCarreg;
    }

    public void setEquipCarreg(Long equipCarreg) {
        this.equipCarreg = equipCarreg;
    }

    public Long getMotoCarreg() {
        return motoCarreg;
    }

    public void setMotoCarreg(Long motoCarreg) {
        this.motoCarreg = motoCarreg;
    }

    public Long getProdCarreg() {
        return prodCarreg;
    }

    public void setProdCarreg(Long prodCarreg) {
        this.prodCarreg = prodCarreg;
    }

    public String getDthrCarreg() {
        return dthrCarreg;
    }

    public void setDthrCarreg(String dthrCarreg) {
        this.dthrCarreg = dthrCarreg;
    }

    public Long getDthrCarregLong() {
        return dthrCarregLong;
    }

    public void setDthrCarregLong(Long dthrCarregLong) {
        this.dthrCarregLong = dthrCarregLong;
    }

    public Long getTipoCarreg() {
        return tipoCarreg;
    }

    public void setTipoCarreg(Long tipoCarreg) {
        this.tipoCarreg = tipoCarreg;
    }

    public Long getOsCarreg() {
        return osCarreg;
    }

    public void setOsCarreg(Long osCarreg) {
        this.osCarreg = osCarreg;
    }

    public Long getIdLeiraCarreg() {
        return idLeiraCarreg;
    }

    public void setIdLeiraCarreg(Long idLeiraCarreg) {
        this.idLeiraCarreg = idLeiraCarreg;
    }

    public Long getIdRegCompostoCarreg() {
        return idRegCompostoCarreg;
    }

    public void setIdRegCompostoCarreg(Long idRegCompostoCarreg) {
        this.idRegCompostoCarreg = idRegCompostoCarreg;
    }

    public Long getIdOrdCarreg() {
        return idOrdCarreg;
    }

    public void setIdOrdCarreg(Long idOrdCarreg) {
        this.idOrdCarreg = idOrdCarreg;
    }

    public Double getPesoEntradaCarreg() {
        return pesoEntradaCarreg;
    }

    public void setPesoEntradaCarreg(Double pesoEntradaCarreg) {
        this.pesoEntradaCarreg = pesoEntradaCarreg;
    }

    public Double getPesoSaidaCarreg() {
        return pesoSaidaCarreg;
    }

    public void setPesoSaidaCarreg(Double pesoSaidaCarreg) {
        this.pesoSaidaCarreg = pesoSaidaCarreg;
    }

    public Double getPesoLiquidoCarreg() {
        return pesoLiquidoCarreg;
    }

    public void setPesoLiquidoCarreg(Double pesoLiquidoCarreg) {
        this.pesoLiquidoCarreg = pesoLiquidoCarreg;
    }

    public Long getStatusCarreg() {
        return statusCarreg;
    }

    public void setStatusCarreg(Long statusCarreg) {
        this.statusCarreg = statusCarreg;
    }

}
