package study;

public abstract class Money {
    protected int amount;
    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    static Dollar dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    static Franc franc(int amount) {
        return new Franc(amount, "CHF");
    }

    abstract Money times(int multiplier);

    String currency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;

        return amount == money.amount
                && this.getClass().equals(obj.getClass());
    }
}
