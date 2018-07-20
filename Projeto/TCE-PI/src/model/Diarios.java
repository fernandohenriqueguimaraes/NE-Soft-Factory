package model;

import java.util.Date;

public class Diarios {

    private Date date;
    private String pdfName;

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public Diarios(Date date, String pdfName) {
        setDate(date);
        setPdfName(pdfName);
    }

}
