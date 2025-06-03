package co.in.sagarkale.requestmatcher.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    @GetMapping("/invoice")
    public String getInvoiceDetails(){
        return "Invoice : ABC1123 - Sagar Kale - 455 Rs";
    }

    @PostMapping("/invoice")
    public String addNewInvoice(){
        return "Invoice added successfully";
    }

    @DeleteMapping("/invoice")
    public String deleteInvoice(){
        return "Invoice deleted successfully";
    }

}
