package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.LivroDTO;
import br.com.alura.livraria.dto.LivroFormDTO;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;
import br.com.alura.livraria.service.LivroService;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

	@Mock
	private AutorRepository autorRepository;

	@Mock
	private LivroRepository livroRepository;

	@InjectMocks
	private LivroService livroService;

	@Test
	void deveriaCadastrarUmLivro() {
		LivroFormDTO formDTO = new LivroFormDTO("LivroTec", LocalDate.of(2021, 11, 11), 100, 1l

		);

		LivroDTO dto = livroService.cadastrar(formDTO);

		Mockito.verify(livroRepository).save(Mockito.any());

		assertEquals(formDTO.getTitulo(), dto.getTitulo());
		assertEquals(formDTO.getDataLancamento(), dto.getDataLancamento());
		assertEquals(formDTO.getNumeroDePaginas(), dto.getNumeroDePaginas());

	}

	@Test
	void naoDeveriaCadastrarUmLivroComAutorInvalido() {
		LivroFormDTO formDTO = new LivroFormDTO("LivroTec", LocalDate.of(2021, 11, 11), 100, 99999l

				);

		Mockito.when(autorRepository.getById(formDTO.getAutorId())).thenThrow(EntityNotFoundException.class);

		assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(formDTO));
	}

}
