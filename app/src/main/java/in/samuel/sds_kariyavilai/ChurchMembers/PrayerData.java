package in.samuel.sds_kariyavilai.ChurchMembers;

public class PrayerData {
    private String MemName,MemPrayer,MemDate;



    public PrayerData(){

    }
    public PrayerData(String mname, String mprayer, String mdate) {
        MemName = mname;
        MemPrayer = mprayer;
        MemDate = mdate;
    }

    public String getMemName() {
        return MemName;
    }

    public void setMemName(String memName) {
        MemName = memName;
    }

    public String getMemPrayer() {
        return MemPrayer;
    }

    public void setMemPrayer(String memPrayer) {
        MemPrayer = memPrayer;
    }

    public String getMemDate() {
        return MemDate;
    }

    public void setMemDate(String memDate) {
        MemDate = memDate;
    }
}
