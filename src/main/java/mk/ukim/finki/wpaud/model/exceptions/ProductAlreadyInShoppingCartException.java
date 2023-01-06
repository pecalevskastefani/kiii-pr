package mk.ukim.finki.wpaud.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException {


    public ProductAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Product with id: %d already exists in shopping cart for user in username %s", id, username));
    }
}
