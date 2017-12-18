package com.ero.cweather.controllers;

import com.ero.cweather.db.collections.WeatherCollection;
import com.ero.cweather.models.Weather;
import com.ero.cweather.weather.WeatherSearcher;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    FlowPane flowPane;

    @FXML
    private JFXCheckBox chckbxFinished;

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

    private FXMLLoader fxmlLoaderDates = new FXMLLoader();
    private Parent fxmlDates;
    private Stage datesStage;
    private DatesController datesController;

    private static WeatherCollection weatherCollection;

    private boolean showFinished;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/Main.fxml"));
        fxmlLoader.setResources(resources);

        initLoader();

        weatherCollection = new WeatherCollection();

        showFinished = true;
        fillData();
        init();
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

            fxmlLoaderDates.setLocation(getClass().getResource("/fxml/Dates.fxml"));
            fxmlDates = fxmlLoaderDates.load();
            datesController = fxmlLoaderDates.getController();
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

        flowPane.getChildren().setAll(children);
    }

    private Node createChild(Weather weather) {
        VBox child = new VBox();

        // create content
        VBox header = new VBox();
        String headerColor = getDefaultColor(weatherCollection.getWeatherObservableList().indexOf(weather));
        header.setStyle("-fx-background-color: " + headerColor);
        header.setPrefSize(250, 180);
        header.setAlignment(Pos.CENTER);

        int pt = 30;
        List<String> dates = WeatherSearcher.getDates(weather);
        for (String date : dates) {
            if (pt == 0) break;
            Label datesLabel = new Label(date);
            datesLabel.setFont(Font.font("Ayuthaya", pt));
            datesLabel.setTextFill(Color.WHITE);
            header.getChildren().add(datesLabel);
            pt -= 10;
        }
        if (dates.size() > 3) {
            Label datesLabel = new Label("...");
            datesLabel.setFont(Font.font("Ayuthaya", 10));
            datesLabel.setTextFill(Color.WHITE);
            header.getChildren().add(datesLabel);
        }


        StackPane footer = new StackPane();
        footer.setStyle("-fx-background-color: white");
        footer.setPrefSize(250, 70);

        Label titleLabel = new Label(weather.title);
        titleLabel.setTextFill(Color.web(headerColor));
        titleLabel.setFont(Font.font("Ayuthaya", 20));
        footer.getChildren().add(titleLabel);

        child.getChildren().addAll(header, footer);

        child.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && dates.size() > 3) showDatesWindow(dates);
        });

        setContextMenu(child, weather.finished);
        return child;
    }

    private void setContextMenu(Node node, boolean isFinished) {
        MenuItem titleItem = new MenuItem("Имя задачи");
        titleItem.setOnAction(event -> showTitleWindow(node, children.indexOf(node)));

        MenuItem temperatureItem = new MenuItem("Температура");
        temperatureItem.setOnAction(event -> showTemperatureWindow(node, children.indexOf(node)));

        MenuItem windItem = new MenuItem("Скорость ветра");
        windItem.setOnAction(event -> showWindWindow(node, children.indexOf(node)));

        MenuItem precipitationItem = new MenuItem("Осадки");
        precipitationItem.setOnAction(event -> showPrecipitationWindow(node, children.indexOf(node)));

        MenuItem finishItem = new MenuItem(isFinished ? "Открыть задачу" : "Закрыть задачу");
        finishItem.setOnAction(event -> setFinishedValue(node, children.indexOf(node)));

        MenuItem removeTask = new MenuItem("Удалить");
        removeTask.setOnAction(event -> deleteWeather(
                weatherCollection.getWeatherObservableList().get(children.indexOf(node)),
                children.indexOf(node))
        );

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
            addWeather(titleController.getWeather());
    }

    private void showTitleWindow(Node node, int index) {
        titleController.setWeather(weatherCollection.getWeatherObservableList()
                .get(children.indexOf(node)));
        showTitleWindow();
        if (titleController.isSubmitted())
            editWeather(titleController.getWeather(), index);
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

    private void showTemperatureWindow(Node node, int index) {
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
            editWeather(temperatureController.getWeather(), index);
    }

    private void showWindWindow(Node node, int index) {
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
            editWeather(windController.getWeather(), index);
    }

    private void showPrecipitationWindow(Node node, int index) {
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
            editWeather(precipitationController.getWeather(), index);
    }

    private void showDatesWindow(List<String> dates) {
        datesController.setDates(dates);

        if (datesStage == null) {
            datesStage = new Stage();
            datesStage.setScene(new Scene(fxmlDates));
            datesStage.setResizable(false);
            datesStage.initModality(Modality.WINDOW_MODAL);
            datesStage.initOwner(mainStage);
        }
        datesStage.showAndWait();
    }

    public void addWeather(Weather weather) {
        if (!showFinished && weather.finished)
            weatherCollection.add(weather);
        else {
            Node node = createChild(weatherCollection.add(weather));
            children.add(node);
            flowPane.getChildren().add(node);
        }
    }

    public void editWeather(Weather weather, int index) {
        if (!showFinished && weather.finished) {
            weatherCollection.edit(weather);
            children.remove(index);
            flowPane.getChildren().remove(index);
        } else {
            Node node = createChild(weatherCollection.edit(weather));
            children.set(index, node);
            flowPane.getChildren().set(index, node);
        }
    }

    public void deleteWeather(Weather weather, int index) {
        weatherCollection.delete(weather);
        children.remove(index);
        flowPane.getChildren().remove(index);
    }

    private void setFinishedValue(Node node, int index) {
        Weather weather = weatherCollection.getWeatherObservableList()
                .get(children.indexOf(node));
        weather.finished = !weather.finished;

        editWeather(weather, index);
    }

    public void onFinishedChecked(ActionEvent actionEvent) {
        showFinished = !chckbxFinished.isSelected();
        init();
    }
}
