package Exceptions;

public class PatientDoesNotExistException  extends Exception{
    String message;

    public PatientDoesNotExistException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return "Patient n'existe pas pour faire un " + message;
    }
}
