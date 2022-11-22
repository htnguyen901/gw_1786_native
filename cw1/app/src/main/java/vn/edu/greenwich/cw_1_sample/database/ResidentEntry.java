package vn.edu.greenwich.cw_1_sample.database;

public class ResidentEntry {
    public static final String TABLE_NAME = "resident";
    public static final String COL_ID = "id";
    public static final String COL_NAME_OF_TRIP = "Name_of_trip";
    public static final String COL_DESTINATION = "Destination";
    public static final String COL_DESCRIPTION = "Discription";
    public static final String COL_OTHER = "Other";
    public static final String COL_END_DATE_OF_TRIP = "Total_Date";
    public static final String COL_START_DATE_OF_TRIP = "start_date_of_trip";
    public static final String COL_REQUIRES_RISK = "Requires_risk_assessmen";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY," +
                    COL_NAME_OF_TRIP + " TEXT NOT NULL," +
                    COL_DESTINATION + " TEXT NOT NULL," +
                    COL_DESCRIPTION + " TEXT NOT NULL," +
                    COL_OTHER + " TEXT NOT NULL," +
                    COL_END_DATE_OF_TRIP + " TEXT NOT NULL," +
                    COL_START_DATE_OF_TRIP + " TEXT NOT NULL," +
                    COL_REQUIRES_RISK + " INTEGER NULL)";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}