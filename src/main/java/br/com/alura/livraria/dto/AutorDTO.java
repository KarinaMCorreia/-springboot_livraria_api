package br.com.alura.livraria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDTO {

	private Long id;
	private String nome;
	private String email;
	private String miniCurriculo;
}
