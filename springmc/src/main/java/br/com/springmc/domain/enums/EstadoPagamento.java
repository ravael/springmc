package br.com.springmc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1,"Pagamento pedente"),
	QUITADO(2,"Pagamento quitado"),
	CANCELADO(3, "Pagamento cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for (EstadoPagamento tipo : EstadoPagamento.values()) {
			if(codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("Codigo inválido");
		
	}
	
}
