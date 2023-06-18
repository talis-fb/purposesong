package imd.ufrn.br.purposesong.entity;

enum Access {
    NORMAL,
    VIP
}

public class User extends ModelDatabaseEntity {
    String name;
    String email;
    String password;

    Access access = Access.NORMAL;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVipUser() {
        return this.access == Access.VIP;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVipUser() {
        this.access = Access.VIP;
    }

    public void setNormalUser() {
        this.access = Access.NORMAL;
    }
}
