package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.AutorDTO;
import br.com.alura.livraria.dto.AutorFormDTO;
import br.com.alura.livraria.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
	private AutorRepository autorRepository;

	@InjectMocks
	private AutorService autorService;

	@Test
	void deveriaCadastrarUmAutor() {
		AutorFormDTO formDTO = new AutorFormDTO("Karina Mendes", "Karina@gmail.com", LocalDate.of(1994, 4, 15),
				"Livros Tec");

		AutorDTO dto = autorService.cadastrar(formDTO);

		Mockito.verify(autorRepository).save(Mockito.any());

		assertEquals(formDTO.getNome(), dto.getNome());
		assertEquals(formDTO.getEmail(), dto.getEmail());
		assertEquals(formDTO.getMiniCurriculo(), dto.getMiniCurriculo());

	}

}
