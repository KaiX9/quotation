package sg.edu.nus.iss.starbucks_challenge.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Items implements Serializable {
    
    private String name;
    private float price = -1;
    private String username;
    private String id;
    private String timestamp;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return "Items [name=" + name + ", price=" + price + ", username=" + username + ", id=" + id + ", timestamp="
                + timestamp + "]";
    }

    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
            .add("name", this.getName())
            .add("price", this.getPrice())
            .add("username", this.getUsername());
    }

    public static Items createFromJson(JsonObject o) {
        Items i = new Items();
        i.setName(o.getString("name"));
        i.setUsername(o.getString("username"));
        i.setId(o.getString("id"));
        i.setTimestamp(o.getString("dt"));
        i.setPrice((float)o.getJsonNumber("price").doubleValue());

        return i;        
    }
}
