/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.persistence.sql.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_owner_user")
public class UserOwnerUser extends BusinessItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_owner_user_id")
    private int userOwnerUserId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_owner_id", referencedColumnName = "user_id")
    private User userOwner;

    public int getUserOwnerUserId() {
        return userOwnerUserId;
    }

    public void setUserOwnerUserId(int userOwnerUserId) {
        this.userOwnerUserId = userOwnerUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }
}
