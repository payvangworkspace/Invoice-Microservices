package com.payvang.InvoiceRetrival.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payvang.InvoiceRetrival.Entities.Invoice;
import com.payvang.InvoiceRetrival.Models.CustomResponse;
import com.payvang.InvoiceRetrival.Services.EmailService;
import com.payvang.InvoiceRetrival.Services.InvoiceService;
import com.payvang.InvoiceRetrival.Util.Messages;

@RestController
@RequestMapping("/retrieval")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/publish/{id}")
    public ResponseEntity<?> publishInvoice(@PathVariable Long id) {
        Invoice response = invoiceService.getInvoiceById(id);

        if (response==null) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CustomResponse.builder().message(Messages.SEND_ERROR).status(HttpStatus.BAD_REQUEST).build());
          
        }

        // Construct the payment URL (example URL)
        String paymentUrl = "https://payvang.com/invoice/pay/" + response.getInvoiceNo();

        emailService.sendInvoiceEmail(response, paymentUrl);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(CustomResponse.builder().message(Messages.SEND_SUCCESS).status(HttpStatus.ACCEPTED).build());
    }
    
 
    @GetMapping("/all")
    public ResponseEntity<?> getAllInvoices() {
    	List<Invoice>invoices=invoiceService.getAllInvoices();
    	if(invoices==null) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(CustomResponse.builder().message(Messages.NO_INVOICE_EXIST_ERROR).status(HttpStatus.NO_CONTENT).build());
    	}
    	return ResponseEntity.status(HttpStatus.FOUND).body(invoices);
        
    }
}