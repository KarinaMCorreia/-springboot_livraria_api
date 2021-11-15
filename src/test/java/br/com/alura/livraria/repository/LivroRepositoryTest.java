package br.com.alura.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.dto.QuantidadeDeLivrosDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {

	@Autowired
	private LivroRepository repository;

	@Autowired
	private TestEntityManager em;

	@Test
	void deveriaRetornarRelatorioDeLivros() {
		Autor autor01 = new Autor("Karina Mendes", "Karina@gmail.com", LocalDate.of(1994, 4, 15), "Tec01");

		em.persist(autor01);

		Autor autor02 = new Autor("Filipe Olivitti", "filipe@gamil.com", LocalDate.of(1993, 7, 22), "Tec02");

		em.persist(autor02);

		Livro livro01 = new Livro("Tec 01", LocalDate.of(2021, 01, 01), 100, autor01);

		em.persist(livro01);

		Livro livro02 = new Livro("Tec 02", LocalDate.of(2021, 01, 01), 100, autor02);

		em.persist(livro02);

		Livro livro03 = new Livro("Tec 03", LocalDate.of(2021, 01, 01), 100, autor01);

		em.persist(livro03);

		List<QuantidadeDeLivrosDTO> relatorio = repository.relatorioQuantidadeDeLivros();
		Assertions.assertThat(relatorio).hasSize(2)
				.extracting(QuantidadeDeLivrosDTO::getAutor, QuantidadeDeLivrosDTO::getQuantidade,
						QuantidadeDeLivrosDTO::getPercentual)
				.containsExactlyInAnyOrder(Assertions.tuple("Karina Mendes", 2l, 66.67),
						Assertions.tuple("Filipe Olivitti", 1l, 33.33));

	}
}
