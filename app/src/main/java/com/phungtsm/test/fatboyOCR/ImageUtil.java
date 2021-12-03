package com.phungtsm.test.fatboyOCR;

public class ImageUtil {
    private String id_document_type;
    private String front_image;
    private String back_image;

    public ImageUtil(String id_document_type, String front_image, String back_image) {
        this.id_document_type = id_document_type;
        this.front_image = front_image;
        this.back_image = back_image;
    }

    public String getId_document_type() {
        return id_document_type;
    }

    public void setId_document_type(String id_document_type) {
        this.id_document_type = id_document_type;
    }

    public String getFront_image() {
        return front_image;
    }

    public void setFront_image(String front_image) {
        this.front_image = front_image;
    }

    public String getBack_image() {
        return back_image;
    }

    public void setBack_image(String back_image) {
        this.back_image = back_image;
    }
}
