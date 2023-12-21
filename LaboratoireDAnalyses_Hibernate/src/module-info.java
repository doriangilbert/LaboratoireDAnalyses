module LaboratoireDAnalyses_Hibernate {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires org.hibernate.orm.core;
	
	opens fr.univtours.polytech.laboratoiredanalyses_hibernate.controller to javafx.graphics, javafx.fxml, javafx.base, org.hibernate.orm.core;
}
