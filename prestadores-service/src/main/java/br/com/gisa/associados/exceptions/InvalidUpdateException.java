package br.com.gisa.associados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidUpdateException extends Exception {

	private static final long serialVersionUID = -6443362632195638948L;

	public InvalidUpdateException(){
		super();
	}

	public InvalidUpdateException(String msg){
		super(msg);
	}

}
