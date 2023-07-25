import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Word {

    private JFrame frame;
    private Result[] res = new Result[10000];
    private int count = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        EventQueue.invokeLater(() -> {

            try {
                Word window = new Word();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public Word() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
                "D:\\JAVAProject\\WordCount\\src\\ico\\ABCD.png"));
        frame.setTitle("单词计数器");
        frame.setResizable(false);
        frame.setBounds(550, 100, 645, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlatLightLaf.install();

        JPanel panel = new JPanel();
        panel.setBounds(550, 100, 604, 834);
        panel.setLayout(null);
        frame.add(panel);

        JMenuBar menuBar = new JMenuBar();
        //创建菜单
        JMenu registeMenu = new JMenu("关于(N)");
        registeMenu.setFont(new Font("等线", Font.BOLD, 16));
        //设置热键
        registeMenu.setMnemonic(KeyEvent.VK_N);
        //创建、并向菜单添加菜单项
        JMenuItem jmiRegiste_1, jmiRegiste_2;
        registeMenu.add(jmiRegiste_1 = new JMenuItem("关于作者(N)"));
        jmiRegiste_1.setFont(new Font("等线", Font.BOLD, 14));
        jmiRegiste_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

        registeMenu.add(jmiRegiste_2 = new JMenuItem("关于APP(C)"));
        jmiRegiste_2.setFont(new Font("等线", Font.BOLD, 14));
        jmiRegiste_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        //将菜单添加到菜单栏
        menuBar.add(registeMenu);
        frame.setJMenuBar(menuBar);

        jmiRegiste_1.addActionListener(e -> {
            new AboutAuthor().setVisible(true);
        });

        jmiRegiste_2.addActionListener(e -> {
            new AboutApp().setVisible(true);
        });
        JProgressBar progressBar = new JProgressBar();
        progressBar.setFont(new Font("等线", Font.BOLD, 20));
        progressBar.setForeground(new Color(0, 0, 0));
        progressBar.setBounds(10, 276, 570, 25);
        progressBar.setIndeterminate(false);
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        panel.add(progressBar);
        //frame.getContentPane().add(progressBar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 600, 256);
        //frame.getContentPane().add(scrollPane);
        panel.add(scrollPane);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Schoolbook", Font.PLAIN, 25));
        scrollPane.setViewportView(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 309, 259, 427);
        scrollPane_1.setBorder(BorderFactory.createEmptyBorder()); // 设置滚动窗格的边框为空边框
        panel.add(scrollPane_1);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setEditable(false);
        textArea_1.setFont(new Font("Century Schoolbook", Font.PLAIN, 25));
        scrollPane_1.setViewportView(textArea_1);
        textArea_1.setLineWrap(true);
        textArea_1.setWrapStyleWord(true);
        textArea_1.setBorder(null);

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setFont(new Font("等线", Font.PLAIN, 25));
        lblNewLabel.setBounds(10, 757, 259, 30);
        //frame.getContentPane().add(lblNewLabel);
        panel.add(lblNewLabel);

        JButton btnNewButton = new JButton("开始计数");
        btnNewButton.setFont(new Font("等线", Font.PLAIN, 20));
        btnNewButton.addActionListener(e -> {
            if (textArea.getText().equals("")) {
                JOptionPane.showMessageDialog(btnNewButton, "内容为空");
            } else {
                progressBar.setVisible(true);
                new Progress(progressBar, btnNewButton).start();

                String text = textArea.getText();

                // 获取text中的所有单词
                String[] words = text.split("[.,:— ]+");
                for (int i = 0; i < words.length; i++)
                    words[i] = words[i].toLowerCase();

                // 为每个单词设置统计标识：true代表已统计，false代表未统计
                boolean[] flags = new boolean[words.length];

                // 统计每个单词出现的次数
                for (int i = 0; i < words.length; i++) {
                    // 统计words[i]在后续元素中出现的次数
                    if (!flags[i]) {// 尚未被统计过
                        int times = 1;
                        for (int j = i + 1; j < words.length; j++) {
                            if (!flags[j] && words[i].equals(words[j])) {
                                times++;
                                flags[j] = true;
                            }
                        }
                        // 经统计结果写入Result[]数组
                        res[count++] = new Result(words[i], times);
                    }
                }
            }
        });
        btnNewButton.setBounds(415, 378, 120, 41);
        //frame.getContentPane().add(btnNewButton);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("打印结果");
        btnNewButton_1.setFont(new Font("等线", Font.PLAIN, 20));
        btnNewButton_1.addActionListener(e -> {
            if (textArea.getText().equals("")) {
                JOptionPane.showMessageDialog(btnNewButton_1, "内容为空");
            } else {
                progressBar.setVisible(false);
                // 将统计结果数组中的非null元素复制到临时数组
                Result[] tmp = new Result[count];
                tmp = Arrays.copyOfRange(res, 0, count);

                // 排序
                Arrays.sort(tmp);

                // 输出结果
                for (int i = 0; i < count; i++) {
                    textArea_1.append(tmp[i].getWords() + ":" + tmp[i].getTimes() + "\n");
                    // System.out.println(tmp[i].getWords() + ":" + tmp[i].getTimes());

                }
                int s = 0;
                for (int j = 0; j < count; j++) {
                    s += tmp[j].getTimes();
                }
                // System.out.println("共有单词数："+s);
                lblNewLabel.setText("共有单词数：" + s);

            }
        });
        btnNewButton_1.setBounds(415, 464, 120, 41);
        //frame.getContentPane().add(btnNewButton_1);
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("重置");
        btnNewButton_2.setFont(new Font("等线", Font.PLAIN, 20));
        btnNewButton_2.addActionListener(e -> {
            frame.dispose();
            new Word().frame.setVisible(true);
        });
        btnNewButton_2.setBounds(415, 539, 120, 41);
        //frame.getContentPane().add(btnNewButton_2);
        panel.add(btnNewButton_2);

        JComboBox<String> themeComboBox = new JComboBox<>(new String[]{"Light", "Dark", "IntelliJ", "Darcula"});
        themeComboBox.addActionListener(e -> {
            String theme = (String) themeComboBox.getSelectedItem();
            if ("Light".equals(theme)) {
                FlatLightLaf.install();
            } else if ("Dark".equals(theme)) {
                FlatDarkLaf.install();
            } else if ("IntelliJ".equals(theme)) {
                FlatIntelliJLaf.install();
            } else if ("Darcula".equals(theme)) {
                FlatDarculaLaf.install();
            }
            // Update UI
            SwingUtilities.updateComponentTreeUI(frame);
        });
        themeComboBox.setBounds(415, 614, 120, 41);
        panel.add(themeComboBox);

        JComboBox<String> fontComboBox = new JComboBox<>(new String[]{"Agency FB", "Algerian", "Arial", "Arial Black", "Arial Narrow", "Arial Rounded MT Bold", "Bahnschrift", "Baskerville Old Face", "Bauhaus 93", "Bell MT", "Berlin Sans FB", "Berlin Sans FB Demi", "Bernard MT Condensed", "Blackadder ITC", "Bodoni MT", "Bodoni MT Black", "Bodoni MT Condensed", "Bodoni MT Poster Compressed", "Book Antiqua", "Bookman Old Style", "Bookshelf Symbol 7", "Bradley Hand ITC", "Britannic Bold", "Broadway", "Brush Script MT", "Calibri", "Calibri Light", "Californian FB", "Calisto MT", "Cambria", "Cambria Math", "Candara", "Candara Light", "Castellar", "Centaur", "Century", "Century Gothic", "Century Schoolbook", "Chiller", "Colonna MT", "Comic Sans MS", "Consolas", "Constantia", "Cooper Black", "Copperplate Gothic Bold", "Copperplate Gothic Light", "Corbel", "Corbel Light", "Courier New", "Curlz MT", "Dialog", "DialogInput", "DIN Next LT Pro", "DIN Next LT Pro Light", "DIN Next LT Pro Medium", "Dubai", "Dubai Light", "Dubai Medium", "Ebrima", "Edwardian Script ITC", "Elephant", "Engravers MT", "Eras Bold ITC", "Eras Demi ITC", "Eras Light ITC", "Eras Medium ITC", "Felix Titling", "Footlight MT Light", "Forte", "Franklin Gothic Book", "Franklin Gothic Demi", "Franklin Gothic Demi Cond", "Franklin Gothic Heavy", "Franklin Gothic Medium", "Franklin Gothic Medium Cond", "Freestyle Script", "French Script MT", "Gabriola", "Gadugi", "Garamond", "Georgia", "Gigi", "Gill Sans MT", "Gill Sans MT Condensed", "Gill Sans MT Ext Condensed Bold", "Gill Sans Ultra Bold", "Gill Sans Ultra Bold Condensed", "Gloucester MT Extra Condensed", "Goudy Old Style", "Goudy Stout", "Haettenschweiler", "Harlow Solid Italic", "Harrington", "Heebo", "High Tower Text", "HoloLens MDL2 Assets", "icomoon", "Impact", "Imprint MT Shadow", "Informal Roman", "Ink Free", "Javanese Text", "Jokerman", "Juice ITC", "Kristen ITC", "Kunstler Script", "Leelawadee", "Leelawadee UI", "Leelawadee UI Semilight", "Lucida Bright", "Lucida Calligraphy", "Lucida Console", "Lucida Fax", "Lucida Handwriting", "Lucida Sans", "Lucida Sans Typewriter", "Lucida Sans Unicode", "Magneto", "Maiandra GD", "Malgun Gothic", "Malgun Gothic Semilight", "Marlett", "Matura MT Script Capitals", "Microsoft Himalaya", "Microsoft JhengHei", "Microsoft JhengHei Light", "Microsoft JhengHei UI", "Microsoft JhengHei UI Light", "Microsoft New Tai Lue", "Microsoft PhagsPa", "Microsoft Sans Serif", "Microsoft Tai Le", "Microsoft Uighur", "Microsoft YaHei UI", "Microsoft YaHei UI Light", "Microsoft Yi Baiti", "MingLiU-ExtB", "MingLiU_HKSCS-ExtB", "Mistral", "Modern No. 20", "Mongolian Baiti", "Monospaced", "Monotype Corsiva", "MS Gothic", "MS Outlook", "MS PGothic", "MS Reference Sans Serif", "MS Reference Specialty", "MS UI Gothic", "MV Boli", "Myanmar Text", "Niagara Engraved", "Niagara Solid", "Nirmala UI", "Nirmala UI Semilight", "NumberOnly", "OCR A Extended", "Old English Text MT", "Onyx", "Palace Script MT", "Palatino Linotype", "Papyrus", "Parchment", "Perpetua", "Perpetua Titling MT", "Playbill", "PMingLiU-ExtB", "Poor Richard", "Pristina", "Rage Italic", "Ravie", "Rockwell", "Rockwell Condensed", "Rockwell Extra Bold", "SansSerif", "Script MT Bold", "Segoe MDL2 Assets", "Segoe Print", "Segoe Script", "Segoe UI", "Segoe UI Black", "Segoe UI Emoji", "Segoe UI Historic", "Segoe UI Light", "Segoe UI Semibold", "Segoe UI Semilight", "Segoe UI Symbol", "Serif", "Showcard Gothic", "SimSun-ExtB", "Sitka Banner", "Sitka Display", "Sitka Heading", "Sitka Small", "Sitka Subheading", "Sitka Text", "Snap ITC", "Source Code Pro", "Stencil", "Sylfaen", "Symbol", "Tahoma", "Tempus Sans ITC", "Times New Roman", "Trebuchet MS", "Tw Cen MT", "Tw Cen MT Condensed", "Tw Cen MT Condensed Extra Bold", "Verdana", "Viner Hand ITC", "Vivaldi", "Vladimir Script", "Webdings", "Wide Latin", "Wingdings", "Wingdings 2", "Wingdings 3", "Yu Gothic", "Yu Gothic Light", "Yu Gothic Medium", "Yu Gothic UI", "Yu Gothic UI Light", "Yu Gothic UI Semibold", "Yu Gothic UI Semilight", "仿宋", "华文中宋", "华文仿宋", "华文宋体", "华文彩云", "华文新魏", "华文楷体", "华文琥珀", "华文细黑", "华文行楷", "华文隶书", "宋体", "幼圆", "微软雅黑", "微软雅黑 Light", "恅隋棉庥戛潠翷", "文鼎粗魏碑简", "新宋体", "方正姚体", "方正粗黑宋简体", "方正舒体", "楷体", "爱奇艺黑体", "爱奇艺黑体 Black", "爱奇艺黑体 Medium", "等线", "等线 Light", "隶书", "黑体"});
        fontComboBox.addActionListener(e -> {
            textArea.setFont(new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, 20));
            textArea_1.setFont(new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, 25));
        });
        fontComboBox.setBounds(415, 689, 120, 41);
        panel.add(fontComboBox);

    }

    class Progress extends Thread {
        private int[] num = {1, 20, 40, 50, 80, 90, 100};
        private JProgressBar bar;

        public Progress(JProgressBar progressBar, JButton button) {
            this.bar = progressBar;
        }

        public void run() {

            bar.setStringPainted(true);
            bar.setIndeterminate(false);// 采用确定的进度条样式
            for (int j : num) {
                try {
                    bar.setValue(j);
                    Thread.sleep(100);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
            bar.setString("计数完成！");
        }
    }
}
