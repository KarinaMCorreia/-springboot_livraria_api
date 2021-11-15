package br.com.alura.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.livraria.dto.QuantidadeDeLivrosDTO;
import br.com.alura.livraria.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	@Query("select new br.com.alura.livraria.dto.QuantidadeDeLivrosDTO("
			+ "a.autor.nome, "
			+ "count(*), "
			+ "round(count(*) * 100.0 / (select count(*) from Livro a2) * 1.0, 2) as percentage)"
			+ " from Livro a "
			+ "group by a.autor")
	List<QuantidadeDeLivrosDTO> relatorioQuantidadeDeLivros();

}
