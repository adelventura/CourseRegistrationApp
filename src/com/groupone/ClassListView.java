package com.groupone;

import javax.swing.*;

public class ClassListView extends JPanel {

    JLabel titleLabel;

    public ClassListView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titleLabel = new JLabel("Class List", JLabel.LEFT);
        add(titleLabel);

    }

}

