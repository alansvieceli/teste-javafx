package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = -2241183485351217627L;
	
	private Map<String, String > erros = new HashMap<>();
	
	public ValidationException(String msg) {
		
	}

	public Map<String, String> getErros() {
		return erros;
	}
	
	public void addErro(String key, String msg) {
		erros.put(key, msg);		
	}



}
