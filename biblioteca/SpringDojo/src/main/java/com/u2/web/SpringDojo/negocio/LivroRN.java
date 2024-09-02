package com.u2.web.SpringDojo.negocio;

import java.util.HashMap;

import com.u2.web.SpringDojo.model.LivroModel;

public class LivroRN {

	HashMap<String, LivroModel> hashLivro = new HashMap<>();
	
	
	public boolean cadastrarLivro(LivroModel model) {
		
		//Fazer o cadastramento através do título
		if(!hashLivro.containsKey(model.getTitulo())) {
			hashLivro.put(model.getTitulo(), model);
		} else {
			return false;
		}
		
		return true;
	}
	
}
