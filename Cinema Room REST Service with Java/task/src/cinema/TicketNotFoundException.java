package cinema;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message){
        super(message);
    }
}
