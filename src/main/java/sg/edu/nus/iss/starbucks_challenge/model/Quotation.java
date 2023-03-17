package sg.edu.nus.iss.starbucks_challenge.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class Quotation implements Serializable {
    
    private String quotation_id;
    private List<Items> items;
    
    public String getQuotation_id() {
        return quotation_id;
    }
    public void setQuotation_id(String quotation_id) {
        this.quotation_id = quotation_id;
    }
    public List<Items> getItems() {
        return items;
    }
    public void setItems(List<Items> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Quotation [quotation_id=" + quotation_id + ", items=" + items + "]";
    }

    public JsonObject toJSON() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfItems = this.getItems()
            .stream()
            .map(t -> t.toJSON())
            .toList();
        
        for (JsonObjectBuilder x : listOfItems) {
            arrBuilder.add(x);
        }

        return Json.createObjectBuilder()
            .add("items", arrBuilder)
            .build();
    }

    public static Quotation createFromJson(String json) throws IOException {
        Quotation q = new Quotation();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            q.setQuotation_id(o.getString("quotation_id"));

            List<Items> i = new LinkedList<Items>();
            JsonArray items = o.getJsonArray("items");
            for (int x = 0; x < items.size(); x++) {
                JsonObject j = items.getJsonObject(x);
                Items b = Items.createFromJson(j);
                i.add(b);
            }
            q.setItems(i);
        }
            return q;
        }
}
