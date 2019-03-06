import javax.swing.*;
import java.awt.*;

public class MyoFrame extends JFrame {
    private JPanel panel;
    private JButton startButton;
    private JButton markFailureButton;
    private JButton stopButton;
    EmgDataWriteListener dataListener;

    public MyoFrame(EmgDataWriteListener dataListener) {
        super("Myo EMG Data");
        this.dataListener = dataListener;
        initializeGui();
        initializeActionListeners();
    }

    public void initializeGui(){
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel.setOpaque(true);
        this.setContentPane(panel);

        this.startButton = new JButton("Start");
        this.markFailureButton = new JButton("Mark Failure");
        this.stopButton = new JButton("Stop");

        this.panel.add(this.startButton);
        this.panel.add(this.markFailureButton);
        this.panel.add(this.stopButton);

        //pack();
        this.setBounds(100,100,400, 400);
        this.setVisible(true);
        this.panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
    }

    public void initializeActionListeners(){
        this.startButton.addActionListener(new StartButtonListener(dataListener));
        this.markFailureButton.addActionListener(new MarkFailureButtonListener(dataListener));
        this.stopButton.addActionListener(new StopButtonListener(dataListener));
    }
}
