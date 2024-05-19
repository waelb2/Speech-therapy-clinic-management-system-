package Exceptions;

public class PasswordNotProvidedException extends Exception {
    @Override
    public String getMessage(){
        return "Vous devez inséer votre mot de passe.";
    }
}
