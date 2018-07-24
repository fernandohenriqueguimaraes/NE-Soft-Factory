package model;

import java.util.Date;

public class Diarios {

    private String fonte;
    private Date date;
    private String pdfName;

    public String getFonte() { return fonte; }

    public void setFonte(String fonte) { this.fonte = fonte; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public Diarios(String fonte, Date date, String pdfName) {
        setFonte(fonte);
        setDate(date);
        setPdfName(pdfName);
    }

}
