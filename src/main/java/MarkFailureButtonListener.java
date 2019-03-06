import java.awt.event.*;

public class MarkFailureButtonListener implements ActionListener {
    EmgDataWriteListener dataListener;

    MarkFailureButtonListener(EmgDataWriteListener dataListener) {
        this.dataListener = dataListener;
    }

    public void actionPerformed(ActionEvent e) {
        dataListener.markFailure();
    }
}
