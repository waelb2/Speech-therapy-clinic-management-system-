package Exceptions;

public class WrongPasswordException extends Exception {
    @Override
    public String getMessage(){
        return "Mot de passe incorrecte";
    }
}
