package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.models.User;

/**
 * Created by Zhang M. on 20.03.2023.
 */

public class PhotoTypeOptionMenu implements IShowRedoMenu {
    public PhotoTypeOptionMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, user, order, photoStudio);
    }

    private void showMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Ok! How many people would take part in the photo shoot?");

                int answer = scanner.nextInt();
                if (answer < 1 || answer > 50) throw new NoSuchOptionException();

                PhotoType chosenType = photoStudio.matchPhotoType(answer);
                order.setDesiredPhotoType(chosenType);

                System.out.println("Got it! " +
                        "We can suggest the " + chosenType.name() + " photo shoot. \n" +
                        chosenType.getDescription());

                showRedoMenu(scanner, user, order, photoStudio);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\n" +
                        "Sorry, we only can only service 1-50 people. Please adjust your number" +
                        "\n---------");
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    public void showRedoMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nDo you want to continue or to redo?\n " +
                        "1 - Let's continue.\n " +
                        "2 - I want to redo.");

                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        new LocationOptionMenu(scanner, user, order, photoStudio);
                        break;
                    case 2:
                        new PhotoTypeOptionMenu(scanner, user, order, photoStudio);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }
}