package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "livros")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	private LocalDate dataLancamento;
	private Integer numeroDePaginas;

	@ManyToOne
	private Autor autor;

	public void atualizarInformacoes(String titulo, LocalDate lancamento, int numeroDePaginas) {
		this.titulo = titulo;
		this.dataLancamento = lancamento;
		this.numeroDePaginas = numeroDePaginas;
		
	}

	public Livro(String titulo, LocalDate lancamento, Integer numeroDePaginas, Autor autor) {
		super();
		this.titulo = titulo;
		this.dataLancamento = lancamento;
		this.numeroDePaginas = numeroDePaginas;
		this.autor = autor;
	}
	
}
