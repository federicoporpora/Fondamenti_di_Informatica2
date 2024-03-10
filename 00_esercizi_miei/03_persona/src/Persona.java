public class Persona {
    private String nameAndSurname;
    private int yearOfBirth;
    public void costruttore(String nameAndSurname, int yearOfBirth) {
        this.nameAndSurname = nameAndSurname;
        this.yearOfBirth = yearOfBirth;
    }
    public void costruttore(String nameAndSurname, String yearOfBirth) {
        this.nameAndSurname = nameAndSurname;
        this.yearOfBirth = Integer.parseInt(yearOfBirth);
    }
    public String getNameAndSurname() {
        return nameAndSurname;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public boolean omonimo(Persona p) {
        return ((this.nameAndSurname.equals(p.nameAndSurname)) && (this.yearOfBirth == p.yearOfBirth));
    }
    public int isOlderThan(Persona other) {
        if (this.yearOfBirth > other.yearOfBirth) return 1;
        if (this.yearOfBirth == other.yearOfBirth) return 0;
        return -1;
    }
}