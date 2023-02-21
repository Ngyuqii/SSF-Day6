package ssf.ws16.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/*
"name": "Mastermind",
"pieces": { }
*/

public class Mastermind implements Serializable {
    private String name;
    private Pieces pieces;
    private String id;
    private int insertCount;
    private int updateCount;
    private boolean isUpSert;

    //Empty constructor with generated ID for database
    public Mastermind() {
        this.id = generateId(8);
    }

    //Method to generate unique ID
    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    //Getters
    public String getName() {
        return name;
    }
    public Pieces getPieces() {
        return pieces;
    }
    public String getId() {
        return id;
    }
    public int getInsertCount() {
        return insertCount;
    }
    public int getUpdateCount() {
        return updateCount;
    }
    public boolean isUpSert() {
        return isUpSert;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setInsertCount(int insertCount) {
        this.insertCount = insertCount;
    }
    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }
    public void setUpSert(boolean isUpSert) {
        this.isUpSert = isUpSert;
    }

    //Create json object - Main
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("name", this.getName())
                .add("pieces", this.getPieces().toJSON())
                .add("id", this.getId())
                .build();
    }

    //Create json output - insert count 
    public JsonObject toJSONInsert() {
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("insert_count", this.getInsertCount())
                .build();
    }

    //Create json output - update count
    public JsonObject toJSONUpdate() {
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("update_count", this.getUpdateCount())
                .build();
    }

    //Take in json string from database (redis) and convert to Mastermind object
    //Unmarshalling from string / deserialization
    public static Mastermind create(String json) throws IOException {
        Mastermind m = new Mastermind();

        //1. Create an Input Stream "is" from String
        //2. Create a Reader "r" from the Input Stream
        //3. Parse the string to JsonObject
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();

            m.setName(o.getString("name"));
            JsonObject pieces = o.getJsonObject("pieces");
            m.setPieces(Pieces.createFromJson(pieces));
        }
        return m;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getName();
    }
}