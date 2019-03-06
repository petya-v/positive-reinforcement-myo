import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StopButtonListener implements ActionListener {
    EmgDataWriteListener dataListener;

    StopButtonListener(EmgDataWriteListener dataListener){
        this.dataListener = dataListener;
    }


    public void actionPerformed(ActionEvent e) {
        try {
            dataListener.stopRecordingData();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
