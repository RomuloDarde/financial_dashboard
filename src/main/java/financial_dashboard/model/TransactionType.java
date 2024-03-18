package financial_dashboard.model;

public enum TransactionType {
//    DEBITO,
//    CREDITO


    DEBITO ("debito"),
    CREDITO ("credito");

    //Atributos
    private String stringType;

    //Construtor
    TransactionType(String stringType) {
        this.stringType = stringType;
    }


    //Método estático para associar a String ao Enum
    public static TransactionType fromString(String text) {
        for (TransactionType type:TransactionType.values()) {
            if (type.stringType.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException
                ("Nenhum tipo encontrada para a String fornecida: " + text);
    }

}
