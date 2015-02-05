package gui;

import java.awt.EventQueue;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame
{

    private JPanel contentPane;
    private JTextField txtCommands;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        // EventQueue.invokeLater(new Runnable()
        // {
        // public void run()
        // {
        // try
        // {
        // MainWindow frame = new MainWindow();
        // frame.setVisible(true);
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();
        // }
        // }
        // });

        try
        {
            MainWindow frame = new MainWindow();
            frame.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Create the frame.
     */
    public MainWindow()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 773, 543);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.9);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBounds(12, 12, 747, 494);
        contentPane.add(splitPane);

        JTextArea txtrDisplay = new JTextArea();
        txtrDisplay.setText("");
        splitPane.setLeftComponent(txtrDisplay);
        PrintStream printStream = new PrintStream(new CustomOutputStream(
            txtrDisplay));
        System.setOut(printStream);
        System.setErr(printStream);

        txtCommands = new JTextField();
        txtCommands.setText("Commands");
        splitPane.setRightComponent(txtCommands);
        txtCommands.setColumns(10);
    }
}
