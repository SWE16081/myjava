package com.example.demo.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRoleList2 extends User{
     private Set<Role> role=new HashSet<>();

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
