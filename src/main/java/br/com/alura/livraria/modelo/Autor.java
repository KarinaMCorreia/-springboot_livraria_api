package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private String miniCurriculo;
	
	
	public void atualizarInformacoes(String nome, String email, LocalDate nascimento, String miniCurriculo) {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = nascimento;
		this.miniCurriculo = miniCurriculo;
	}

	public Autor(String nome, String email, LocalDate nascimento, String miniCurriculo) {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = nascimento;
		this.miniCurriculo = miniCurriculo;
	}
	
}
