package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroFormDto {

	@NotEmpty
	@Size(min = 10)
	private String titulo;

	@PastOrPresent
	private LocalDate dataLancamento;

	@DecimalMin("100")
	private Integer numeroDePaginas;

	@JsonAlias("autor_id")
	private Long autorID;

}
