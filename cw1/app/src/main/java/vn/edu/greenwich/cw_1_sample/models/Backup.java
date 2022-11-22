package vn.edu.greenwich.cw_1_sample.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Backup implements Serializable {
    protected Date _date;
    protected String _deviceName;
    protected ArrayList<Resident> _residents;
    protected ArrayList<Request> _requests;

    public Backup(Date date, String deviceName, ArrayList<Resident> residents, ArrayList<Request> requests) {
        _date = date;
        _deviceName = deviceName;
        _residents = residents;
        _requests = requests;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public Date getDate() {
        return _date;
    }

    public void setDeviceName(String deviceName) {
        _deviceName = deviceName;
    }

    public String getDeviceName() {
        return _deviceName;
    }

    public void setResidents(ArrayList<Resident> residents) {
        _residents = residents;
    }

    public ArrayList<Resident> getResidents() {
        return _residents;
    }

    public void setRequests(ArrayList<Request> requests) {
        _requests = requests;
    }

    public ArrayList<Request> getRequests() {
        return _requests;
    }
}