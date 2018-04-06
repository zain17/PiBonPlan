package security;



public class Sha512 {
    private String salt;
    private String hash;

    public Sha512() {
    }

    public Sha512(String salt) {
        this.salt = salt;
    }

    public Sha512(String salt, String hash) {
        this.salt = salt;
        this.hash = hash;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Sha512{" +
                "salt='" + salt + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
