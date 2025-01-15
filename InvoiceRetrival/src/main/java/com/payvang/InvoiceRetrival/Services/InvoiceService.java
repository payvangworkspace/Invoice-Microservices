package com.payvang.InvoiceRetrival.Services;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payvang.InvoiceRetrival.Entities.Invoice;
import com.payvang.InvoiceRetrival.Exceptions.InternalServerError;
import com.payvang.InvoiceRetrival.Exceptions.InvoiceNotFoundException;
import com.payvang.InvoiceRetrival.Repositories.InvoiceRepository;
import com.payvang.InvoiceRetrival.Util.InvoiceHasher;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoiceHasher invoicehasher;

	  public List<Invoice> getAllInvoices() {
		List<Invoice> invoices = invoiceRepository.findAll();
		try {
			if (invoices.size() == 0) {
				throw new InvoiceNotFoundException();
			}
			return invoices;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	  public Invoice getInvoiceById(Long id) {
		try {
			Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InternalServerError());
			String hashvalue=invoicehasher.createInvoiceHash(invoice);
			invoice.setHash(hashvalue);
			calculateEnablePay(invoice);
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
	
	  private void calculateEnablePay(Invoice invoice) throws ParseException {
	        try {
	            Date date = invoice.getCreateDate();	           
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(date);
	            cal.add(Calendar.DATE, Integer.parseInt(invoice.getExpiresDay()));
	            cal.add(Calendar.HOUR, Integer.parseInt(invoice.getExpiresHour()));
	            date = cal.getTime();	           
	            Date dateobj = new Date();
	            long diff = dateobj.getTime() - date.getTime();	          
	            int diffInDays = (int) (diff / (24 * 60 * 60 * 1000));	           
	            if (diffInDays > 0) {
	                invoice.setEnablePay("FALSE");
	            } else {
	                invoice.setEnablePay("TRUE");
	            }
	        } catch (Exception exception) {
	                exception.printStackTrace();
	        }
	    }
}
