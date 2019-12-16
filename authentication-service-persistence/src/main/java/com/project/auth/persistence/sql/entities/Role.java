/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.persistence.sql.entities;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends BusinessItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "name")
    private String name;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
