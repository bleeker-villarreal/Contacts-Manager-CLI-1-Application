package contactsmanager;

public class Contact {
    private static String name;
    private static String phoneNumber;

    public Contact (String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static String getName() {
        return name;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "ContactManager.Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
