package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorFormDTO {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@Past
	private LocalDate dataNascimento;
	
	@NotBlank
	private String miniCurriculo;
}
