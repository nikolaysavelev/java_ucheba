public class CheckingAccount extends Account { // Наследование Account
    public CheckingAccount(double balance, String accountNumber, String clientName) { // ЗДЕСЬ ТОЖЕ МОГЛИ БЫ СДЕЛАТЬ ПЕРЕГРУЖЕННЫЙ КОНСТРУКТОР
        super(balance, accountNumber, clientName);
        if (balance < 0) { // Выбрасываем исключение, когда баланс введен отрицательным
            throw new IllegalArgumentException("Баланс не может быть отрицательным");
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    // Данные методы не добавлены в Main.java, реализована логика снятия средств с обычного счета для демонстрации перегрузки методов
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Сумма " + amount + " успешно снята с текущего счета.");
        } else {
            System.out.println("Недостаточно средств на текущем счете.");
        }
    }

    @Override
    public void withdraw(double percentage, boolean isPercentage) {
        System.out.println("Текущие счета не поддерживают снятие средств по процентной ставке.");
    }
}
