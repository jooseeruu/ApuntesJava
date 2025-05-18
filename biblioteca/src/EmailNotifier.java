// EmailNotifier.java
public class EmailNotifier implements Notifier {
    @Override
    public void notify(Person p, String message) {
        System.out.printf(
            "Enviando email a %s: %s%n",
            p.toString(), message
        );
    }
}