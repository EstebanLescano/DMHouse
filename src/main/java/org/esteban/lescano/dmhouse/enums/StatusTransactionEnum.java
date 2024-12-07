package org.esteban.lescano.dmhouse.enums;

/***
 * En este caso es un ENUMERADO con numeracion customizada En JAVA, los
 * enumerados con numeros customizados deben tener un constructor y un
 * comparador para poder funcionar correctamente
 */
// Este es un ejemplo de enumerado de estados customizados.
public enum StatusTransactionEnum {
    PENDING(0),
    SEND(1),
    RECEIVED(2),
    EXECUTED(4),
    MISSING_FUNDS(80),
    GENERAL_ERROR(99);

    private final int value;

    // NOTE: Enum constructor tiene que estar en privado
    private StatusTransactionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StatusTransactionEnum parse(int id) {
        StatusTransactionEnum status = null; // Default
        for (StatusTransactionEnum item : StatusTransactionEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}
