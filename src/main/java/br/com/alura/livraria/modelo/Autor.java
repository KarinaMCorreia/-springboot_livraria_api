package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Autor {

	
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private String curriculo;

}
