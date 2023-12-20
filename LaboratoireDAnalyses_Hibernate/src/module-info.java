module LaboratoireDAnalyses_Hibernate {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens fr.univtours.polytech.laboratoiredanalyses_hibernate.controller to javafx.graphics, javafx.fxml, javafx.base;
}
