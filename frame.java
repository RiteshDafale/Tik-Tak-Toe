package tiktaktogame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class frame extends JFrame implements ActionListener {

    JLabel heading, clock;
    JPanel mainFrame;
    JButton[] btn = new JButton[9];        // array jo button ko store karage ...
    Font font = new Font("", Font.ITALIC, 40);

    int[] gameChances = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 0;
    int[][] wps = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        {0, 4, 8},
        {2, 4, 6}
    };

    frame() {
        System.out.println("construction called ...");
        setSize(850, 850);
        setTitle("TikTacToo...");
        ImageIcon i = new ImageIcon("E:\\E\\New Folder\\tiktaktoGame\\src\\tiktaktogame\\Images\\icon.jfif");
        setIconImage(i.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        CreateUI();
        setVisible(true);
    }

    private void CreateUI() {
        //    this.setBackground(Color.yellow);
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(new BorderLayout());

        heading = new JLabel("TIC-TAC-TOE");
        heading.setFont(font);
              heading.setForeground(Color.decode("#00FFFF"));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(heading, BorderLayout.NORTH);

        clock = new JLabel("Time Label ..");
        clock.setFont(font);
         clock.setForeground(Color.decode("#00FFFF"));
        clock.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clock, BorderLayout.SOUTH);

        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run successsfully ...");
                try {
                    while (true) {
                        Date date = new Date();
                        String time = date.toLocaleString();
                        clock.setText(time);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
        t.start();

        mainFrame = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            JButton b = new JButton();
            b.setBackground(Color.black);
            b.setFont(font);
            mainFrame.add(b);
            btn[i] = b;
            b.addActionListener(this);
          b.setName(String.valueOf(i));

        }
        this.add(mainFrame, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton CurrentBtn = (JButton) e.getSource();
        System.out.println(CurrentBtn);

        int name = Integer.parseInt(CurrentBtn.getName());

        if (gameChances[name] == 2) {
            if (activePlayer == 1) {
                CurrentBtn.setText("1");
                CurrentBtn.setForeground(Color.red);
                Font font1 = new Font("", Font.BOLD, 100);
                CurrentBtn.setFont(font1);
                gameChances[name] = activePlayer;
                activePlayer = 0;
            } else {
                CurrentBtn.setText("0");
                CurrentBtn.setForeground(Color.white);
                Font font1 = new Font("", Font.BOLD, 100);
                CurrentBtn.setFont(font1);
                gameChances[name] = activePlayer;
                activePlayer = 1;
            }
            // Winner Logic
            for (int[] temp : wps) {
                if ((gameChances[temp[0]] == gameChances[temp[1]]) && (gameChances[temp[1]] == gameChances[temp[2]]) && (gameChances[temp[2]] != 2)) {
                    int winner = gameChances[temp[0]];
                    System.out.println(winner);

                    JOptionPane.showMessageDialog(this, "Congratulation ! Player " + winner + " has won the match ... ");
                    int a = JOptionPane.showConfirmDialog(this, "Do you want to continue ...", "Conform Message", JOptionPane.YES_NO_OPTION);
                    if (a == 0) {
                        this.setVisible(false);
                        new frame();
                    }
                    if(a==1){
                      System.exit(12);
                    }
                    break;

                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Box occupied...");
        }
    }
}
