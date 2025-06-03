package co.in.sagarkale.methodsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    @GetMapping("/invoice")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getInvoiceDetails(){
        return "Invoice : ABC1123 - Sagar Kale - 455 Rs";
    }

    @PostMapping("/invoice")
    @PreAuthorize("hasAuthority('WRITE_PERMISSION')")
    public String addNewInvoice(){
        return "Invoice added successfully";
    }

    @DeleteMapping("/invoice")
    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    public String deleteInvoice(){
        return "Invoice deleted successfully";
    }

}
