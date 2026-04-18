import java.util.ArrayList;

public class CustomerManagerApp {

    public static void main(String[] args) {
        // display a welcome message
        System.out.println("Welcome to the Customer Manager\n");

        // create the db object and get the list of customers
        ArrayList<Customer> customers = CustomerDB.getAll();                

        // display the command menu
        displayMenu();

        // perform 1 or more actions
        String action = "";
        while (!action.equalsIgnoreCase("exit")) {
            // get the input from the user
            action = Console.getString("Enter a command: ");
            System.out.println();

            if (action.equalsIgnoreCase("list")) {
                displayAll(customers);
            } else if (action.equalsIgnoreCase("add")) {
                addCustomer(customers);
            } else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) {
                deleteCustomer(customers);
            } else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
                System.out.println("Bye.\n");
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all customers");
        System.out.println("add     - Add a customer");
        System.out.println("del     - Delete a customer");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    public static void displayAll(ArrayList<Customer> customers) {
        System.out.println("CUSTOMER LIST");

        String format = "%-27s%-40s%n";
        for (Customer c : customers) {
            System.out.printf(format, c.getName(), c.getEmail());
        }
        System.out.println();        
    }

    public static void addCustomer(ArrayList<Customer> customers) {
        String firstName = Console.getString("Enter first name: ");
        String lastName = Console.getString("Enter last name: ");
        String email = Console.getString("Enter customer email: ");

        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);        
        CustomerDB.saveAll(customers);

        System.out.println(customer.getName() + " has been added.\n");
    }

    public static void deleteCustomer(ArrayList<Customer> customers) {
        String email = Console.getString("Enter email to delete: ");

        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                customers.remove(c);
                CustomerDB.saveAll(customers);  // save list to file
                System.out.println(c.getName() 
                        + " has been deleted.\n");
                return;
            }
        }        
        
        System.out.println("No customer matches that email.\n");        
    }
}