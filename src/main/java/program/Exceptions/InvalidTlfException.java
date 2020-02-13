package program.Exceptions;

import java.io.IOException;

public class InvalidTlfException extends IOException {
    public InvalidTlfException(String msg) {
        super(msg);
    }
}


