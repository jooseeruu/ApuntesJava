public abstract class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s[%s]", name, getRole());
    }
}