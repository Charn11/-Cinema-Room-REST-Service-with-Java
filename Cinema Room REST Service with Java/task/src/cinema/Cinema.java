package cinema;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Cinema {
    final private int totalRows = 9;
    final private int totalColumns = 9;
    //The full GET Request
    private Map<String, Object> seats;
    //For GET requests "seats" property
    private List<Object> s;
    private List<Seat> actualSeats;
    private List<Booked> bookedSeats;
    private Stats stats;

    public Cinema(){
        seats = new HashMap<String,Object>();
        actualSeats = new ArrayList<Seat>();
        bookedSeats = new ArrayList<>();
        seats.put("rows",totalRows);
        seats.put("columns",totalColumns);
        s = new ArrayList<Object>();
        for(int i=1; i<=totalRows; i++){
            for(int j=1; j<=totalColumns; j++){
                actualSeats.add(new Seat(i,j));
                s.add(new Seat(i,j).getSeat());
            }
        }
        seats.put("seats",s);
        this.stats = new Stats();
    }

    public Map<String, Object> getSeats() {
        return seats;
    }

    public void setS(List<Object> s) {
        this.s = s;
    }

    public Object getS(int row, int column) {
        for(int i=0; i<actualSeats.size(); i++){
            if(actualSeats.get(i).getRow()==row&&actualSeats.get(i).getColumn()==column&&
                    !actualSeats.get(i).isBooked()){
                actualSeats.get(i).setBooked(true);
                UUID uuid = UUID.randomUUID();
                Booked booked = new Booked(s.get(i), uuid, row, column);
                bookedSeats.add(booked);
                this.stats.setIncome(this.stats.getIncome()+actualSeats.get(i).getPrice());
                this.stats.setAvailable(this.stats.getAvailable()-1);
                this.stats.setPurchased(this.stats.getPurchased()+1);
                return booked;
            } else if (actualSeats.get(i).getRow()==row&&actualSeats.get(i).getColumn()==column&&
            actualSeats.get(i).isBooked()) {
                throw new AlreadyBookedException("The ticket has been already purchased!");
            }
        }
        throw new AlreadyBookedException("The number of a row or a column is out of bounds!");
    }

    public List<Booked> getBookedSeats() {
        return bookedSeats;
    }

    public Object updateBooked(UUID returnJson){
        int j = 0;
        for(int i=0; i<bookedSeats.size(); i++){
            if(bookedSeats.get(i).getUuid().equals(returnJson)){
                for(int k=0; k<actualSeats.size(); k++){
                    if(bookedSeats.get(i).getRow()==actualSeats.get(k).getRow()&&
                    bookedSeats.get(i).getColumn()==actualSeats.get(k).getColumn()){
                        j = i;
                        actualSeats.get(k).setBooked(false);
                        this.stats.setIncome(this.stats.getIncome()-actualSeats.get(k).getPrice());
                        this.stats.setAvailable(this.stats.getAvailable()+1);
                        this.stats.setPurchased(this.stats.getPurchased()-1);
                    }
                }
                Map<String, Object> returnResp = new HashMap<>();
                returnResp.put("ticket",bookedSeats.get(i).getTicket());
                bookedSeats.remove(j);
                return returnResp;
            }
        }

        throw new TicketNotFoundException("Wrong token!");
    }

    public Stats getStats() {
        return stats;
    }
}