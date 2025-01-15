package com.payvang.Invoice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payvang.Invoice.Entities.Invoice;
import com.payvang.Invoice.Exceptions.CustomException;
import com.payvang.Invoice.Repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public boolean saveInvoice(Invoice invoice) {

		Invoice savedResponse = invoiceRepository.save(invoice);
		if (savedResponse == null) {
			try {
				throw new CustomException("Error while saving invoice to DB");
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}
		}

		return true;
	}

	public boolean saveInvoices(List<Invoice> invoices) {
		List<Invoice> savedResponse = invoiceRepository.saveAll(invoices);
		if (savedResponse == null) {
			try {
				throw new CustomException("Error while saving invoices to DB");
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}
		}
		return true;

	}

	public boolean deleteInvoiceById(Long id) {
		try {
			Invoice invoice = invoiceRepository.findById(id)
					.orElseThrow(() -> new CustomException("No Invoice Found on the Database"));
			// perform delete operation
			invoiceRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
