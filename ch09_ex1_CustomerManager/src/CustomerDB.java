import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class CustomerDB {

    private static final String FILENAME = "customers.txt";
    private static final String COL_SEP = "\t";    

    public static ArrayList<Customer> getAll() {
        var customers = new ArrayList<Customer>();
        
        try (BufferedReader in = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] columns = line.split(COL_SEP);
                
                if (columns.length == 3) {
                    String firstName = columns[0];
                    String lastName = columns[1];
                    String email = columns[2];
                    
                    Customer c = new Customer(firstName, lastName, email);
                    customers.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from " + FILENAME + ": " + e.getMessage());
        }
        
        return customers;
    }

    public static void saveAll(ArrayList<Customer> customers) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILENAME))) {
            for (Customer c : customers) {
                out.print(c.getFirstName());
                out.print(COL_SEP);
                out.print(c.getLastName());
                out.print(COL_SEP);
                out.print(c.getEmail());
                out.println();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}