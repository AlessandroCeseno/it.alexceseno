package it.alexceseno.receipt;

import it.alexceseno.util.MathUtils;

public abstract  class GenericItem
{
    protected int quantity;
    private String description;
    private double price;

    public final static double IMPORT_DUTY = 0.05;
    public final static String IMPORTED = "imported";
    private boolean imported;
    private static final float ROUND = 0.05f;

    protected abstract double addTaxSales(double startPrice);

    protected double addTaxesForImportedItem(double price){
        double salesTaxes = 0.0;
        if(isImported()){
            double importedTaxes = calculateImportedTaxes(price);
            for(int i = 0; i < getQuantity(); i++){
                salesTaxes += importedTaxes;
                setPrice(MathUtils.roundTwoDecimals(getPrice() + importedTaxes));
            }
        }
        return salesTaxes;
    }

    private double calculateImportedTaxes(double price)
    {
        return ((double) Math.ceil((price * Item.IMPORT_DUTY)/ROUND)*ROUND);
    }

    protected int getQuantity() {
        return quantity;
    }
    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    protected String getDescription() {
        return description;
    }
    protected void setDescription(String description) {
        this.description = description;
    }
    protected double getPrice() {
        return price;
    }
    protected void setPrice(double price) {
        this.price = price;
    }
    protected boolean isImported() {
        return imported;
    }
    protected void setImported(boolean imported) {
        this.imported = imported;
    }

    public String toString(){
        return "quantity[" + quantity + "] description[" + description + "] price["+ price + "] imported[" + imported + "]";
    }

}