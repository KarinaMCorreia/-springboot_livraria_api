package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFormDto {

	@NotEmpty
	private String nome;

	@NotEmpty
	private String email;
	
	@PastOrPresent
	private LocalDate dataNascimento;

	@NotEmpty
	private String curriculo;

}
