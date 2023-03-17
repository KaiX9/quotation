package sg.edu.nus.iss.starbucks_challenge.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.starbucks_challenge.model.Quotation;

@Service
public class QuotationService {
    
    public ResponseEntity<String> post(String name, String price, String username) {
        String url = "https://quotation-production.up.railway.app/quotation";
        Quotation q = new Quotation();
        RequestEntity req = RequestEntity.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(q.toJSON().toString(), String.class);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> res = template.exchange(req, String.class);

        return res;
    }

    public Optional<Quotation> getQuotations() throws IOException {
        String url = "https://quotation-production.up.railway.app/quotation";
        RequestEntity req = RequestEntity
                            .get(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .header("Content-Type", "application/json")
                            .build();
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        Quotation q =  Quotation.createFromJson(resp.getBody());
        if (null == q) {
            return Optional.empty();
        }
        return Optional.of(q);
    }

}
