package it.alexceseno.receipt;

import it.alexceseno.util.MathUtils;

public  class Item extends GenericItem
{
    private static final double TAX_SALES = 0.10;

    @Override
    protected double addTaxSales(double startPrice)
    {
        double basicTaxes = startPrice * TAX_SALES;
        double sumSalesTaxes = 0.00;
        for(int i = 0; i < quantity; i++)
        {
            sumSalesTaxes += basicTaxes;
            setPrice(MathUtils.roundTwoDecimals(this.getPrice() + basicTaxes));
        }
        return sumSalesTaxes;
    }
}

