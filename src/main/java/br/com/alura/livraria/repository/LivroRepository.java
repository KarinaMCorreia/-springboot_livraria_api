package br.com.alura.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.livraria.dto.QuantidadeDeLivrosDto;
import br.com.alura.livraria.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	@Query("select new br.com.alura.livraria.dto.QuantidadeDeLivrosDto(" + "" + "a.autor.nome, " + "count(*), "
			+ "count(*) * 1.0 / (select count(*) from Livro a2) * 1.0 as percentual) "
			+ "from Livro a group by a.autor order by percentual desc")
	List<QuantidadeDeLivrosDto> relatorioQuantidadeDeLivros();

}
