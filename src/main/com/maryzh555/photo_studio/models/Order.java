package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.Location;
import main.com.maryzh555.photo_studio.enums.PhotoPaperType;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.users.Client;
import main.com.maryzh555.photo_studio.models.users.Photographer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an order made by a customer, containing information about the desired photographer,
 * photo type, and location. It also includes methods for calculating the total cost of the order.
 *
 * @author Zhang M. on 23.03.2023.
 */
public class Order implements OrderOrClient {

    // The id value will be reset to 1 on every run of the program, the id field is added for the future database use.
    // The idCounter is a temporary solution until the database will be added.
    private static int idCounter = 1;

    private Client client;

    private int id;

    private Photographer desiredPhotographer;

    private Location desiredLocation;

    private Photo orderedPhoto;

    private List<Photo> photoPack = new ArrayList<>();

    private int total;


    public Order() {
        this.orderedPhoto = new Photo(); //create a photo when the order is created
        this.client = new Client();
        this.orderedPhoto.setPrintStandardQty(-1);//this value is set to '-1' to use it in checking to check_out. If the user did not change any printing options the value will still be -1, but if opened the printing menu, then will be changed
    }

    public int calculateTotal(Order order) {
        int costOfPhoto = (order.getDesiredPhotographer().getHourlyRate() + order.getDesiredLocation().getRentingCost()) * order.getOrderedPhoto().getType().getHours();

        int colorCost; //colored photos cost +1$ per paper
        if (order.getOrderedPhoto().isColored()) {
            colorCost = 1; // additional 1$ per sheet
        } else {
            colorCost = 0; //black and white have no additional fees
        }

        //(number of photos of one type * ( cost for this type per sheet + cost for color per sheet))
        int costOfPrinting = (order.getOrderedPhoto().getPrintStandardQty() * (PhotoPaperType.STANDARD.getCostPerCopy() + colorCost)) +
                (order.getOrderedPhoto().getPrintLargeQty() * (PhotoPaperType.LARGE.getCostPerCopy() + colorCost)) +
                (order.getOrderedPhoto().getPrintProfessionalQty() * (PhotoPaperType.PROFESSIONAL.getCostPerCopy() + colorCost));

        return (costOfPhoto + costOfPrinting);
    }


    /// Only for printed photos
    public void addToPhotoPack(int photoQty, PhotoPaperType paperType, boolean isColored) {
        if (this.orderedPhoto.isToPrint()) {
            for (int i = 0; i < photoQty; i++) {
                this.photoPack.add(new Photo(paperType, isColored));
            }
        } else {
            this.photoPack = null;
        }
    }

    public Photographer getDesiredPhotographer() {
        return desiredPhotographer;
    }

    public void setDesiredPhotographer(Photographer desiredPhotographer) {
        this.desiredPhotographer = desiredPhotographer;
    }

    public Location getDesiredLocation() {
        return desiredLocation;
    }

    public void setDesiredLocation(Location desiredLocation) {
        this.desiredLocation = desiredLocation;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = idCounter;
        idCounter++;
    }

    public Photo getOrderedPhoto() {
        return orderedPhoto;
    }

    public void setOrderedPhoto(Photo orderedPhoto) {
        this.orderedPhoto = orderedPhoto;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Photo> getPhotoPack() {
        return photoPack;
    }
}
