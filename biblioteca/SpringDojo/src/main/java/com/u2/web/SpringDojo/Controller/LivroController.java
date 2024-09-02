package com.u2.web.SpringDojo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import com.u2.web.SpringDojo.model.LivroModel;
import com.u2.web.SpringDojo.negocio.LivroRN;
import com.u2.web.repositories.LivroRepository;

@RestController
@RequestMapping(value= "/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository repository;
	
	
	
	@GetMapping
	public List<LivroModel> findAll(){
			List<LivroModel> result = repository.findAll();
			return result;
	}
	
	LivroRN livroRN = new LivroRN();
	private com.u2.web.SpringDojo.Controller.HttpServletRequest request;
	
	
	@GetMapping({"/homeLivro"})
	public String abreCadastro() {
		return "Cadastro";
	}
	
	
	@PostMapping({"/cadastrar"})
	public String cadastrarLivro(AbstractMultipartHttpServletRequest request,
			@RequestParam(value="titulo") String titulo,
			@RequestParam(value="editora") String editora,
			@RequestParam(value="autor") String autor,
			@RequestParam(value="id") Long id){
		
		this.request = request;
		/* Recuperar os valores dos campos do formulário
		 * e preencher os atributos do model da entidade selecionada
		 */
		LivroModel modelLivro = new LivroModel();
		String mensagem = "";
		boolean cadastrouLivro = false;
		
		if(!titulo.equals("")) {
			modelLivro.setTitulo(titulo);
		} else {
			mensagem = "Título não pode ficar em branco.\n";
		}
		
		if(!editora.equals("")) {
			modelLivro.setEditora(editora);
		} else {
			mensagem += "Editora não pode ficar em branco.";
		}
		
		if(!autor.equals("")) {
			modelLivro.setAutor(autor);
		} else {
			mensagem += "Autor não pode ficar em branco.";
		}
		
		if(!id.equals("")) {
			modelLivro.setId(id);
		} else {
			mensagem += "ISBN não pode ficar em branco.";
		}
		
		
		if(mensagem.equals("")) {
			//Tudo ok, então realizar o cadastro da entidade
			
			cadastrouLivro = livroRN.cadastrarLivro(modelLivro);
			
			if(!cadastrouLivro) {
				mensagem = "Livro já existente na base.";
			}
		} 		
			
		if(!mensagem.equals("")) {
			request.getSession().setAttribute("mensagem", mensagem);
		}
		
		return "Cadastro";
	}
	

}
