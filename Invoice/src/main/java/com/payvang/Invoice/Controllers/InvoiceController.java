package com.payvang.Invoice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payvang.Invoice.Entities.Invoice;
import com.payvang.Invoice.Services.EmailService;
import com.payvang.Invoice.Services.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/create")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @PostMapping("/create-bulk")
    public List<Invoice> createBulkInvoices(@RequestBody List<Invoice> invoices) {
    	System.out.println("Create Bulk is Calling.");
        return invoiceService.saveInvoices(invoices);
    }

    @PostMapping("/publish/{id}")
    public String publishInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);

        if (invoice == null) {
            throw new RuntimeException("Invoice not found");
        }

        // Construct the payment URL (example URL)
        String paymentUrl = "https://payvang.com/invoice/pay/" + invoice.getInvoiceNo();

        emailService.sendInvoiceEmail(invoice, paymentUrl);
        return "Invoice published and email sent successfully!";
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
    	boolean res =invoiceService.deleteInvoiceById(id);
    	if(res) {
    		return ResponseEntity.ok("Invoice Deleted Succcessfully..");
    		
    	}
    	else {
    		return ResponseEntity.badRequest().build();
    		
    	}
    }
   
    @GetMapping("/all")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}