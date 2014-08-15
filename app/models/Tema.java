package models;

public enum Tema {

	ARDUINO(0, "Arduino"), PROGRAMACAO(1, "Programação"),DESAFIOS(2, "Desafios"), 
	WEB(3, "Web"), ELETRONICA(4, "Eletrônica");

	private final Integer tipo;

	private final String descricao;

	private Tema(Integer tipo, String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
