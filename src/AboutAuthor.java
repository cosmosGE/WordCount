import javax.swing.*;
import java.awt.*;

public class AboutAuthor extends JFrame {
    public static void main(String[] args) {
        new AboutAuthor().setVisible(true);
    }

    public AboutAuthor(){
        init();
    }

    public void init() {

        setBounds(700,300,400,300);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVAProject\\WordCount\\src\\ico\\Author.png"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(700,300,400,300);
        getContentPane().add(panel);

        JLabel label = new JLabel("Author : Cosmos");
        label.setBounds(30,-50,300,200);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("等线", Font.BOLD, 25));
        panel.add(label);

        JLabel label1 = new JLabel("School : MNNU");
        label1.setBounds(30,0,300,200);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("等线", Font.BOLD, 25));
        panel.add(label1);
    }


}
