package factory.workers;


public class TimeSettingsForWorkers {
    private long timeForAccesory;
    private long timeForBody;
    private long timeForMotor;
    private long timeForMachine;
    private long timeForSaleCar;
    public TimeSettingsForWorkers() { // изначально у всех время на выполнение их действия будет 10 секунд(10000 милисекунд)
        timeForAccesory = 2;
        timeForBody = 2;
        timeForMachine = 1;
        timeForMotor = 2;
        timeForSaleCar = 5;
    }
    public long getTimeForAccesory() {
        return timeForAccesory;
    }
    public long getTimeForMotor() {
        return timeForMotor;
    }
    public long getTimeForBody() {
        return timeForBody;
    }
    public long getTimeForMachine() {
        return timeForMachine;
    }
    public long getTimeForSaleCar() {
        return timeForSaleCar;
    }

    public void setTimeForMachine(long timeForMachine) {
        this.timeForMachine = timeForMachine;
    }

    public void setTimeForAccesory(long timeForAccesory) {
        this.timeForAccesory = timeForAccesory;
    }

    public void setTimeForBody(long timeForBody) {
        this.timeForBody = timeForBody;
    }

    public void setTimeForMotor(long timeForMotor) {
        this.timeForMotor = timeForMotor;
    }

    public void setTimeForSaleCar(long timeForSaleCar) {
        this.timeForSaleCar = timeForSaleCar;
    }
}
