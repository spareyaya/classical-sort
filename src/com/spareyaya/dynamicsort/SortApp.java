package com.spareyaya.dynamicsort;

import com.spareyaya.dynamicsort.sort.Sort;
import com.spareyaya.dynamicsort.sort.impl.BubbleSort;
import com.spareyaya.dynamicsort.sort.impl.InsertionSort;
import com.spareyaya.dynamicsort.sort.impl.QuickSort;
import com.spareyaya.dynamicsort.sort.impl.SelectionSort;
import com.spareyaya.dynamicsort.util.DataUtils;
import com.spareyaya.dynamicsort.util.Utils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2017/4/20.
 *
 * @author spareyaya.
 */
public class SortApp extends Application {

    private static final String TAG = "SortApp";

    private final int DURATION = 10;

    private BarChart<String, Number> barChart;
    XYChart.Series<String, Number> series;
    private XYChart.Data<String, Number>[] seriesData;

    ComboBox<String> comboBox;

    int sortMethod = 0; //排序方法
    int sortOrder = 1; //1代表降序，2代表升序
    ToggleGroup group;


    private int[] currentData; //排序的原数据
    private int[] data;
    private List<int[]> dataList = new ArrayList<>();

    private Timeline timeline;

    private int i, j;
    private int m, n;


    @SuppressWarnings("all")
    public SortApp() {
        //可以在此处做初始化的操作
        currentData = Utils.createRadomData();
        //不能让两个引用指向同一组数据，否则原数组的顺序无法保留
        data = Arrays.copyOf(currentData, currentData.length);

        series = new XYChart.Series<>();
        seriesData = new XYChart.Data[currentData.length];
        for (int i = 0; i < seriesData.length; i++) {
            seriesData[i] = new XYChart.Data<>("" + (i + 1), data[i]);
            series.getData().add(seriesData[i]);
        }

        if (null == timeline) {
            timeline = new Timeline();
        }

        i = 0;
        j = 0;
    }


    //创建应用界面
    private Parent createContent() {
        //使用AnchorPane作为根布局
        AnchorPane anchorPane = new AnchorPane();

        //设置内边距为20
        anchorPane.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));

        //数据图表
        CategoryAxis xAxis = new CategoryAxis(); //X轴
        NumberAxis yAxis = new NumberAxis(); //Y轴
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.getStylesheets().add(SortApp.class.getResource("DataBar.css").toExternalForm());
        barChart.setBarGap(0.1);
        barChart.setCategoryGap(0.5);
        barChart.setAnimated(false); //数据变化时不播放变化动画
        barChart.setVerticalGridLinesVisible(false); //不显示垂直网格
        barChart.setLegendVisible(false); //不显示颜色标示
        xAxis.setLabel("value");
        yAxis.setLabel("index");
        yAxis.setUpperBound(300.0);
        AnchorPane.setTopAnchor(barChart, 0.0);
        AnchorPane.setLeftAnchor(barChart, 0.0);

        //add data
        barChart.getData().add(series);



        //水平分割线
        Separator hSep = new Separator(Orientation.HORIZONTAL);
        hSep.setPrefWidth(660.0);
        AnchorPane.setLeftAnchor(hSep, 0.0);
        AnchorPane.setTopAnchor(hSep, 410.0);

        //垂直分割线
        Separator vSep = new Separator(Orientation.VERTICAL);
        vSep.setPrefHeight(400.0);
        AnchorPane.setLeftAnchor(vSep, 540.0);
        AnchorPane.setTopAnchor(vSep, 0.0);



        //右侧排序算法的下拉菜单
        ObservableList<String> options = FXCollections.observableArrayList(
                "冒泡排序",
                "插入排序",
                "快速排序",
                "堆排序",
                "希尔排序",
                "归并排序",
                "选择排序");
        comboBox = new ComboBox<>(options);

        comboBox.setPrefWidth(100.0);
        AnchorPane.setTopAnchor(comboBox, 0.0);
        AnchorPane.setRightAnchor(comboBox, 0.0);
        comboBox.getSelectionModel().select(0);
        comboBox.setOnAction(event -> {
            sortMethod = comboBox.getSelectionModel().getSelectedIndex();
        });



        //右侧排列顺序单选按钮组
        group = new ToggleGroup();

        RadioButton descRb = new RadioButton("降序");
        descRb.setUserData("降序");
        descRb.setToggleGroup(group);
        descRb.setSelected(true);
        descRb.setPrefWidth(100.0);
        descRb.setPrefHeight(38.0);
        AnchorPane.setRightAnchor(descRb, 0.0);
        AnchorPane.setTopAnchor(descRb, 70.0);

        RadioButton ascRb = new RadioButton("升序");
        ascRb.setUserData("升序");
        ascRb.setToggleGroup(group);
        ascRb.setPrefWidth(100.0);
        ascRb.setPrefHeight(38.0);
        AnchorPane.setRightAnchor(ascRb, 0.0);
        AnchorPane.setTopAnchor(ascRb, 100.0);

        //当单选按钮切换时记下当前选中的按钮
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if ("降序".equals(newValue.getUserData().toString())) {
                sortOrder = 1;
            } else {
                sortOrder = 2;
            }
        });



        //底部控制按钮
        //重置数据按钮
        Button resetBtn = new Button("重置数据");
        resetBtn.setPrefWidth(100.0);
        resetBtn.setPrefHeight(38.0);
        AnchorPane.setLeftAnchor(resetBtn, 0.0);
        AnchorPane.setBottomAnchor(resetBtn, 0.0);
        resetBtn.setOnAction(event -> {
            data = Arrays.copyOf(currentData, currentData.length);

            clearData();

            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION), event1 -> {


                for (XYChart.Series<String, Number> s : barChart.getData()) {
                    m = 0;
                    for (XYChart.Data<String, Number> d : s.getData()) {
                        d.setYValue(data[m]);
                        m++;
                    }
                }

            }
            ));

            timeline.setCycleCount(1);
            timeline.play();
        });


        //变换数据按钮
        Button changeBtn = new Button("变换数据");
        changeBtn.setPrefWidth(100.0);
        changeBtn.setPrefHeight(38.0);
        AnchorPane.setLeftAnchor(changeBtn, 150.0);
        AnchorPane.setBottomAnchor(changeBtn, 0.0);
        changeBtn.setOnAction(event -> {
            currentData = Utils.createRadomData();
            data = Arrays.copyOf(currentData, currentData.length);

            clearData();

            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION), event1 -> {


                for (XYChart.Series<String, Number> s : barChart.getData()) {
                    n = 0;
                    for (XYChart.Data<String, Number> d : s.getData()) {
                        d.setYValue(data[n]);
                        n++;
                    }
                }

            }
            ));

            timeline.setCycleCount(1);
            timeline.play();

        });


        //开始排序按钮
        Button sortBtn = new Button("开始排序");
        sortBtn.setPrefWidth(100.0);
        sortBtn.setPrefHeight(38.0);
        AnchorPane.setLeftAnchor(sortBtn, 300.0);
        AnchorPane.setBottomAnchor(sortBtn, 0.0);
        sortBtn.setOnAction(event -> {

            //开始排序之前先把原来的数据清空
            clearData();

            startSort(sortMethod, sortOrder);

            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION), event1 -> {

                for (XYChart.Series<String, Number> s : barChart.getData()) {

                    for (XYChart.Data<String, Number> d : s.getData()) {

                        d.setYValue(DataUtils.sData.get(i)[j]);
                        j++;

                    }

                    j = 0;
                    i++;

                }

            }
            ));

            timeline.setCycleCount(DataUtils.sData.size());
            timeline.play();

        });


        //添加所有组件到根布局中
        anchorPane.getChildren().addAll(barChart, hSep, vSep, comboBox, descRb, ascRb, resetBtn, changeBtn, sortBtn);

        return anchorPane;
    }

    private void startSort(int sortMethod, int sortOrder) {

        i = 0;
        j = 0;

        Sort sort;

        if (sortOrder == 1) {
            //降序
            switch (sortMethod) {
                case SortMethod.BUBBLE_SORT:

                    sort = new BubbleSort();
                    sort.sortDesc(data);

                    break;

                case SortMethod.INSERTION_SORT:

                    sort = new InsertionSort();
                    sort.sortDesc(data);

                    break;

                case SortMethod.QUICK_SORT:

                    sort = new QuickSort();
                    sort.sortDesc(data);

                    break;

                case SortMethod.HEAP_SORT:
                    break;

                case SortMethod.SHELL_SORT:
                    break;

                case SortMethod.MERGE_SORT:
                    break;

                case SortMethod.SELECTION_SORT:

                    sort = new SelectionSort();
                    sort.sortDesc(data);

                    break;
                default:
                    break;

            }
        } else {
            //升序
            switch (sortMethod) {
                case SortMethod.BUBBLE_SORT:

                    sort = new BubbleSort();
                    sort.sortInc(data);

                    break;

                case SortMethod.INSERTION_SORT:

                    sort = new InsertionSort();
                    sort.sortInc(data);

                    break;

                case SortMethod.QUICK_SORT:

                    sort = new QuickSort();
                    sort.sortInc(data);

                    break;

                case SortMethod.HEAP_SORT:
                    break;

                case SortMethod.SHELL_SORT:
                    break;

                case SortMethod.MERGE_SORT:
                    break;

                case SortMethod.SELECTION_SORT:

                    sort = new SelectionSort();
                    sort.sortInc(data);

                    break;

                default:
                    break;

            }
        }
    }

    private void clearData() {

        DataUtils.clear();
        timeline.getKeyFrames().clear();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene root = new Scene(createContent(), 700, 500);

        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("几种排序算法的比较");
        primaryStage.setScene(root);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }


    class SortMethod {

        static final int BUBBLE_SORT = 0;

        static final int INSERTION_SORT = 1;

        static final int QUICK_SORT = 2;

        static final int HEAP_SORT = 3;

        static final int SHELL_SORT = 4;

        static final int MERGE_SORT = 5;

        static final int SELECTION_SORT = 6;
    }


}
