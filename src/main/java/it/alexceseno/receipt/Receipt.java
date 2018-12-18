package it.alexceseno.receipt;
import it.alexceseno.util.MathUtils;
import java.util.ArrayList;
import java.util.List;

public class Receipt
{

    private double salesTaxes;
    private double total;
    private List<GenericItem> genericItems = new ArrayList<GenericItem>() ;

    public double getSalesTaxes() {
        return MathUtils.roundTwoDecimals(salesTaxes);
    }

    public double getTotal() {
        return MathUtils.roundTwoDecimals(total);
    }

    public void calculate(){

        for(GenericItem genericItem:genericItems){
            double price = genericItem.getPrice();
            addSalesTaxes(genericItem, price);
            addTaxesForImportedItem(genericItem, price);
            updateTotal(genericItem, price);
        }
        total += salesTaxes;

    }

    public void addItem(int quantity, String description, double price){

        GenericItem genericItem = createItem(description);
        genericItem.setQuantity(quantity);
        genericItem.setDescription(description);
        genericItem.setPrice(price);
        genericItem.setImported(description.contains(Item.IMPORTED));
        genericItems.add(genericItem);

    }

    private GenericItem createItem(String description) {

        GenericItem genericItem = new Item();
        for(ExemptItem.BASIC_SALES_EXEMPT basicSalesExempt : ExemptItem.BASIC_SALES_EXEMPT.values()){
            if(description.toUpperCase().contains(basicSalesExempt.name()))
                genericItem = new ExemptItem();
        }
        return genericItem;

    }

    private void updateTotal(GenericItem genericItem, double price) {

        for(int i = 0; i < genericItem.getQuantity(); i++){
            if(i>0){
                genericItem.setPrice(MathUtils.roundTwoDecimals(genericItem.getPrice() + price));
            }
            this.total += price;
        }

    }

    private void addTaxesForImportedItem(GenericItem genericItem, double price){

        this.salesTaxes += genericItem.addTaxesForImportedItem(price);

    }

    private void addSalesTaxes(GenericItem genericItem, double price) {

        this.salesTaxes += genericItem.addTaxSales(price);

    }

    public List<String> print() {

        List<String> listItems = new ArrayList<String>();
        for(GenericItem genericItem:genericItems){
            int quantity = genericItem.getQuantity();
            String description = genericItem.getDescription();
            double price = genericItem.getPrice();
            listItems.add(String.valueOf(quantity) + " " + description + " " + String.valueOf(price));
        }
        return listItems;

    }

}