package br.com.ljcm.maonaroda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CPFExistenteException extends Exception{

	private static final long serialVersionUID = 6208890125157318839L;

	public CPFExistenteException(String msg){
		super(msg);
	}

}
