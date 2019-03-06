public class Emg {
    private long timestamp;
    private int emg1;
    private int emg2;
    private int emg3;
    private int emg4;
    private int emg5;
    private int emg6;
    private int emg7;
    private int emg8;
    private boolean isFatigued;

    public Emg(long timestamp, int emg1, int emg2, int emg3, int emg4, int emg5, int emg6, int emg7, int emg8, boolean isFatigued) {
        this.timestamp = timestamp;
        this.emg1 = emg1;
        this.emg2 = emg2;
        this.emg3 = emg3;
        this.emg4 = emg4;
        this.emg5 = emg5;
        this.emg6 = emg6;
        this.emg7 = emg7;
        this.emg8 = emg8;
        this.isFatigued = isFatigued;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getEmg1() {
        return emg1;
    }

    public int getEmg2() {
        return emg2;
    }

    public int getEmg3() {
        return emg3;
    }

    public int getEmg4() {
        return emg4;
    }

    public int getEmg5() {
        return emg5;
    }

    public int getEmg6() {
        return emg6;
    }

    public int getEmg7() {
        return emg7;
    }

    public int getEmg8() {
        return emg8;
    }

    public boolean isFatigued() {
        return isFatigued;
    }

    public String getEmgSignal(){
        return this.timestamp + "," + this.emg1
                + "," + this.emg2 + "," + this.emg3
                + "," + this.emg4 + "," + this.emg5
                + "," + this.emg6 + "," + this.emg7
                + "," + this.emg8 + "," + this.isFatigued;
    }
}
