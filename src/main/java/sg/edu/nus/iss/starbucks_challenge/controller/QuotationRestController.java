package sg.edu.nus.iss.starbucks_challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.starbucks_challenge.service.QuotationService;

@Controller
@RequestMapping(path="/api")
public class QuotationRestController {
    
    @Autowired
    private QuotationService qSvc;

    @PostMapping (path="/quotation")
    public ResponseEntity<String> postQuotation(@RequestParam(required = true) String name
        , @RequestParam(required = true) String price, @RequestParam(required = true) String username) {
        
        ResponseEntity<String> res = qSvc.post(name, price, username);
        
        return res;
    }

}
