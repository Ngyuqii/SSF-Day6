package ssf.ws16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

/*
"pieces": {
    "decoding_board": { },
    "pegs": { },
    "rulebook": { }
}
*/

public class Pieces implements Serializable {
    private DecodingBoard decoding_board;
    private Pegs pegs;
    private Rulebook rulebook;

    //Getters
    public DecodingBoard getDecoding_board() {
        return decoding_board;
    }
    public Pegs getPegs() {
        return pegs;
    }
    public Rulebook getRulebook() {
        return rulebook;
    }

    //Setters
    public void setDecoding_board(DecodingBoard decoding_board) {
        this.decoding_board = decoding_board;
    }
    public void setPegs(Pegs pegs) {
        this.pegs = pegs;
    }
    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    //Create json object - Pieces
    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                .add("decoding_board", this.getDecoding_board().toJSON())
                .add("pegs", this.getPegs().toJSON())
                .add("rulebook", this.getRulebook().toJSON());
    }

    //Return pieces object from json object
    public static Pieces createFromJson(JsonObject o) {
        Pieces p = new Pieces();
        JsonObject decodingBoard = o.getJsonObject("decoding_board");
        JsonObject pegs = o.getJsonObject("pegs");
        JsonObject rulebook = o.getJsonObject("rulebook");
        p.decoding_board = DecodingBoard.createFromJson(decodingBoard);
        p.pegs = Pegs.createFromJson(pegs);
        p.rulebook = Rulebook.createFromJson(rulebook);
        return p;
    }

}