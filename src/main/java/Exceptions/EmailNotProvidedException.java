package Exceptions;

public class EmailNotProvidedException extends Exception {
    @Override
    public String getMessage(){
        return "Vous devez inséer votre email.";
    }
}
