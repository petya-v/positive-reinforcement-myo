import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Myo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmgDataWriteListener extends AbstractDeviceListener implements ActionListener {
    private BufferedWriter writer;
    private FileWriter fileWriter;
    private boolean isFailure;
    private boolean streamClosed;
    private ValuesTransformations valuesTransformations;
    public String fileName;

    EmgDataWriteListener(String fileName) throws IOException {
        this.fileName = fileName;
        this.fileWriter = new FileWriter(this.fileName);
    }

    public void recordData() throws IOException {
        this.writer = new BufferedWriter(fileWriter);
        this.writer.write("timestamp,emg1,emg2,emg3,emg4,emg5,emg6,emg7,emg8,isFailure\n");
    }

    @Override
    public void onEmgData(Myo myo, long timestamp, byte[] emg) {
        super.onEmgData(myo, timestamp, emg);
        /*System.out.println("EMG Received");*/

        if (this.writer == null) {
            /*System.out.println("File not opened yet, ignoring data");*/
            return;
        }

        if (streamClosed){
            return;
        }

        try {
            this.writer.write(timestamp + "," + emg[0] + "," + emg[1] + "," + emg[2] + "," + emg[3] + "," + emg[4] +
                    "," + emg[5] + "," + emg[6] + "," + emg[7] + "," + isFailure + "\n");
            isFailure = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void markFailure(){
        this.isFailure = true;
    }

    public void stopRecordingData() throws IOException {
        valuesTransformations = new ValuesTransformations(fileName);
        //this.writer.close();
        //this.fileWriter.close();
        //this.streamClosed = true;

        valuesTransformations.Rescaling(fileName);
        valuesTransformations.calculateMovingAverage(20,10);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
