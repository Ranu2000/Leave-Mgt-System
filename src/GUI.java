import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class GUI extends Application {
    TableView<Object> detailTable;

    public static MongoCollection<Document> collection = Main.database.getCollection("Member");

    public ObservableList<Object> getTableMembersData(){
        ObservableList<Object> objectList = FXCollections.observableArrayList();
        for (Document document : collection.find()) {
            DataBase newData = new DataBase();
            newData.setMemberID((Integer) document.get("employeeId"));
            newData.setStudentFName(String.valueOf(document.get("Name")));
            objectList.addAll(newData);
        }
        return objectList;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("LeaveManager");

        AnchorPane pane = new AnchorPane();

        TableColumn<Object, Integer> mIDColumn = new TableColumn<>("Member Id");
        mIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        mIDColumn.setMinWidth(98);

        TableColumn<Object, String> fNameColumn = new TableColumn<>("First Name");
        fNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        fNameColumn.setMinWidth(200);

//        ImageView gymBackground = new ImageView();
////        gymBackground.setImage(img);
//        gymBackground.setPreserveRatio(true);
//        gymBackground.setFitWidth(1200);


        detailTable = new TableView<>();
        detailTable.setLayoutY(80);
        detailTable.setLayoutX(100);
        detailTable.setItems(getTableMembersData());
        //detailTable.setItems(getTableMembersData());
        detailTable.getColumns().addAll(mIDColumn,fNameColumn);

        pane.getChildren().add(detailTable);
//        pane.getChildren().add(gymBackground);

        Scene scene = new Scene(pane, 1200, 600);
        primaryStage.show();
        primaryStage.setScene(scene);

    }

    public static void main(String[] args) {
        launch(args);

    }
}
