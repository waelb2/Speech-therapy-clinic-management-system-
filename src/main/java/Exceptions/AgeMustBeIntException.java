package Exceptions;

public class AgeMustBeIntException  extends  Exception{
    @Override
    public String getMessage(){
        return "L'age doit être un entier.";
    }
}
