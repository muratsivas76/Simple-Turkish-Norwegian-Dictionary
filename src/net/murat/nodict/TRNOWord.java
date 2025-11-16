package net.murat.nodict;

import java.io.Serializable;

public class TRNOWord implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String tr;
    private String no;
    
    public TRNOWord(String tr, String no) {
        this.tr = tr;
        this.no = no;
    }
    
    public String getTR() {
        return this.tr;
    }
    
    public String getNO() {
        return this.no;
    }
    
    @Override
    public String toString() {
        return (this.no + ":" + this.tr);
    }
    
}
