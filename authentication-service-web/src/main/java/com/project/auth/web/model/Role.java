/*
 * 26/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.model;

public enum Role {

    ADMIN(1),
    PM(2),
    DEV(3),
    USER(4);

    private int ordinal;

    Role(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public static Role getRole(int ordinal){
        for(Role role : values()){
            if(role.getOrdinal() == ordinal){
                return role;
            }
        }
        return null;
    }
}
