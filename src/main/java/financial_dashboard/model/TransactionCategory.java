package financial_dashboard.model;

public enum TransactionCategory {
//    SALARIO,
//    MERCADO,
//    FARMACIA,
//    RESTAURANTE,
//    CONTA_FIXA,
//    LAZER,
//    GASTOS_COM_PET,
//    COMPRAS_INTERNET


    SALARIO ("salario"),
    MERCADO ("mercado"),
    FARMACIA ("farmacia"),
    RESTAURANTE ("restaurante"),
    CONTA_FIXA ("conta fixa"),
    LAZER ("lazer"),
    GASTOS_COM_PET ("gastos com pet"),
    COMPRAS_INTERNET ("compras internet");


    //Atributos
    private String stringCategory;


    //Construtor
    TransactionCategory(String stringCategory) {
        this.stringCategory = stringCategory;
    }


    //Método estático para associar a String ao Enum
    public static TransactionCategory fromString(String text) {
        for (TransactionCategory category: TransactionCategory.values()) {
            if (category.stringCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException
                ("Nenhuma categoria encontrada para a String fornecida: " + text);
    }


}
