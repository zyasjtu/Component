package org.cora.exception;

import lombok.Getter;
import org.apache.ibatis.binding.BindingException;
import org.springframework.validation.BindingResult;

@Getter
public class ValidatingException extends BindingException{
    private BindingResult bindingResult;

    public ValidatingException(BindingException e, BindingResult bindingResult) {
        super(e);
        this.bindingResult = bindingResult;
    }
}
