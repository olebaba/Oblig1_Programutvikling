package program.Exceptions;

import java.io.IOException;

public class InvalidAgeException extends IOException {
        public InvalidAgeException(String msg) {
            super(msg);
        }

}
