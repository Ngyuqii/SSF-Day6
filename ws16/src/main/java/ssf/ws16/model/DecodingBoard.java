package ssf.ws16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

/*
"decoding_board": {
    "total_count": 1
}
*/

public class DecodingBoard implements Serializable {
    private int total_count;

    //Getter and Setter
    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    //Create json object - Decoding board
    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                .add("total_count", this.getTotal_count());
    }

    //Return decoding board object from json object
    public static DecodingBoard createFromJson(JsonObject o) {
        DecodingBoard d = new DecodingBoard();
        JsonNumber totalCnt = o.getJsonNumber("total_count");
        System.out.println(totalCnt);
        d.total_count = totalCnt.intValue();
        return d;
    }

}