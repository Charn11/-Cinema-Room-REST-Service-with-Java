package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaController {
    @Autowired
    Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public ResponseEntity<?> getCinemaSeats(){
        return ResponseEntity.ok().body(cinema.getSeats());
    }

    @PostMapping(value="/purchase")
    public ResponseEntity<?> makePurchase(@RequestBody Map<String, Integer> json){
        Object response = cinema.getS(json.get("row"),json.get("column"));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, UUID> returnJson){
        Object response = cinema.updateBooked(returnJson.get("token"));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<?> movieStats(@RequestParam(required = false) String password){
        if(password==null){
            throw new WrongPasswordException("The password is wrong!");
        }
        if(password.equals("super_secret")){
            Object response = cinema.getStats();
            return ResponseEntity.ok().body(response);
        }

        throw new WrongPasswordException("The password is wrong!");
    }
}
