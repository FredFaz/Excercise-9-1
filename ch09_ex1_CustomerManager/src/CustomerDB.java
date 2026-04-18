import java.io.*;
import java.util.ArrayList;

public final class CustomerDB {

    private static final String FILENAME = "Excercise-9-1/ch09_ex1_CustomerManager/src/customers.txt";
    private static final String COL_SEP = "\t";

    public static ArrayList<Customer> getAll() {
        var customers = new ArrayList<Customer>();

        File file = new File(FILENAME);
        System.out.println("Looking for: " + file.getAbsolutePath());
        System.out.println("Exists? " + file.exists());

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = in.readLine()) != null) {
                String[] columns = line.split(COL_SEP);

                if (columns.length == 3) {
                    String firstName = columns[0];
                    String lastName = columns[1];
                    String email = columns[2];

                    customers.add(new Customer(firstName, lastName, email));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found -> " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return customers;
    }

    public static void saveAll(ArrayList<Customer> customers) {
        File file = new File(FILENAME);

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Customer c : customers) {
                out.println(c.getFirstName() + COL_SEP
                        + c.getLastName() + COL_SEP
                        + c.getEmail());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}