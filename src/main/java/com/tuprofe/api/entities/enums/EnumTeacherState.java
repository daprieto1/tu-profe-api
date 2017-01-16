package com.tuprofe.api.entities.enums;

/**
 *
 * @author diego
 */
public enum EnumTeacherState {

    SIGN_UP(0, "signUp"),
    CURRICULUM(1, "curriculum"),
    INTERVIEW(2, "interview"),
    INACTIVE(3, "inactive"),
    ACTIVE(4, "active"),
    REJECTED(5, "rejected");

    private int id;
    private String value;

    private EnumTeacherState(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return this.id;
    }
}
