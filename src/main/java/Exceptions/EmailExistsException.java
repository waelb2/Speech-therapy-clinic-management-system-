package Exceptions;

public class EmailExistsException extends Exception{
    @Override
    public String getMessage(){
        return "Cet email déja existe";
    }
}
