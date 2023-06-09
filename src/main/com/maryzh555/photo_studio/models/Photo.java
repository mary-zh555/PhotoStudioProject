package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.PhotoPaperType;
import main.com.maryzh555.photo_studio.enums.PhotoType;

/**
 * @author by Zhang M. on 07.04.2023.
 */

//represent the product
public class Photo {

    private PhotoType type;

    private PhotoPaperType paperType;

    private int printStandardQty;

    private int printLargeQty;

    private int printProfessionalQty;

    private boolean isColored;

    private boolean isToPrint;


    public Photo(){}

    public Photo(PhotoPaperType paperType, boolean colored){
        this.paperType = paperType;
        this.isColored = colored;
    }


    public PhotoType getType() {
        return type;
    }

    public void setType(PhotoType type) {
        this.type = type;
    }

    public int getPrintStandardQty() {
        return printStandardQty;
    }

    public void setPrintStandardQty(int printStandardQty) {
        this.printStandardQty = printStandardQty;
    }

    public int getPrintLargeQty() {
        return printLargeQty;
    }

    public void setPrintLargeQty(int printLargeQty) {
        this.printLargeQty = printLargeQty;
    }

    public int getPrintProfessionalQty() {
        return printProfessionalQty;
    }

    public void setPrintProfessionalQty(int printProfessionalQty) {
        this.printProfessionalQty = printProfessionalQty;
    }

    public boolean isColored() {
        return isColored;
    }

    public void setColored(boolean colored) {
        this.isColored = colored;
    }

    public boolean isToPrint() {
        return isToPrint;
    }

    public void setToPrint(boolean toPrint) {
        isToPrint = toPrint;
    }

    public PhotoPaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PhotoPaperType paperType) {
        this.paperType = paperType;
    }
}
