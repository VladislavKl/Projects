public class Coffee extends Drink {

    @Override
    public String toString() {
        return super.toString()+"; тип кофе: "+getCoffeeType();
    }

    public String getCoffeeType() {
        return CoffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        CoffeeType = coffeeType;
    }


    private String CoffeeType;
}
