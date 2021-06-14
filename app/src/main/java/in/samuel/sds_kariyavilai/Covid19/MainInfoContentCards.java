package in.samuel.sds_kariyavilai.Covid19;

public class MainInfoContentCards
{
    String latestValue="";

    public MainInfoContentCards(String latestValue) {
        this.latestValue = latestValue;
    }

    public String  getLatestValue() {
        return latestValue;
    }

    public void setLatestValue(String latestValue) {
        this.latestValue = latestValue;
    }
}
