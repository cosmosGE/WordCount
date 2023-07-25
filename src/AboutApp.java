import javax.swing.*;
import java.awt.*;

public class AboutApp extends JFrame {
    public static void main(String[] args) {
        new AboutApp().setVisible(true);
    }

    public AboutApp(){
        init();
    }

    public void init(){
        setBounds(600,300,525,300);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVAProject\\WordCount\\src\\ico\\Clapperboard.png"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(600,300,525,300);
        getContentPane().add(panel);

        JLabel label = new JLabel("Original APP");
        label.setBounds(30,-50,400,200);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("等线", Font.BOLD, 25));
        panel.add(label);

        JLabel label1 = new JLabel("Infringement must be prosecuted");
        label1.setBounds(30,0,400,200);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("等线", Font.BOLD, 25));
        panel.add(label1);
    }
}
