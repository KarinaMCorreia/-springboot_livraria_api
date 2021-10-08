package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFormDto {

	private Long id;
	@NotBlank
	@Size(min = 10)
	private String nome;
	@NotBlank
	private String email;
	@NotBlank
	private LocalDate dataNascimento;
	@DecimalMin("100")
	private String miniCurriculo;

}
