package cinema;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

public class Seat {

    private int row;
    private int column;
    private int price;
    private boolean booked = false;
    private Map<String, Integer> seat;

    public Seat(int row, int column){
        this.row = row;
        this.column = column;
        if(row<=4){
            this.price = 10;
        }else {
            this.price = 8;
        }
        seat = new HashMap<>();
        seat.put("row",row);
        seat.put("column",column);
        seat.put("price",this.price);
    }

    public Map<String, Integer> getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean flag) {
        this.booked = flag;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
