package Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame{
    protected static Sound BGM = new Sound();
    protected static JButton musicBtn;
    protected JLabel welcome;
    protected JButton startBtn;
    protected static JButton muteBtn;
    protected JButton exitBtn;
    protected BufferedImage bufferedImage;
    protected static Board board;

    protected Image image;
    public MainFrame() {

        setTitle("Monopoly");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("img/Background_Start.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = bufferedImage.getScaledInstance(1536, 864, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);

        welcome = new JLabel("WELCOME TO MONOPOLY!");
        welcome.setHorizontalAlignment(SwingConstants.LEFT);
        welcome.setBounds(550, 45, 700, 100);
        welcome.setFont(new Font("Comic Sans", Font.BOLD, 50));


        startBtn = new JButton("START");
        startBtn.setBounds(700, 400, 200, 75);
        startBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        startBtn.setIconTextGap(-15);
        startBtn.setIcon(new ImageIcon("/img/btn.jpg"));
        startBtn.setFocusable(false);
        startBtn.addActionListener(e -> {
            this.dispose();
            BGM.btnSFX("Sound/click.wav");
            board = new Board();
        });

        muteBtn = new JButton("MUTE");
        muteBtn.setBounds(700, 600, 200, 75);
        muteBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        muteBtn.setIconTextGap(-15);
        muteBtn.setFocusable(false);
        muteBtn.addActionListener(e -> {
                BGM.btnSFX("Sound/click.wav");
                if (muteBtn.getText().equals("MUTE")) {
                    muteBtn.setText("UNMUTE");
                    BGM.muteBGM();
                } else if (muteBtn.getText().equals("UNMUTE")) {
                    muteBtn.setText("MUTE");
                }
        });


        musicBtn = new JButton("MUSIC ON");
        musicBtn.setBounds(700, 500, 200, 75);
        musicBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        musicBtn.setIconTextGap(-15);
        musicBtn.setFocusable(false);
        musicBtn.addActionListener(e -> {
            BGM.playBGM();
            musicBtn.setEnabled(false);
        });

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(1300, 700, 200, 75);
        exitBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        exitBtn.setIconTextGap(-15);
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(e -> {
            if (e.getSource() == exitBtn) {
                BGM.btnSFX("Sound/click.wav");
                MainFrame.this.dispose();
            }
        });

        add(startBtn);
        add(musicBtn);
        add(muteBtn);
        add(exitBtn);
        add(welcome);
        add(jLabel);
        setVisible(true);
        setResizable(false);
    }
}