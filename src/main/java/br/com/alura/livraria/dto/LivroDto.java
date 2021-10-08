package br.com.alura.livraria.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {

	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private Integer numeroDePaginas;
	private AutorDto autor;

}
