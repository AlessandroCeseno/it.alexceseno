package it.alexceseno.util;

public class MathUtils
{
    public static double roundTwoDecimals(double cost)
    {
        return (double)Math.round(cost * 100) / 100;
    }
}
