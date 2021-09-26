package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Livro {
	

	private String titulo;
	private LocalDate dataLancamento;
	private int numeroDePaginas;
	private Autor autor;

}
