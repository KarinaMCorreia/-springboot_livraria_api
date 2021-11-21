package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.jayway.jsonpath.JsonPath;

import br.com.alura.livraria.infra.security.TokenService;
import br.com.alura.livraria.modelo.Perfil;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.repository.PerfilRepository;
import br.com.alura.livraria.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class RelatoriosControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private String token;

	@BeforeEach
	public void gerarToken() {
		Usuario logado = new Usuario("Karina", "karina", "123456","email@email.com");
		Perfil admin = perfilRepository.findById(1l).get();
		logado.adicionarPerfil(admin);
		usuarioRepository.save(logado);
		Authentication authentication = new UsernamePasswordAuthenticationToken(logado, logado.getLogin());
		this.token = tokenService.gerarToken(authentication);
	}

	@Test
	void deveriaRetornarRelatorioDeLivros() throws Exception {
		String jsonAutor = "{\"nome\":\"karina mendes\"," + "\"email\":\"karina@gmail.com.com\","
				+ "\"dataNascimento\":\"1990-01-01\",\"miniCurriculo\":\"livros do tec\"}";

		String jsonAutorRetornado = "{\"nome\":\"karina mendes\",\"email\":\"karina@gmail.com.com\",\"miniCurriculo\":\"livros do tec\"}";

		MvcResult resultado = mvc
				.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(jsonAutor)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(content().json(jsonAutorRetornado)).andReturn();

		Integer id = JsonPath.read(resultado.getResponse().getContentAsString(), "$.id");

		String jsonLivro = "{\"titulo\":\"Livros do tec\",\"dataLancamento\":\"2020-01-01\",\"numeroDePaginas\":100,\"autor_id\":"
				+ id + "}";

		mvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content(jsonLivro).header("Authorization",
				"Bearer " + token));

		String json = "[{\"autor\":\"karina mendes\",\"quantidade\":1,\"percentual\":100.0}]";

		mvc.perform(get("/relatorios/livraria").header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(content().json(json));

	}

}
