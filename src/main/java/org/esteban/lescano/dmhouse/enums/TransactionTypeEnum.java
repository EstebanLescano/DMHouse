package org.esteban.lescano.dmhouse.enums;

/***
 * En este caso es un ENUMERADO con numeracion default En JAVA. Estos comienzan
 * desde 0 y si intercambiamos el orden el 0 pasa a ser siempre el primero. Si
 * quisieramos tener uno customizado, en JAVA es mas complejo(se ahoga en un
 * vaso de agua)
 * estos enum debria hacerlos en otra clase que sea exclusiva
 */
public enum TransactionTypeEnum {
    outgoing, // Este es siempre 0
    incoming // Este es siempre 1
}
