package com.payvang.InvoiceRetrival.Util;

import java.util.HashMap;
import java.util.Map;

public enum FieldType {

	APP_ID("APP_ID", 2, 36, true, FieldFormatType.NUMBER, false),
	ORDER_ID("ORDER_ID", 1, 50, true, FieldFormatType.SPECIAL, false),
	AMOUNT("AMOUNT", 3, 12, true, FieldFormatType.AMOUNT, false),
	TXNTYPE("TXNTYPE", 4, 50, true, FieldFormatType.ALPHA, false),
	CUST_NAME("CUST_NAME", 1, 150, false, FieldFormatType.ALPHA, true),
	CUST_STREET_ADDRESS1		("CUST_STREET_ADDRESS1", 2, 250, false, FieldFormatType.ALPHANUM, true),
	CUST_ZIP("CUST_ZIP", 6, 9, false, FieldFormatType.ALPHANUM, true),
	CUST_PHONE("CUST_PHONE", 8, 15, false, FieldFormatType.NUMBER, true),
	CUST_EMAIL("CUST_EMAIL", 6, 120, true, FieldFormatType.EMAIL, false),
	PRODUCT_DESC("PRODUCT_DESC", 1, 1024, false, FieldFormatType.SPECIAL, true),
	CURRENCY_CODE("CURRENCY_CODE", 3, 3, true, FieldFormatType.NUMBER, false),
	RETURN_URL("RETURN_URL", 5, 1024, false, FieldFormatType.URL, false),

	HASH("HASH", 64, 64, false, FieldFormatType.ALPHANUM, false),
	ORIG_TXN_ID("ORIG_TXN_ID", 8, 16, false, FieldFormatType.SPECIAL, false);

	private final String name;
	private final int minLength;
	private final int maxLength;
	private final boolean required;
	private final FieldFormatType type;
	private final String responseMessage;
	private final boolean isSpecialCharReplacementAllowed;

	private FieldType(String name, int minLength, int maxLength, boolean required, String responseMessage,
			FieldFormatType type, boolean isSpecialCharReplacementAllowed) {
		this.name = name;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.required = required;
		this.responseMessage = responseMessage;
		this.type = type;
		this.isSpecialCharReplacementAllowed = isSpecialCharReplacementAllowed;
	}

	private FieldType(String name, int minLength, int maxLength, boolean required, FieldFormatType type,
			boolean isSpecialCharReplacementAllowed) {
		this.name = name;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.required = required;
		this.responseMessage = "Invalid " + name;
		this.type = type;
		this.isSpecialCharReplacementAllowed = isSpecialCharReplacementAllowed;
	}

	public static Map<String, FieldType> getFieldsMap() {
		Map<String, FieldType> fields = new HashMap<String, FieldType>();

		FieldType[] fieldTypes = FieldType.values();
		for (FieldType fieldType : fieldTypes) {
			fields.put(fieldType.getName(), fieldType);
		}

		return fields;
	}

	public static Map<String, FieldType> getMandatoryRequestFields() {
		Map<String, FieldType> fields = new HashMap<String, FieldType>();

		FieldType[] fieldTypes = FieldType.values();
		for (FieldType fieldType : fieldTypes) {
			if (fieldType.isRequired()) {
				fields.put(fieldType.getName(), fieldType);
			}
		}

		return fields;
	}

	public static Map<String, FieldType> getMandatorSupportFields() {
		Map<String, FieldType> fields = new HashMap<String, FieldType>();

		fields.put(FieldType.ORIG_TXN_ID.getName(), FieldType.ORIG_TXN_ID);
		fields.put(FieldType.TXNTYPE.getName(), FieldType.TXNTYPE);
		fields.put(FieldType.AMOUNT.getName(), FieldType.AMOUNT);
		fields.put(FieldType.APP_ID.getName(), FieldType.APP_ID);
		fields.put(FieldType.HASH.getName(), FieldType.HASH);

		return fields;
	}

	public String getName() {
		return name;
	}

	public int getMinLength() {
		return minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public boolean isRequired() {
		return required;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public FieldFormatType getType() {
		return type;
	}

	public boolean isSpecialCharReplacementAllowed() {
		return isSpecialCharReplacementAllowed;
	}
}
