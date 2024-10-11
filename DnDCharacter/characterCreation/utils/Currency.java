package utils;

/**
 * Enum que representa diferentes tipos de monedas y sus equivalencias.
 */
public enum Currency {
    COPPER(1),      // 1 cobre
    SILVER(100),    // 1 plata = 100 cobre
    GOLD(10000),    // 1 oro = 10000 cobre
    PLATINUM(1000000); // 1 platino = 1000000 cobre

    private final int valueInCopper; // Valor de la moneda en cobre

    // Constructor
    Currency(int valueInCopper) {
        this.valueInCopper = valueInCopper;
    }

    /**
     * Convierte una cantidad de una moneda a otra.
     *
     * @param amount La cantidad de la moneda de origen.
     * @param from La moneda de origen.
     * @param to La moneda de destino.
     * @return La cantidad convertida a la moneda de destino.
     */
    public static double convert(double amount, Currency from, Currency to) {
        // Convertir a cobre y luego a la moneda de destino
        double amountInCopper = amount * from.valueInCopper;
        return amountInCopper / to.valueInCopper;
    }
}