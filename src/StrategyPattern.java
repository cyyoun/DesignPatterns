import java.util.ArrayList;
import java.util.List;

interface Payment {
    public void pay(int amount);
}

class Naverpay implements Payment {
    private String name;
    private String cardNum;
    private String expiryDate;

    public Naverpay(String name, String cardNum, String expiryDate) {
        this.name = name;
        this.cardNum = cardNum;
        this.expiryDate = expiryDate;
    }
    @Override
    public void pay(int amount) {
        System.out.println("Naverpay =" + amount);
    }
}

class Kakaopay implements Payment {
    private String id;
    private String pw;

    public Kakaopay(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Kakaopay = " + amount);
    }
}


class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class ShoppingCart {
    List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void delItem(Item item) {
        this.items.remove(item);
    }

    public int calcTotal() {
        int sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public void pay(Payment payment) {
        int amount = calcTotal();
        payment.pay(amount);
    }
}


public class StrategyPattern {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item a = new Item("socks", 50);
        Item b = new Item("wallet", 100);

        cart.addItem(a);
        cart.addItem(b);

        cart.pay(new Kakaopay("cyyoun", "1234"));
        cart.pay(new Naverpay("윤채영", "111", "12/31"));


    }
}
