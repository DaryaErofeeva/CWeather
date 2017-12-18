package com.ero.cweather.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class DatesController {
    @FXML
    FlowPane flowPane;

    public void setDates(List<String> dates) {
        flowPane.getChildren().clear();
        for (String date : dates)
            flowPane.getChildren().add(createChild(date, dates.indexOf(date)));
    }

    private Node createChild(String date, int index) {
        VBox child = new VBox();
        String headerColor = getDefaultColor(index);
        child.setStyle("-fx-background-color: " + headerColor);
        child.setMinWidth(250);
        child.setAlignment(Pos.CENTER);

        Label datesLabel = new Label(date);
        datesLabel.setFont(Font.font("Ayuthaya", 30));
        datesLabel.setTextFill(Color.WHITE);

        child.getChildren().add(datesLabel);
        return child;
    }

    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0:
                color = "#8F3F7E";
                break;
            case 1:
                color = "#B5305F";
                break;
            case 2:
                color = "#CE584A";
                break;
            case 3:
                color = "#DB8D5C";
                break;
            case 4:
                color = "#DA854E";
                break;
            case 5:
                color = "#E9AB44";
                break;
            case 6:
                color = "#FEE435";
                break;
            case 7:
                color = "#99C286";
                break;
            case 8:
                color = "#01A05E";
                break;
            case 9:
                color = "#4A8895";
                break;
            case 10:
                color = "#16669B";
                break;
            case 11:
                color = "#2F65A5";
                break;
            case 12:
                color = "#4E6A9C";
                break;
            default:
                break;
        }
        return color;
    }
}
