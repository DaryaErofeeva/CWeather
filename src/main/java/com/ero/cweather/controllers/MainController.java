package com.ero.cweather.controllers;

import com.ero.cweather.db.collections.WeatherCollection;
import com.ero.cweather.models.Weather;
import com.ero.cweather.weather.WeatherSearcher;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.effects.JFXDepthManager;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private JFXCheckBox chckbxFinished;

    private JFXMasonryPane masonryPane;
    private ArrayList<Node> children;

    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage mainStage;

    private FXMLLoader fxmlLoaderTitle = new FXMLLoader();
    private Parent fxmlTitle;
    private Stage titleStage;
    private TitleController titleController;

    private FXMLLoader fxmlLoaderTemperature = new FXMLLoader();
    private Parent fxmlTemperature;
    private Stage temperatureStage;
    private TemperatureController temperatureController;

    private FXMLLoader fxmlLoaderWind = new FXMLLoader();
    private Parent fxmlWind;
    private Stage windStage;
    private WindController windController;

    private FXMLLoader fxmlLoaderPrecipitation = new FXMLLoader();
    private Parent fxmlPrecipitation;
    private Stage precipitationStage;
    private PrecipitationController precipitationController;

    private static WeatherCollection weatherCollection;

    private boolean showFinished;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/Main.fxml"));
        fxmlLoader.setResources(resources);

        showFinished = true;

        initLoader();

        weatherCollection = new WeatherCollection();
        weatherCollection.getWeatherObservableList().addListener((ListChangeListener<Weather>) c -> init());
        fillData();
    }

    private void initLoader() {
        try {
            fxmlLoaderTitle.setLocation(getClass().getResource("/fxml/Title.fxml"));
            fxmlTitle = fxmlLoaderTitle.load();
            titleController = fxmlLoaderTitle.getController();

            fxmlLoaderTemperature.setLocation(getClass().getResource("/fxml/Temperature.fxml"));
            fxmlTemperature = fxmlLoaderTemperature.load();
            temperatureController = fxmlLoaderTemperature.getController();

            fxmlLoaderWind.setLocation(getClass().getResource("/fxml/Wind.fxml"));
            fxmlWind = fxmlLoaderWind.load();
            windController = fxmlLoaderWind.getController();

            fxmlLoaderPrecipitation.setLocation(getClass().getResource("/fxml/Precipitation.fxml"));
            fxmlPrecipitation = fxmlLoaderPrecipitation.load();
            precipitationController = fxmlLoaderPrecipitation.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void fillData() {
        weatherCollection.fillWeatherList();
    }


    private void init() {
        children = new ArrayList<>();
        if (showFinished)
            for (Weather weather : weatherCollection.getWeatherObservableList())
                children.add(createChild(weather));
        else
            for (Weather weather : weatherCollection.getWeatherObservableList())
                if (!weather.finished) children.add(createChild(weather));

        masonryPane = new JFXMasonryPane();
        masonryPane.getChildren().setAll(children);
        scrollPane.setContent(masonryPane);
    }

    private Node createChild(Weather weather) {
        StackPane child = new StackPane();

        JFXDepthManager.setDepth(child, 1);

        // create content
        StackPane header = new StackPane();
        String headerColor = getDefaultColor(weatherCollection.getWeatherObservableList().indexOf(weather));
        header.setStyle("-fx-background-color: " + headerColor);

        Label datesLabel = new Label();
        for (String date : WeatherSearcher.getDates(weather))
            datesLabel.setText(datesLabel.getText() + date + "\n");
        datesLabel.setFont(Font.font("Ayuthaya", 14));
        datesLabel.setPadding(new Insets(10, 10, 10, 10));
        header.getChildren().add(datesLabel);

        VBox.setVgrow(header, Priority.ALWAYS);
        StackPane body = new StackPane();
        body.setMinHeight(Math.random() * 20 + 50);
        Label titleLable = new Label(weather.title);
        titleLable.setFont(Font.font("Ayuthaya", 14));
        body.getChildren().add(titleLable);
        VBox content = new VBox();
        content.getChildren().addAll(header, body);
        body.setStyle("-fx-background-color: rgb(255,255,255,0.87);");
        child.getChildren().add(content);

        setContextMenu(child, weather.finished);
        return child;
    }

    private void setContextMenu(Node node, boolean isFinished) {
        MenuItem titleItem = new MenuItem("Имя задачи");
        titleItem.setOnAction(event -> showTitleWindow(node));

        MenuItem temperatureItem = new MenuItem("Температура");
        temperatureItem.setOnAction(event -> showTemperatureWindow(node));

        MenuItem windItem = new MenuItem("Скорость ветра");
        windItem.setOnAction(event -> showWindWindow(node));

        MenuItem precipitationItem = new MenuItem("Осадки");
        precipitationItem.setOnAction(event -> showPrecipitationWindow(node));

        MenuItem finishItem = new MenuItem(isFinished ? "Открыть задачу" : "Закрыть задачу");
        finishItem.setOnAction(event -> setFinishedValue(node));

        MenuItem removeTask = new MenuItem("Удалить");
        removeTask.setOnAction(event -> weatherCollection.delete(weatherCollection.getWeatherObservableList()
                .remove(children.indexOf(node))));

        ContextMenu contextMenu = new ContextMenu(
                titleItem,
                temperatureItem,
                windItem,
                precipitationItem,
                new SeparatorMenuItem(),
                finishItem,
                removeTask
        );

        node.setOnContextMenuRequested(event -> contextMenu.show(node, Side.TOP, 0, 175));
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

    public void onAddButtonActionListener(ActionEvent actionEvent) {
        titleController.setWeather(new Weather());
        showTitleWindow();
        if (titleController.isSubmitted())
            weatherCollection.add(titleController.getWeather());
    }

    private void showTitleWindow(Node node) {
        titleController.setWeather(weatherCollection.getWeatherObservableList()
                .get(children.indexOf(node)));
        showTitleWindow();
        if (titleController.isSubmitted())
            weatherCollection.edit(titleController.getWeather());
    }

    private void showTitleWindow() {
        if (titleStage == null) {
            titleStage = new Stage();
            titleStage.setScene(new Scene(fxmlTitle));
            titleStage.setResizable(false);
            titleStage.initModality(Modality.WINDOW_MODAL);
            titleStage.initOwner(mainStage);
        }
        titleStage.showAndWait();
    }

    private void showTemperatureWindow(Node node) {
        temperatureController.setWeather(weatherCollection.getWeatherObservableList()
                .get(children.indexOf(node)));

        if (temperatureStage == null) {
            temperatureStage = new Stage();
            temperatureStage.setScene(new Scene(fxmlTemperature));
            temperatureStage.setResizable(false);
            temperatureStage.initModality(Modality.WINDOW_MODAL);
            temperatureStage.initOwner(mainStage);
        }
        temperatureStage.showAndWait();

        if (temperatureController.isSubmitted())
            weatherCollection.edit(temperatureController.getWeather());
    }

    private void showWindWindow(Node node) {
        windController.setWeather(weatherCollection.getWeatherObservableList()
                .get(children.indexOf(node)));

        if (windStage == null) {
            windStage = new Stage();
            windStage.setScene(new Scene(fxmlWind));
            windStage.setResizable(false);
            windStage.initModality(Modality.WINDOW_MODAL);
            windStage.initOwner(mainStage);
        }
        windStage.showAndWait();

        if (windController.isSubmitted())
            weatherCollection.edit(windController.getWeather());
    }

    private void showPrecipitationWindow(Node node) {
        precipitationController.setWeather(weatherCollection.getWeatherObservableList()
                .get(children.indexOf(node)));

        if (precipitationStage == null) {
            precipitationStage = new Stage();
            precipitationStage.setScene(new Scene(fxmlPrecipitation));
            precipitationStage.setResizable(false);
            precipitationStage.initModality(Modality.WINDOW_MODAL);
            precipitationStage.initOwner(mainStage);
        }
        precipitationStage.showAndWait();

        if (precipitationController.isSubmitted())
            weatherCollection.edit(precipitationController.getWeather());
    }

    private void setFinishedValue(Node node) {
        Weather weather = weatherCollection.getWeatherObservableList()
                .get(children.indexOf(node));
        weather.finished = !weather.finished;
        weatherCollection.edit(weather);
    }

    public void onFinishedChecked(ActionEvent actionEvent) {
        showFinished = !chckbxFinished.isSelected();
        fillData();
    }
}
