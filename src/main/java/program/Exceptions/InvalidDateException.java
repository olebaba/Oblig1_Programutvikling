package program.Exceptions;

import java.io.IOException;

public class InvalidDateException extends IOException {
        public InvalidDateException(String msg) {
            super(msg);
        }
}
