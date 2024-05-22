package Exceptions;

public class ElementNotFoundException extends Exception{
    @Override
    public String getMessage(){
        return "cet element n'existe Pas";
    }
}
