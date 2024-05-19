package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

public class Booked {

    @JsonIgnore
    private int row;
    @JsonIgnore
    private int column;
    private Object ticket;
    @JsonProperty("token")
    private UUID uuid;

    public Booked(Object ticket, UUID uuid, int row, int column){
        this.ticket = ticket;
        this.uuid = uuid;
        this.row = row;
        this.column = column;
    }

    public Object getTicket() {
        return ticket;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
