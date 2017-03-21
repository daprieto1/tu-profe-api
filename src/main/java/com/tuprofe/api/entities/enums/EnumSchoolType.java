package com.tuprofe.api.entities.enums;

/**
 *
 * @author diego
 */
public enum EnumSchoolType {

    UNIVERSITY(1, "Universidad"),
    SCHOOL(2, "Colegio");

    private int id;
    private String value;

    private EnumSchoolType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return this.id;
    }
}
