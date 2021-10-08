package br.com.alura.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuantidadeDeLivrosDto {
	
	private String autor;
    private Long quantidadeLivros;
    private Double percentual;

}
