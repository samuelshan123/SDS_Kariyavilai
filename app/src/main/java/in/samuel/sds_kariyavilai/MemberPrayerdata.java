package in.samuel.sds_kariyavilai;

public class MemberPrayerdata {
    private String mname,mdesc,mdate;

    public MemberPrayerdata(String mname, String mdesc, String mdate) {
        this.mname = mname;
        this.mdesc = mdesc;
        this.mdate = mdate;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }
}
