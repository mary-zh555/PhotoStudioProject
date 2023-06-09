package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.enums.WorkerType;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.Vacancy;
import main.com.maryzh555.photo_studio.models.users.Candidate;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author by Zhang M. on 19.04.2023.
 */
public class CandidateMenu extends Menu {

    public CandidateMenu(Scanner scanner, PhotoStudio photoStudio) {
        showMenu(scanner, photoStudio);
    }

    @Override
    public  void showMenu(Scanner scanner,  PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("We are glad you chose our company!");
                System.out.println("Please choose one of our vacancy categories:" +
                        "\n 1 - Photographer" +
                        "\n 2 - Supply Manager" +
                        "\n 3 - Customer Manager" +
                        "\n 4 - HR Manager");

                int answer = scanner.nextInt();
                List<Vacancy> vacancies;
                Candidate newCandidate = new Candidate();
                switch (answer) {
                    case 1:
                        vacancies = photoStudio.getDigitalStorage().matchVacancies(WorkerType.PHOTOGRAPHER);
                        newCandidate.setWorkerType(WorkerType.PHOTOGRAPHER);
                        break;
                    case 2:
                        vacancies = photoStudio.getDigitalStorage().matchVacancies(WorkerType.SUPPLY_MANAGER);
                        newCandidate.setWorkerType(WorkerType.SUPPLY_MANAGER);
                        break;
                    case 3:
                        vacancies = photoStudio.getDigitalStorage().matchVacancies(WorkerType.CUSTOMER_MANAGER);
                        newCandidate.setWorkerType(WorkerType.CUSTOMER_MANAGER);
                        break;
                    case 4:
                        vacancies = photoStudio.getDigitalStorage().matchVacancies(WorkerType.HR_MANAGER);
                        newCandidate.setWorkerType(WorkerType.HR_MANAGER);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
                if (vacancies.isEmpty()) {
                    System.out.println("Sorry! We don't have any opened vacancy for your job title!");
                    System.out.println(" Choose: " +
                            "\n 1 - Redo" +
                            "\n 2 - Go to previous ->(User distribution)");
                    int answer2 = scanner.nextInt();
                    switch (answer2) {
                        case 1:
                            showMenu(scanner, photoStudio);
                            break;
                        case 2:
                            new UserDistributionMenu(scanner, null, photoStudio);
                            break;
                        default:
                            throw new NoSuchOptionException();
                    }
                    break;
                } else {
                    System.out.println("We have these opened vacancies:");
                    int i = 1;
                    for (Vacancy vacancy : vacancies) {
                        System.out.println(" " + i + " - " + vacancy.getWorkerType() + ", with minimum " + vacancy.getMinExperience() + " years of experience.");
                        i++;
                    }
                }
                photoStudio.getHrManager().setUserCandidate(newCandidate);
                new RedoMenu(scanner, null, photoStudio, this);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

}
