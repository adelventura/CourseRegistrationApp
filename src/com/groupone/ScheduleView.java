package com.groupone;

import javax.swing.*;
import java.awt.*;

public class ScheduleView extends JPanel {

    JLabel titleLabel;
    ClassListView classListView;
    JButton dropButton;

    public ScheduleView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Schedule Page", JLabel.LEFT);
        dropButton = new JButton("Drop");
        classListView = new ClassListView();

        add(titleLabel);
        add(classListView);
        add(dropButton);
    }

}
