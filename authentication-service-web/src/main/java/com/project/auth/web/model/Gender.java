/*
 * 25/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.model;

public enum Gender {

    FEMALE(1),
    MALE(2),
    OTHER(3);

    private int ordinal;

    Gender(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public static Gender getGender(int ordinal){
            for(Gender gender: values()){
                if(gender.getOrdinal() == ordinal){
                    return gender;
                }
            }

        return null;
    }
}
