package modelo;

public class BancoDeDados {

	private int Codigo;
	private String NomeBancoDados;
	private int tipoBanco;

	public String getNomeBancoDados() {
		return NomeBancoDados;
	}

	public void setNomeBancoDados(String nomeBancoDados) {
		NomeBancoDados = nomeBancoDados;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public int getTipoBanco() {
		return tipoBanco;
	}

	public void setTipoBanco(int tipoBanco) {
		this.tipoBanco = tipoBanco;
	}
}