package com.fortune.model;

/**
 * @author fchidzikwe
 */
public enum ClientType {

    COMPANY("COMPANY"), INDIVIDUAL("INDIVIDUAL");

    private final String name;

    ClientType(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
