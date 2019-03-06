import java.awt.event.*;
import java.io.IOException;

public class StartButtonListener implements ActionListener {
    EmgDataWriteListener dataListener;

    StartButtonListener(EmgDataWriteListener dataListener) {
        this.dataListener = dataListener;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            dataListener.recordData();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
