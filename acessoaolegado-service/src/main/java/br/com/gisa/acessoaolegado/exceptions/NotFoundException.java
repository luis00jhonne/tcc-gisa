package br.com.gisa.acessoaolegado.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    private static final long serialVersionUID = -2586209354700102349L;

    public NotFoundException(){
        super();
    }

    public NotFoundException(String msg){
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
