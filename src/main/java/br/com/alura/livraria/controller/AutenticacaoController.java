package br.com.alura.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.LoginFormDTO;
import br.com.alura.livraria.dto.TokenDTO;
import br.com.alura.livraria.infra.security.AutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AutenticacaoService service;

	@PostMapping
	public TokenDTO autenticar(@RequestBody @Valid LoginFormDTO dto) {
		return new TokenDTO(service.autenticar(dto));

	}

}
