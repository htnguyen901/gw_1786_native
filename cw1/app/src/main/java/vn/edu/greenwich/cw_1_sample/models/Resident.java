package vn.edu.greenwich.cw_1_sample.models;

import java.io.Serializable;

public class Resident implements Serializable {
    protected long _id;
    protected String _Name_of_trip;
    protected String _Destination;
    protected String _Description;
    protected String _Other;
    protected String _end_date_of_trip;
    protected String _start_date_of_trip;
    protected int _Requires_risk_assessmen;

    public Resident() {
        _id = -1;
        _Other = null;
        _end_date_of_trip = null ;
        _Name_of_trip= null;
        _Destination = null;
        _Description = null;
        _start_date_of_trip= null;
        _Requires_risk_assessmen= -1;
    }

    public Resident(long id, String Name_of_trip, String startDate, int owner,String Description,String Destination,String Other, String endDate) {
        _id = id;
        _Name_of_trip= Name_of_trip;
        _Destination= Destination;
        _Description= Description;
        _Other= Other;
        _end_date_of_trip= endDate;
        _start_date_of_trip= startDate;
        _Requires_risk_assessmen= owner;
    }

    public long getId() { return _id; }
    public void setId(long id) {
        _id = id;
    }

    public String getName_of_trip() {
        return _Name_of_trip;
    }
    public void setName_of_trip(String Name_of_trip) {
        _Name_of_trip= Name_of_trip;
    }
    public String getDestination() {
        return _Destination;
    }
    public void setDestination(String Destination) {
        _Destination= Destination;
    }
    public String getDescription() {
        return _Description;
    }
    public void setDescription(String Description) {
        _Description= Description;
    }

    public String getStartDate() {
        return _start_date_of_trip;
    }
    public void setStartDate(String startDate) {
        _start_date_of_trip= startDate;
    }
    public String getEndDate() {
        return _end_date_of_trip;
    }
    public void setEndDate(String endDate) {
        _end_date_of_trip= endDate;
    }

    public String getOther() {
        return _Other;
    }
    public void setOther(String Other) {
        _Other= Other;
    }

    public int getRequiresRisk() {
        return _Requires_risk_assessmen;
    }
    public void setRequiresRisk(int owner) {
        _Requires_risk_assessmen= owner;
    }

    public boolean isEmpty() {
        if (-1 == _id && null == _Name_of_trip&& null==_Description && null ==_Destination && null == _Other && null == _end_date_of_trip && null == _start_date_of_trip&& -1 == _Requires_risk_assessmen)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "[" + _start_date_of_trip+ "] " + _Name_of_trip + _Destination + _Description + _Other + "[" + _end_date_of_trip+ "] ";
    }
}