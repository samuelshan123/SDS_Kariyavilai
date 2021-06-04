package in.samuel.sds_kariyavilai;

public class WishmeData {
    private String wname,wmessage,wtime;

    public WishmeData(String wname, String wmessage, String wtime) {
        this.wname = wname;
        this.wmessage = wmessage;
        this.wtime = wtime;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWmessage() {
        return wmessage;
    }

    public void setWmessage(String wmessage) {
        this.wmessage = wmessage;
    }

    public String getWtime() {
        return wtime;
    }

    public void setWtime(String wtime) {
        this.wtime = wtime;
    }
}
