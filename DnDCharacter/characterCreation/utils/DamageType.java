package utils;

/**
 * Enum que representa los diferentes tipos de daño que pueden causar las armas.
 */
public enum DamageType {
    BLUDGEONING("Contundente"), // Daño contundente
    PIERCING("Perforante"),      // Daño perforante
    SLASHING("Cortante"),       // Daño cortante
    FIRE("Fuego"),               // Daño por fuego
    COLD("Frío"),                // Daño por frío
    LIGHTNING("Rayos"),         // Daño por rayos
    ACID("Ácido"),               // Daño por ácido
    POISON("Veneno"),            // Daño por veneno
    RADIANT("Radiante"),         // Daño radiante
    NECROTIC("Necrotico"),      // Daño necrótico
    FORCE("Fuerza"),            // Daño de fuerza
    THUNDER("Trueno"),          // Daño por trueno
    PSYCHIC("Psíquico");        // Daño psíquico

    private final String description; // Descripción del tipo de daño

    // Constructor
    DamageType(String description) {
        this.description = description;
    }

    /**
     * Obtiene la descripción del tipo de daño.
     *
     * @return La descripción del tipo de daño.
     */
    public String getDescription() {
        return description;
    }
}
