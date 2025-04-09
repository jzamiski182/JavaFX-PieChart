import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        int[] counts;
        TransactionData data = new TransactionData();
        String path = "Enter path for csv here";

        data.countCategories(path);
        counts = data.getCategoryCounts();

        // Creating Pie Chart
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                new PieChart.Data("Restaurants & Dining", counts[TransactionData.Categories.RESTAURANTS.ordinal()]),
                new PieChart.Data("Transportation", counts[TransactionData.Categories.TRANSPORTATION.ordinal()]),
                new PieChart.Data("Health", counts[TransactionData.Categories.HEALTH.ordinal()]),
                new PieChart.Data("Shopping & Entertainment", counts[TransactionData.Categories.SHOPPING.ordinal()]),
                new PieChart.Data("Insurance", counts[TransactionData.Categories.INSURANCE.ordinal()]),
                new PieChart.Data("Groceries", counts[TransactionData.Categories.GROCERIES.ordinal()])
        );

        PieChart pieChart = new PieChart(chartData);

        setTooltips(pieChart, data);

        pieChart.setTitle("Transaction Breakdown");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setLegendVisible(false);

        BorderPane borderPane = new BorderPane(pieChart);

        Scene scene = new Scene(borderPane, 1000, 600);

        primaryStage.setTitle("Pie Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setTooltips(PieChart pieChart, TransactionData data)
    {
        int total = data.getTotal();
        int[] counts = data.getCategoryCounts();
        int percent;

        for (int i = 0; i < 6; ++i) {
            percent = (int) (Math.round(((double) counts[i] / total) * 100.0));
            Tooltip tooltip = new Tooltip(percent + "%");
            tooltip.setShowDelay(Duration.seconds(0));
            Tooltip.install(pieChart.getData().get(i).getNode(), tooltip);
        }
    }
}
