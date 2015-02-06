package turtle;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class GuiWindow {

    private JFrame frame;
    private JTextField txtCmd;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiWindow window = new GuiWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GuiWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.03);
        splitPane.setBounds(12, 219, 424, 43);
        frame.getContentPane().add(splitPane);
        
        txtCmd = new JTextField();
        txtCmd.setText("Cmd");
        splitPane.setRightComponent(txtCmd);
        txtCmd.setColumns(10);
        
        JButton btnG = new JButton("G");
        splitPane.setLeftComponent(btnG);
        
        JSplitPane splitPane_1 = new JSplitPane();
        splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane_1.setBounds(12, 12, 424, 195);
        frame.getContentPane().add(splitPane_1);
        
        JLabel lblDisplay = new JLabel("Display");
        splitPane_1.setRightComponent(lblDisplay);
    }
}
