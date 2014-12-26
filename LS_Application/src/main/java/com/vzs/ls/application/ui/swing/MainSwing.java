package com.vzs.ls.application.ui.swing;

import com.google.common.base.Stopwatch;
import com.vzs.common.util.log.SingleThreadLogUtil;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.ui.CalaulateMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by byao on 12/20/14.
 */
public class MainSwing {
    String defaultFolder = "D:\\LS_Statistic\\";
    JFrame jFrame = new JFrame("LS Statistic Toll");
    public MainSwing(){
        Toolkit defaultToolKit = Toolkit.getDefaultToolkit();
        Dimension dimension = defaultToolKit.getScreenSize();
        jFrame.setSize(400,100);
        jFrame.setLocationByPlatform(true);
        jFrame.setLocation(getPoint());
        init();
    }

    private Point getPoint(){
        Toolkit defaultToolKit = Toolkit.getDefaultToolkit();
        Dimension dimension = defaultToolKit.getScreenSize();
        Point point = new Point((int)dimension.getWidth()/2-jFrame.getWidth()/2,(int)dimension.getHeight()/2-jFrame.getHeight()/2);
        return point;
    }

    private void showHint(String message){
        final JDialog dialog = new JDialog((Frame)null,"",true);
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.setTitle("Ben");
        dialog.setSize(200, 100);
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(new JLabel(message), BorderLayout.CENTER);
        jp.add(ok,BorderLayout.SOUTH);
        dialog.getContentPane().add(jp);
        dialog.setLocation(getPoint());

        dialog.setVisible(true);



    }

    private void init() {
        JPanel jPanel = new JPanel();
        final JTextField folder = new JTextField(defaultFolder);
        folder.setToolTipText(folder.getText());
        folder.setEditable(false);

        final JButton chooseFolder = new JButton("Choose Folder");
        final JFileChooser fileChooser = new JFileChooser(defaultFolder);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooseFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(jFrame);
                if(result == JFileChooser.APPROVE_OPTION){
                    String filePath = fileChooser.getSelectedFile().toString();
                    folder.setText(filePath);
                    folder.setToolTipText(filePath);
                }
            }
        });



        final JButton go = new JButton("GO");
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stopwatch stopwatch = Stopwatch.createStarted();
                go.setEnabled(false);

                String directory = folder.getText();
                if(!directory.endsWith(File.separator)){
                    directory = directory + File.separator;
                }
                CalaulateMain.start(new InputContext(directory));
                go.setEnabled(true);
                stopwatch.stop();
                showHint("Finished within " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
                SingleThreadLogUtil.log("Used " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
            }
        });

        jPanel.add(folder);
        jPanel.add(chooseFolder);
        jPanel.add(go);

        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String...args){
        new MainSwing();
    }
}
