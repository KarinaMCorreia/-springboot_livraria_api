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

import br.com.alura.livraria.dto.AtualizacaoAutorFormDTO;
import br.com.alura.livraria.dto.AutorDTO;
import br.com.alura.livraria.dto.AutorFormDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public Page<AutorDTO> listar(Pageable paginacao) {
		Page<Autor> autores = autorRepository.findAll(paginacao); 
		return autores.map(t -> modelMapper.map(t, AutorDTO.class));

	}

	@Transactional
	public AutorDTO cadastrar(AutorFormDTO dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		
		autorRepository.save(autor);
		return modelMapper.map(autor, AutorDTO.class);

	}

	@Transactional
	public AutorDTO atualizar(@Valid AtualizacaoAutorFormDTO dto) {
		Autor autor = autorRepository.getById(dto.getId());
		
		autor.atualizarInformacoes(
				dto.getNome(),
				dto.getEmail(),
				dto.getDataNascimento(),
				dto.getMiniCurriculo());
		
		return modelMapper.map(autor, AutorDTO.class);
	}

	@Transactional
	public void remover(@NotNull Long id) {
		autorRepository.deleteById(id);
		
	}

	public AutorDTO detalhar(@NotNull Long id) {
		Autor autor = autorRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(autor, AutorDTO.class);
	}
}