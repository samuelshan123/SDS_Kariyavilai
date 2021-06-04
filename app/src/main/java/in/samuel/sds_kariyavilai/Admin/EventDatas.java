package in.samuel.sds_kariyavilai.Admin;

public class EventDatas {
    private String EventTitle,EventDescription,EventDate;
public EventDatas(){

}
    public EventDatas(String eventTitle, String eventDescription, String eventDate) {
        EventTitle = eventTitle;
        EventDescription = eventDescription;
        EventDate = eventDate;
    }

    public String getEventTitle() {
        return EventTitle;
    }

    public void setEventTitle(String eventTitle) {
        EventTitle = eventTitle;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }
}
