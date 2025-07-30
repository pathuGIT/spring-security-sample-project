package com.athome.springTest.dto;

import com.athome.springTest.model.Role;

public class RoleChangeRequest {
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
