module com.NewYearGifts {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires static lombok;
    requires com.microsoft.sqlserver.jdbc;

    opens NewYearGifts to javafx.fxml;
    exports NewYearGifts;
    opens NewYearGifts.Controllers to javafx.fxml;
    exports NewYearGifts.Controllers;
    exports NewYearGifts.Actions;
    exports NewYearGifts.DAO;
    exports NewYearGifts.DTO;
    exports NewYearGifts.Entities;
    exports NewYearGifts.EnumConstants;
}