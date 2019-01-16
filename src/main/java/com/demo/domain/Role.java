package com.demo.domain;

public class Role {
    private int rid;
    private String role_name;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
