import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.StreamEmgType;
import simulation.HubSimulator;

import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
        EmgDataWriteListener dataListener = new EmgDataWriteListener("emgData" + new Date().getTime() + ".csv");
        new MyoFrame(dataListener);

        HubSimulator hub = new HubSimulator("com.example.HelloMyo");
        Myo myo = hub.waitForMyo(10000);

        try {
            myo.setStreamEmg(StreamEmgType.STREAM_EMG_ENABLED);
        } catch (Exception e) {}

        hub.addListener(dataListener);

        while (true) {
            hub.run(1000 / 20);
        }
    }
}
