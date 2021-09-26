package br.com.alura.livraria.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDto {
	
	
	private String nome;
	private String email;
	private LocalDate dataNascimento;

}
