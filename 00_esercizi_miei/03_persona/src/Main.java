public class Main {
    public static void main(String[] args) {
        Persona Federico = new Persona();
        Persona Andrea = new Persona();
        Federico.costruttore("Federico Porpora ", "2004");
        Andrea.costruttore("Andrea Porpora ", 2007);
        System.out.println(Andrea.getNameAndSurname() + Andrea.getYearOfBirth());
        System.out.println(Federico.getNameAndSurname() + Federico.getYearOfBirth());
        System.out.println(Federico.isOlderThan(Andrea)); //il re
    }
}