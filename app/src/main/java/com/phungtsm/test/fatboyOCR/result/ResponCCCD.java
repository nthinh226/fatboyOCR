package com.phungtsm.test.fatboyOCR.result;

public class ResponCCCD {
    private CCCD_Info data;
    private String error_code;
    private String id_document_type;
    private String logged_in_as;
    private String message;
    private String transaction_id;

    public CCCD_Info getData() {
        return data;
    }

    public void setData(CCCD_Info data) {
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getId_document_type() {
        return id_document_type;
    }

    public void setId_document_type(String id_document_type) {
        this.id_document_type = id_document_type;
    }

    public String getLogged_in_as() {
        return logged_in_as;
    }

    public void setLogged_in_as(String logged_in_as) {
        this.logged_in_as = logged_in_as;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
