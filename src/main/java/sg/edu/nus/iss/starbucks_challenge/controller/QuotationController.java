package sg.edu.nus.iss.starbucks_challenge.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.starbucks_challenge.model.Quotation;
import sg.edu.nus.iss.starbucks_challenge.service.QuotationService;

@Controller
@RequestMapping
public class QuotationController {
    
    @Autowired
    private QuotationService qSvc;

    @GetMapping(path="/quotations")
    public String getQuotations(Model m) throws IOException {
        Optional<Quotation> q = qSvc.getQuotations();
        m.addAttribute("quotations", q.get());
        return "quotations";
    }
}
