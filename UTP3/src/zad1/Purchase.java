package zad1;

public class Purchase {
    int id;
    String name;
    String lastName;
    String product;
    float amount;
    float price;

    private Purchase(int id, String name, String lastName, String product, float amount, float price){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("c%05d;%s %s;%s;%.1f;%.1f", id, lastName, name, product, amount, price);
    }

    public static Purchase of(String data){
        String[] split = data.split(";");
        String[] name = split[1].split(" ");

        return new Purchase(Integer.parseInt(split[0].substring(1)), name[1], name[0], split[2], Float.parseFloat(split[3]), Float.parseFloat(split[4]));
    }
}
