package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // vai abrir em qqr porta que estiver disponivel no computadr
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // classe feita para fazer testes de instancia
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
	    LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    
	    Usuario usuario = new Usuario(0, "Lucas", "LuquinhasDevJr@email.com.br", "134652789", data);
	    if(usuarioRepository.findByUsuario(usuario.getUsuario()).isEmpty())
	    	usuarioRepository.save(usuario);
	    
	    usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "987654321", data);
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isEmpty())
			usuarioRepository.save(usuario);

        usuario = new Usuario(0, "Paulo Antunes da Silva", "paulo@email.com.br", "147258369", data);
        if(usuarioRepository.findByUsuario(usuario.getUsuario()).isEmpty())
            usuarioRepository.save(usuario);
	    
	}
	@Test
	@DisplayName("Retorna o nome")
	public void findBynomeRetornaNome() {
		
		Usuario usuario = usuarioRepository.findByNome("Lucas");
		assertTrue(usuario.getNome().equals("Lucas"));
	}
	@Test
	@DisplayName("Retorna 2 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(2, listaDeUsuarios.size());
	}

	@AfterAll
	public void end() {
		
		System.out.println("Teste Finalizado!");
		
	}

}
