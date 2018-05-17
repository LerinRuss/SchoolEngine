package package1.exception;

import java.io.IOException;

public class LoaderTexturesException extends IOException{
    public LoaderTexturesException(String message){
        super(message);
    }

    public LoaderTexturesException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
