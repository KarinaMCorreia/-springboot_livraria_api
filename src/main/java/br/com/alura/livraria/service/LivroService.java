package br.com.alura.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AtualizacaoLivroFormDTO;
import br.com.alura.livraria.dto.LivroDTO;
import br.com.alura.livraria.dto.LivroFormDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public Page<LivroDTO> listar(Pageable paginacao) {
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(t-> modelMapper.map(t, LivroDTO.class));
		
	}

	@Transactional
	public LivroDTO cadastrar(LivroFormDTO dto) {
		try {
			Livro livro = modelMapper.map(dto, Livro.class);
			Autor autor = autorRepository.getById(dto.getAutorId());
			
			livro.setId(null);
			livro.setAutor(autor);
			
			livroRepository.save(livro);
			return modelMapper.map(livro, LivroDTO.class);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Autor inexistente!");
		}

	}

	@Transactional
	public LivroDTO atualizar(@Valid AtualizacaoLivroFormDTO dto) {
		Livro livro = livroRepository.getById(dto.getId());
		
		livro.atualizarInformacoes(
				dto.getTitulo(),
				dto.getDataLancamento(),
				dto.getNumeroDePaginas()
				);
		return modelMapper.map(livro, LivroDTO.class);
	}

	@Transactional
	public void remover(@NotNull Long id) {
		livroRepository.deleteById(id);
		
	}

	public LivroDTO detalhar(@NotNull Long id) {
		Livro livro = livroRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(livro, LivroDTO.class);
	}

}
