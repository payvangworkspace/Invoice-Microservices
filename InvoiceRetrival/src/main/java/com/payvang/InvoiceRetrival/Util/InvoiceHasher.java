package com.payvang.InvoiceRetrival.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.payvang.InvoiceRetrival.Entities.Invoice;
import com.payvang.InvoiceRetrival.Exceptions.CustomException;

@Component
public class InvoiceHasher {

	private static Logger logger = LoggerFactory.getLogger(InvoiceHasher.class.getName());

	public String createInvoiceHash(Invoice invoice) throws CustomException {

		StringBuilder allFields = new StringBuilder();
		Map<String, String> invoiceMap = new HashMap<String, String>();
		String amount = invoice.getTotalAmount();
		String amountnew = amount.replace(".", "");
		logger.info("Invoice Hash Amount:" + amountnew);
		invoiceMap.put(FieldType.APP_ID.getName(), invoice.getAppId());
		invoiceMap.put(FieldType.ORDER_ID.getName(), invoice.getInvoiceId());
		invoiceMap.put(FieldType.AMOUNT.getName(), amountnew);
		invoiceMap.put(FieldType.TXNTYPE.getName(), "SALE");
		String custName = invoice.getName();
		if (custName == null) {
			custName = "";
			invoiceMap.put(FieldType.CUST_NAME.getName(), custName);
		} else {
			invoiceMap.put(FieldType.CUST_NAME.getName(), invoice.getName());
		}

		if (invoice.getAddress() != null) {
			invoiceMap.put(FieldType.CUST_STREET_ADDRESS1.getName(), invoice.getAddress());
		} else {
			invoiceMap.put(FieldType.CUST_STREET_ADDRESS1.getName(), "");
		}
		if (invoice.getZip() != null) {
			invoiceMap.put(FieldType.CUST_ZIP.getName(), invoice.getZip());
		} else {
			invoiceMap.put(FieldType.CUST_ZIP.getName(), "");
		}

		invoiceMap.put(FieldType.CUST_PHONE.getName(), invoice.getPhone());
		invoiceMap.put(FieldType.CUST_EMAIL.getName(), invoice.getEmail());
		String productDesc = invoice.getProductDesc();
		if (productDesc == null) {
			productDesc = "";
			invoiceMap.put(FieldType.PRODUCT_DESC.getName(), productDesc);
		} else {
			invoiceMap.put(FieldType.PRODUCT_DESC.getName(), invoice.getProductDesc());
		}

		invoiceMap.put(FieldType.CURRENCY_CODE.getName(), invoice.getCurrencyCode());
		invoiceMap.put(FieldType.RETURN_URL.getName(), invoice.getReturnUrl());

		Map<String, String> sortedMap = new TreeMap<String, String>(invoiceMap);
		for (String key : sortedMap.keySet()) {
			allFields.append(ConfigurationConstants.FIELD_SEPARATOR.getValue());
			allFields.append(key);
			allFields.append(ConfigurationConstants.FIELD_EQUATOR.getValue());
			allFields.append(sortedMap.get(key));
		}
		logger.info("Invoice Hash AllFields:" + allFields);
		allFields.deleteCharAt(0); // Remove first FIELD_SEPARATOR
		String salt = (new PropertiesManager()).getSalt(invoice.getAppId());
		allFields.append("~btnPay=Pay Now~token=");
		allFields.append(salt);
		logger.info("Invoice Hash AllFields:" + allFields.toString());
		return Hasher.getHash(allFields.toString());
	}
}
