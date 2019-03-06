package simulation;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Myo;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HubSimulator {
    private Myo myo = null;

    public HubSimulator(String applicationName) {
    }

    public Myo waitForMyo(int waitTime) {
        return this.myo;
    }

    public void run(int ms) {}

    static Random random = new Random();

    public void addListener(final AbstractDeviceListener deviceListener) {
        //1. Simulate connection
        deviceListener.onConnect(null, new Date().getTime(), null);

        //2. Schedule running task:
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                long timeStamp = new Date().getTime();
                byte[] emgData = new byte[8];

                random.nextBytes(emgData);
                // Call it:
                deviceListener.onEmgData(null, timeStamp, emgData);
            }
        }, 1000, 50);
    }


}
