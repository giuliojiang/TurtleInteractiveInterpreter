package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    public static MainWindow main()
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
            return frame;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
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
        txtrDisplay.setToolTipText("Output display");
        txtrDisplay.setText("");
        splitPane.setLeftComponent(txtrDisplay);
        PrintStream printStream = new PrintStream(new CustomOutputStream(
            txtrDisplay));
        System.setOut(printStream);
        System.setErr(printStream);
        JScrollPane scroll = new JScrollPane (txtrDisplay, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         splitPane.add(scroll);
         splitPane.setVisible (true);
         txtrDisplay.setFont(new Font("Courier New", Font.PLAIN, 12));
         txtrDisplay.setForeground(Color.BLACK);


        txtCommands = new JTextField();
        txtCommands.setToolTipText("Command prompt field");
        txtCommands.setText("");
        splitPane.setRightComponent(txtCommands);
        txtCommands.setColumns(10);
        TexfFieldStreamer ts = new TexfFieldStreamer(txtCommands);
        txtCommands.addActionListener(ts);
        System.setIn(ts);
        
    }
    
    public void setTxtCommandsText(String s)
    {
        txtCommands.setText(s);
        setTxtCommandsCursorToEnd();
    }
    
    public void setTxtCommandsCursorToEnd()
    {
        txtCommands.setCaretPosition(txtCommands.getDocument().getLength());
    }
    
    public void printDone()
    {
        System.out.println("OK!");
    }
    
    public void closeAndExit()
    {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    public void setWindowTitle(String s)
    {
        this.setTitle(s);
    }
}
