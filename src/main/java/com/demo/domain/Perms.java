package com.demo.domain;

public class Perms {
    private int pid;
    private String perm;
    private int rid;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "Perms{" +
                "pid=" + pid +
                ", perm='" + perm + '\'' +
                ", rid=" + rid +
                '}';
    }
}
