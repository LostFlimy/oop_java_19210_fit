package factory.workers;


public class TimeSettingsForWorkers {
    private final int timeForAccesory;
    private final int timeForBody;
    private final int timeForMotor;
    private final int timeForMachine;
    public TimeSettingsForWorkers() {
        timeForAccesory = 3;
        timeForBody = 3;
        timeForMachine = 9;
        timeForMotor = 6;
    }
    public int getTimeForAccesory() {
        return timeForAccesory;
    }
    public int getTimeForMotor() {
        return timeForMotor;
    }
    public int getTimeForBody() {
        return timeForBody;
    }
    public int getTimeForMachine() {
        return timeForMachine;
    }
}
