package com.yl;

import cn.hutool.crypto.digest.DigestUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class UpLoad {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel md5;
    private JTextField md5Value;
    private JTextField filePathValue;
    private JLabel filePath;
    private JButton showFileDialogButton;
    private JLabel logo;
    private JLabel logoFont;

    String font = "<html><font style=\"font-size:%spx;\">%s</font></html>";

    public UpLoad() {
        prepareGUI();
    }

    public static void main(String[] args) {
        UpLoad swingControlDemo = new UpLoad();
        swingControlDemo.showFileChooserDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Md5生成器");
        mainFrame.setSize(550, 450);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        URL url = UpLoad.class.getResource("/img/logo.png");
        logo = new JLabel(new ImageIcon(url));
        logo.setBounds(20,50,60,60);

        logoFont = new JLabel();
        logoFont.setText(String.format(font, "17", "文件转MD5系统"));
        logoFont.setBounds(100,50,440,60);

        headerLabel = new JLabel("", JLabel.CENTER);
        md5Value = new JTextField();
        md5Value.setBounds(20,280,440,30);

        filePath = new JLabel(String.format(font, "14", "文件路径："));
        filePath.setBounds(20, 170, 140, 20);
        mainFrame.add(filePath);

        filePathValue = new JTextField();
        filePathValue.setBounds(20, 200,400,30);
        mainFrame.add(filePathValue);

        md5 = new JLabel();
        md5.setText(String.format(font, "14","MD5值："));
        md5.setBounds(20,250,100,20);
        mainFrame.add(md5);

        showFileDialogButton = new JButton("...");
        showFileDialogButton.setBounds(430,200,30,30);

        mainFrame.add(logoFont);
        mainFrame.add(logo);
        mainFrame.add(showFileDialogButton);
        mainFrame.add(headerLabel);
        mainFrame.add(md5Value);
        mainFrame.setVisible(true);
    }

    private void showFileChooserDemo() {
        final JFileChooser fileDialog = new JFileChooser();
        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(mainFrame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
                    String md5 = DigestUtil.md5Hex(file);
                    md5Value.setText(md5);
                    filePathValue.setText(file.getAbsolutePath());
                } else {
//                    statusLabel.setText("用户取消打开文件");
                }
            }
        });
        mainFrame.setVisible(true);
    }
}

