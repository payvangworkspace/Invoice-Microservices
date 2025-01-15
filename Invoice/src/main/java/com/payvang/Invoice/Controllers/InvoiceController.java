package com.payvang.Invoice.Controllers;

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
import com.payvang.Invoice.Entities.Invoice;
import com.payvang.Invoice.Models.CreatedResponse;
import com.payvang.Invoice.Models.CustomResponse;
import com.payvang.Invoice.Models.NotCreatedResponse;
import com.payvang.Invoice.Services.InvoiceService;
import com.payvang.Invoice.Util.Messages;

@RestController
@RequestMapping("/management")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice) {
    	
    	boolean response=invoiceService.saveInvoice(invoice);
    	if(!response) {
    		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(NotCreatedResponse.builder().message(Messages.NOT_CREATED).status(HttpStatus.BAD_REQUEST).build());
    	}
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatedResponse.builder().message(Messages.SUCCESS_CREATED).status(HttpStatus.CREATED).build());
    }

    @PostMapping("/create-bulk")
    public ResponseEntity<?> createBulkInvoices(@RequestBody List<Invoice> invoices) {
    	boolean response=invoiceService.saveInvoices(invoices);
    	if(!response) {
    		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(NotCreatedResponse.builder().message(Messages.NOT_CREATED).status(HttpStatus.BAD_REQUEST).build());
    		
    	}
    	
    	 return ResponseEntity.status(HttpStatus.CREATED).body(CreatedResponse.builder().message(Messages.SUCCESS_CREATED).status(HttpStatus.CREATED).status(HttpStatus.CREATED).
    			 build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
    	boolean res =invoiceService.deleteInvoiceById(id);
    	if(res) {
    		return ResponseEntity.ok(CustomResponse.builder().message(Messages.DELETED_SUCCESS));
    		
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CustomResponse.builder().message(Messages.DELETED_ERROR).status(HttpStatus.BAD_REQUEST).build());
    		
    	}
    }
   
   
}