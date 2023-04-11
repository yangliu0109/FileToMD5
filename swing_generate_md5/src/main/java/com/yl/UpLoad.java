package com.yl;

import cn.hutool.crypto.digest.DigestUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpLoad {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JTextField statusLabel;
    private JPanel controlPanel;
    private JLabel md5Label;

    private JPanel jPanel;

    private JPanel jPanel2;
    private JTextField md5Value = new JTextField();


    public UpLoad() {
        prepareGUI();
    }

    public static void main(String[] args) {
        UpLoad swingControlDemo = new UpLoad();
        swingControlDemo.showFileChooserDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Md5生成器");
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JTextField();
//        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());

        md5Label = new JLabel();
        md5Label.setText("MD5的值为：");

        jPanel.add(md5Label);

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(jPanel);
        mainFrame.setVisible(true);
    }

    private void showFileChooserDemo() {
//        headerLabel.setText("欢迎使用Md5生成器");
        JLabel filePath = new JLabel();
        final JFileChooser fileDialog = new JFileChooser();
        JButton showFileDialogButton = new JButton("选择文件...");

        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(mainFrame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
                    filePath.setText(file.getAbsolutePath());
                    String md5 = DigestUtil.md5Hex(file);
                    md5Value.setText(md5);
                } else {
                    statusLabel.setText("用户取消打开文件");
                }
            }
        });
        jPanel.add(md5Value);
        controlPanel.add(filePath);
        controlPanel.add(showFileDialogButton);
        mainFrame.setVisible(true);
    }
}

