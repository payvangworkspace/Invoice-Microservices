package com.payvang.InvoiceRetrival.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payvang.InvoiceRetrival.Entities.Invoice;
import com.payvang.InvoiceRetrival.Exceptions.InternalServerError;
import com.payvang.InvoiceRetrival.Exceptions.InvoiceNotFoundException;
import com.payvang.InvoiceRetrival.Repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

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
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
}
