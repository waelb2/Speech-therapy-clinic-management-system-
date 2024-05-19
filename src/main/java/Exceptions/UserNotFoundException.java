package Exceptions;

public class UserNotFoundException extends Exception{

    private  String email ;
    public UserNotFoundException(String email){
       this.email = email ;
    }
   @Override
   public String getMessage(){
       return "L'email : " + email + " n'existe pas";
   }
}
