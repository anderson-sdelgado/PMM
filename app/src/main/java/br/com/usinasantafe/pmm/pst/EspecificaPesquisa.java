package br.com.usinasantafe.pmm.pst;

public class EspecificaPesquisa {

	private String campo;
	private Object valor;
	private int tipo;
	
	public EspecificaPesquisa() {
		// TODO Auto-generated constructor stub
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
