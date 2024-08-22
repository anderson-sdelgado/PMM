package br.com.usinasantafe.cmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.cmm.model.pst.Entidade;

@DatabaseTable(tableName="tblocalcarregvar")
public class LocalCarregBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idLocalCarreg;
    @DatabaseField
    private String descrOS;
    @DatabaseField
    private String descrLiberacao;
    @DatabaseField
    private String codFrente;
    @DatabaseField
    private String codPropriedade;
    @DatabaseField
    private String descrPropriedade;
    @DatabaseField
    private String descrCaminho;

    public LocalCarregBean() {
    }

    public Long getIdLocalCarreg() {
        return idLocalCarreg;
    }

    public void setIdLocalCarreg(Long idLocalCarreg) {
        this.idLocalCarreg = idLocalCarreg;
    }

    public String getCodFrente() {
        return codFrente;
    }

    public void setCodFrente(String codFrente) {
        this.codFrente = codFrente;
    }

    public String getCodPropriedade() {
        return codPropriedade;
    }

    public void setCodPropriedade(String codPropriedade) {
        this.codPropriedade = codPropriedade;
    }

    public String getDescrPropriedade() {
        return descrPropriedade;
    }

    public void setDescrPropriedade(String descrPropriedade) {
        this.descrPropriedade = descrPropriedade;
    }

    public String getDescrCaminho() {
        return descrCaminho;
    }

    public void setDescrCaminho(String descrCaminho) {
        this.descrCaminho = descrCaminho;
    }

    public String getDescrOS() {
        return descrOS;
    }

    public void setDescrOS(String descrOS) {
        this.descrOS = descrOS;
    }

    public String getDescrLiberacao() {
        return descrLiberacao;
    }

    public void setDescrLiberacao(String descrLiberacao) {
        this.descrLiberacao = descrLiberacao;
    }
}
