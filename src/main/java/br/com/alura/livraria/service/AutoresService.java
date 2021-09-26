package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.modelo.Autor;

@Service
public class AutoresService {

	private List<Autor> autores = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();

	public List<AutorDto> listar() {

		return autores.stream().map(t -> modelMapper.map(t, AutorDto.class)).collect(Collectors.toList());
	}

	public void cadastrar(@Valid AutorFormDto dto) {
		Autor Autor = new ModelMapper().map(dto, Autor.class);

		autores.add(Autor);

	}

}
