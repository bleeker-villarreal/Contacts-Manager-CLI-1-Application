package contactsmanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    public static void main(String[] args) {
        ContactsManager contactsManager = new ContactsManager();
        contactsManager.run();
    }
    private List<Contact> contacts;
    private Scanner scanner;

    public ContactsManager() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadContacts();
    }

    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String phoneNumber = parts[1].trim();
                    Contact contact = new Contact(name, phoneNumber);
                    contacts.add(contact);
                }
            }
            System.out.println("Contacts loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }


    private void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt"))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
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
                    viewContacts();
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
                    saveContacts();
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private void viewContacts() {
        System.out.println(Contact.getName() + " | " + Contact.getPhoneNumber());
        System.out.println("------------------");
    }

    private void addContact() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber);
        contacts.add(newContact);

        System.out.println("Contact added successfully!");
    }

    private void searchContact() {
        System.out.print("Enter the name to search: ");
        String searchName = scanner.nextLine();

        for (Contact contact : contacts) {
            if (contact.toString().toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println("Contact found: " + contact.toString());
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    private void deleteContact() {
        System.out.print("Enter the name to delete: ");
        String deleteName = scanner.nextLine();

        for (Contact contact : contacts) {
            if (contact.toString().toLowerCase().contains(deleteName.toLowerCase())) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully!");
                return;
            }
        }

        System.out.println("Contact not found.");
    }
}





