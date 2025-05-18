public class SMSNotifier implements Notifier {
    @Override
    public void notify(Person p, String message) {
        System.out.printf(
            "Enviando SMS a %s: %s%n",
            p.toString(), message
        );
    }
}