package utils;

public enum Divisa {//Para coger el valor del nombre del enum usado se usa la funcion .name()
    PC(1),
    PP(10),
    PE(50),
    PO(100),
    PPT(1000);

    private final int valorPC;

    Divisa(int valorPC) {
        this.valorPC = valorPC;
    }

    public int getValorPC() {
        return valorPC;
    }
    
}