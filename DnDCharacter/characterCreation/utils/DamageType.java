package utils;

/**
 * Enum que representa los diferentes tipos de daño que pueden causar las armas.
 */
public enum DamageType {
	
	ACIDO("Ácido"),
	CONTUNDENTE("Contundente"),
	CORTANTE("Cortante"),
	FRIO("Frío"),
	FUEGO("Fuego"),
	FUERZA("Fuerza"),
	NECROTICO("Necrotico"),
	PERFORANTE("Perforante"),
	PSIQUICO("Psíquico"),
	RADIANTE("Radiante"),
	RAYOS("Rayos"),
	TRUENO("Trueno");

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
