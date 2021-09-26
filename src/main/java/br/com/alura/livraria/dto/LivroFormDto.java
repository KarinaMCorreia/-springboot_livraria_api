package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class LivroFormDto {
	
	
	@NotEmpty
	@Size(min = 10)
	private String titulo;             //Título deve ser obrigatório e ter no mínimo 10 caracteres;

	@PastOrPresent
	private LocalDate dataLancamento;  //Data de lançamento deve ser uma data menor ou igual a data atual;

	@DecimalMin("100")
	private int numeroDePaginas;       //O número de páginas deve ser maior ou igual a 100.
	
	private AutorDto autorDto;
	

}
