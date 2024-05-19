package Exceptions;

public class AllInputsShouldBeProvidedException  extends  Exception{
    @Override
    public String getMessage(){
        return "Veuillez entrer tout les champs";
    }
}

