package ssf.ws16.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

/*
"pegs": {
    "total_count": 102,
    "types": [ ]
}
*/

public class Pegs implements Serializable {
    private int total_count;
    private List<Type> types;

    //Getters
    public int getTotal_count() {
        return total_count;
    }
    public List<Type> getTypes() {
        return types;
    }

    //Setters
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    //Create json object - Pegs
    //Arraybuilder for types array > Objectbuilder Method
    public JsonObjectBuilder toJSON() {
        JsonArrayBuilder arrbld = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfTypes = this.getTypes()
                .stream()
                .map(t -> t.toJSON())
                .toList();
        for (JsonObjectBuilder x : listOfTypes)
            arrbld.add(x);

        return Json.createObjectBuilder()
                .add("total_count", this.getTotal_count())
                .add("types", arrbld);

    }

    //Return pegs object from json object
    public static Pegs createFromJson(JsonObject o) {
        Pegs pp = new Pegs();
        List<Type> result = new LinkedList<Type>();
        JsonNumber totalCnt = o.getJsonNumber("total_count");
        JsonArray types = o.getJsonArray("types");
        pp.total_count = totalCnt.intValue(); //jsonnumber to int
        for (int i = 0; i < types.size(); i++) {
            JsonObject x = types.getJsonObject(i);
            Type t = Type.createFromJson(x);
            result.add(t);
        }
        pp.types = result;
        return pp;
    }

}