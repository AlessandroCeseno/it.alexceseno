package it.alexceseno.test;

import it.alexceseno.receipt.Receipt;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SalesTaxesTest
{
    private Receipt receipt;

    @Before
    public void setup()
    {
        receipt = new Receipt();
    }

    /*
     * SalesTaxes Test. The item list is a book, a cd and a bar chocolate
     */
    @Test
    public void purchaseOneBookOneCDOneChocolateBarSalesTaxes() throws Exception
    {
        addOneBookOneCDOneChocolateBar();

        checkSalesTaxes(1.50);
    }

    /*
     * Total test. The item list is a book, a cd and a bar chocolate
     */
    @Test
    public void purchaseOneBookOneCDOneChocolateBarTotal() throws Exception
    {
        addOneBookOneCDOneChocolateBar();

        checkTotal(29.83);
    }

    private void addOneBookOneCDOneChocolateBar() throws Exception
    {
        receipt.addItem(1, "book", 12.49);
        receipt.addItem(1, "music CD", 14.99);
        receipt.addItem(1, "chocolate bar", 0.85);
    }

    /*
     * SalesTaxes Test. The item list is an imported box of chocolate and an imported
     * bottle of perfume
     */
    @Test
    public void purchaseOneImportedBoxChocolatesOneImportedPerfumeSalesTaxes() throws Exception
    {
        addOneImportedBoxChocolatesOneImportedPerfume();

        checkSalesTaxes(7.65);
    }

    /*
     * Total test. The item list is an imported box of chocolate and an imported
     * bottle of perfume
     */
    @Test
    public void purchaseOneImportedBoxChocolatesOneImportedPerfumeTotal() throws Exception{

        addOneImportedBoxChocolatesOneImportedPerfume();

        checkTotal(65.15);
    }

    private void addOneImportedBoxChocolatesOneImportedPerfume() throws Exception
    {
        receipt.addItem(1, "imported box of chocolates", 10.00);
        receipt.addItem(1, "imported bottle of perfume", 47.50);
    }

    /*
     * SalesTaxes test. The item list is:
     * an imported bottle of perfume,
     * a bottle of perfume,
     * a packet of pills
     * a box of imported chocolates
     */
    @Test
    public void purchaseOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateSalesTaxes() throws Exception
    {
        addOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();

        checkSalesTaxes(6.70);
    }

    /*
     * Total test. The item list is:
     * an imported bottle of perfume,
     * a bottle of perfume,
     * a packet of pills
     * a box of imported chocolates
     */
    @Test
    public void purchaseOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateTotal() throws Exception
    {
        addOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();

        checkTotal(74.68);
    }

    private void addOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate() throws Exception
    {
        receipt.addItem(1, "imported bottle of perfume", 27.99);
        receipt.addItem(1, "bottle of perfume", 18.99);
        receipt.addItem(1, "packet of headache pills", 9.75);
        receipt.addItem(1, "box of imported chocolates", 11.25);
    }

    /*
     * SalesTaxes test. The item list is:
     * two imported bottle of perfume,
     * a bottle of perfume,
     * a packet of pills
     * a box of imported chocolates
     */
    @Test
    public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateSalesTaxes() throws Exception
    {
        addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();

        checkSalesTaxes(10.90);
    }

    /*
     * Total test. The item list is:
     * two imported bottle of perfume,
     * a bottle of perfume,
     * a packet of pills
     * a box of imported chocolates
     */
    @Test
    public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateTotal() throws Exception
    {
        addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();

        checkTotal(106.87);
    }

    /*
     * Print test. The item list is:
     * two imported bottle of perfume,
     * a bottle of perfume,
     * a packet of pills
     * a box of imported chocolates
     */
    @Test
    public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolatePrint() throws Exception
    {
        addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();

        receipt.calculate();

        List<String> items = receipt.print();

        assertTrue("First item doesn't contain 2",items.get(0).contains("2"));
        assertTrue("First item doesn't contain imported bottle of perfume",items.get(0).contains("imported bottle of perfume"));
        assertTrue("First item doesn't contain 64.38",items.get(0).contains("64.38"));

        assertTrue("Second item doesn't contain 1",items.get(1).contains("1"));
        assertTrue("Second item doesn't contain bottle of perfume",items.get(1).contains("bottle of perfume"));
        assertTrue("Second item doesn't contain 20.89",items.get(1).contains("20.89"));

        assertTrue("Third item doesn't contain 1",items.get(2).contains("1"));
        assertTrue("Third item doesn't contain packet of headache pills",items.get(2).contains("packet of headache pills"));
        assertTrue("Third item doesn't contain 9.75",items.get(2).contains("9.75"));

        assertTrue("Fourth item doesn't contain 1",items.get(3).contains("1"));
        assertTrue("Fourth item doesn't contain box of imported chocolates",items.get(3).contains("box of imported chocolates"));
        assertTrue("Fourth item doesn't contain 11.85",items.get(3).contains("11.85"));
    }

    private void addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate() throws Exception
    {
        receipt.addItem(2, "imported bottle of perfume", 27.99);
        receipt.addItem(1, "bottle of perfume", 18.99);
        receipt.addItem(1, "packet of headache pills", 9.75);
        receipt.addItem(1, "box of imported chocolates", 11.25);
    }

    private void checkSalesTaxes(double expectedSalesTaxes)
    {
        receipt.calculate();

        assertEquals(expectedSalesTaxes, receipt.getSalesTaxes(), 0.0);
    }

    private void checkTotal(double expectedTotal)
    {
        receipt.calculate();

        assertEquals(expectedTotal, receipt.getTotal(), 0.0);
    }
}
