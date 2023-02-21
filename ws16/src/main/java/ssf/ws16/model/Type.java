package ssf.ws16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

/*
"types": [
    {
        "type": "code",
        "count": 72
    },
    {
        "type": "key",
        "count": 30
    }
]
*/

public class Type implements Serializable {
    private String type;
    private int count;

    //Getters
    public String getType() {
        return type;
    }
    public int getCount() {
        return count;
    }

    //Setters
    public void setType(String type) {
        this.type = type;
    }
    public void setCount(int count) {
        this.count = count;
    }

    //Create json object - Type array elements
    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                .add("type", this.getType())
                .add("count", this.getCount());
    }

    //Return type array element object from json object
    public static Type createFromJson(JsonObject o) {
        Type t = new Type();
        JsonNumber count = o.getJsonNumber("count");
        String type = o.getString("type");
        t.count = count.intValue();
        t.type = type;
        return t;
    }

}