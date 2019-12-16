/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.persistence.sql.entities;

import javax.persistence.*;

@Entity
@Table(name = "gender")
public class Gender extends BusinessItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private int genderId;

    @Column(name = "gender_name")
    private String genderName;

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
