package gr.codehub.api.model.enumarator;

public enum LevelEnumarator {
    JUNIOR(0),
    MID(1),
    SENIOR(2);

    private int dbValue;

    LevelEnumarator(int dbValue){
        this.dbValue = dbValue;
    }

    public int getDbValue(){
        return dbValue;
    }

}
