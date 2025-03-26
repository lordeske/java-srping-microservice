package com.proizvod;

public class ProizvodKupovinaException extends RuntimeException {
    public ProizvodKupovinaException(String nekogProizvodaNemaNaStanju) {
        super(nekogProizvodaNemaNaStanju);
    }
}
