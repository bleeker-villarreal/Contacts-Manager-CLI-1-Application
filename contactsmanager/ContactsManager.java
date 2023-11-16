package contactsmanager;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.util.*;

public class ContactsManager {

    private Scanner scanner;

    private Path pathToContacts = Paths.get("contacts.txt");


//    private static List<Contact> contacts;
    public static void main(String[] args) {
        ContactsManager contactsManager = new ContactsManager();
        contactsManager.run();
    }
//    private List<Contact> contacts;
//    private Scanner scanner;

    public ContactsManager() {
//        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadContacts();
    }

    private List<Contact> loadContacts() {
        List<Contact> contacts = new ArrayList<>();
        try  {

            List<String> contactsFromFile = Files.readAllLines(pathToContacts);

            Iterator<String> contactsIterator = contactsFromFile.iterator();
            while (contactsIterator.hasNext()) {
                String[] parts = contactsIterator.next().split("\\|");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String phoneNumber = parts[1].trim();
                    Contact contact = new Contact(name, phoneNumber);
                    contacts.add(contact);
                }
            };

        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }

        return contacts;
    }


    private void saveContacts(List<Contact> contacts) {
        List<String> moreContacts = new ArrayList<>();
        for (Contact contact : contacts){
            String contactString = contact.toString();
            moreContacts.add(contactString);
        }

        try  {
            Files.write(pathToContacts, moreContacts);
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    private void showMainMenu() {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.print("Enter an option (1, 2, 3, 4, or 5): ");
    }

    public void run() {
        int choice;
        do {
            showMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
//                    viewContacts();
                    newViewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private void viewContacts() {
        List<Contact> contacts = loadContacts();
        for (Contact contact : contacts){
            System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
//        System.out.println("------------------");
        }
//        System.out.println(Contact.getName() + " | " + Contact.getPhoneNumber());
        System.out.println("------------------");
    }

    private void newViewContacts(){
        List<Contact> contacts = loadContacts();
        System.out.printf("-------------------------------------%n");
        System.out.printf("| %-15s | %-15s |%n", "Name", "Phone number");
        System.out.printf("-------------------------------------%n");
        for (Contact contact : contacts){
            System.out.printf("| %-15s | %-15s |%n", contact.getName(), contact.getPhoneNumber());
        }
        System.out.printf("-------------------------------------%n");
    }

    private void addContact() {
        List<Contact> contacts = loadContacts();
        String name;
        name = getAName();


        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber);
        contacts.add(newContact);
        saveContacts(contacts);


    }

    private void searchContact() {
        System.out.print("Enter the name to search: ");
        String searchName = scanner.nextLine();
        List<Contact> contacts = loadContacts();
        for (Contact contact : contacts) {
            if (contact.toString().toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println("Contact found: " + contact.toString());
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    private void deleteContact() {
        List<Contact> contacts = loadContacts();
        System.out.print("Enter the name to delete: ");
        String deleteName = scanner.nextLine();

        for (Contact contact : contacts) {
            if (contact.toString().toLowerCase().contains(deleteName.toLowerCase())) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully!");
                saveContacts(contacts);
                return;
            }
        }

        System.out.println("Contact not found.");
    }
//    Check and get name from input
    private String getAName(){
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        while (checkName(name)){
            System.out.print("That name already exist please enter a different name:");
            name = scanner.nextLine();
        }
        System.out.println("Name excepted!");
        return name;
    }
    private boolean checkName(String name){
        boolean result = false;
        List<Contact> contacts = loadContacts();
        for (Contact contact : contacts){
            if(contact.getName().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
    }
}






