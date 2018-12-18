package it.alexceseno.receipt;

public class ExemptItem extends GenericItem
{
    private static double TAX_SALES = 0.00;

    protected enum BASIC_SALES_EXEMPT
    {
        BOOK, FOOD, MEDICAL_PRODUCT, CHOCOLATE, PILL;
    }

    @Override
    protected double addTaxSales(double startPrice)
    {
        return this.TAX_SALES;
    }
}