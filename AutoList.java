import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AutoList {
    public static void main(String[] args) {

        /*
        *Use the declared carList.
        *This is used to store the car fields based on user input of number of cars.
        */
        try (Scanner input = new Scanner(System.in)) {
        System.out.print("Enter the number of cars you want to add:");
        int numberOfCars = input.nextInt();
        input.nextLine(); // Consume the newline character left by nextInt()

        /*
        *Create a list to store car fields based on user input of number of cars.
        *This enables the user to put properties of the car in the list(Make, Model, MPG).
        */
        List<String> carList = new LinkedList<>();
        for (int i = 0; i < numberOfCars; i++) {
            System.out.print("Enter Make " + (i + 1) + ": ");
            String carMake = input.nextLine();
            carList.add(carMake);
            System.out.print("Enter Model " + (i + 1) + ": ");
            String carModel = input.nextLine();
            carList.add(carModel);
            System.out.print("Enter mpg " + (i + 1) + ": ");
            double carMpg = input.nextDouble();
            input.nextLine(); // Consume the newline character left by nextDouble()
            carList.add("MPG: " + carMpg);
        }

        /*
        *Write the car list to the file.
        *this is used to write a sort car list by mpg to a text file named carlist.txt.
        *Eables the textfile to be printed to the console and text file via (Y/N).
        *If user selctes Y, the list is printed to the text file.
        *If user selects N, the program exits without printing to the text file.
        *If user selects anything else, an error message is printed to the console.
        *Finally, the list of cars is printed.
         */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("carList.txt"))) {
            // Write the unsorted car list to the file
            bw.write("Car List (Unsorted):" + System.lineSeparator());
            for (String line : carList) {
                bw.write(line + System.lineSeparator());
            }

            Collections.sort(carList, (a, b) -> {
                String[] partsA = a.split(": ");
                String[] partsB = b.split(": ");
                if (partsA.length > 1 && partsB.length > 1) {
                    double mpgA = Double.parseDouble(partsA[1]);
                    double mpgB = Double.parseDouble(partsB[1]);
                    return Double.compare(mpgA, mpgB);
                }
                return 0;
            });
            Collections.sort(carList);

            bw.write(System.lineSeparator() + "Car List (Sorted):" + System.lineSeparator());
            for (String line : carList) {
                bw.write(line + System.lineSeparator());
            }

            System.out.println("Sorted Car List:");
            for (String car : carList) {
                System.out.println(car);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }

        System.out.print("Do you want to print the list to a text file? (Y/N): ");
        String printToFile = input.nextLine();
        if (printToFile.equalsIgnoreCase("Y")) {
            System.out.println("Printing to file carList.txt...");
        } else if (printToFile.equalsIgnoreCase("N")) {
            System.out.println("Exiting without printing to file.");

        } else {
            System.out.println("Invalid input. Please enter Y or N.");
        }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            System.out.println("carList.txt has been created.");
            System.out.println("Please check the file for the list of cars.");
        }
    }
}