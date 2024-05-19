package Exceptions;

public class PatientHasConsultationException extends  Exception {
    @Override
    public String getMessage(){
        return "Patient avait déja une consultation.";
    }
}
