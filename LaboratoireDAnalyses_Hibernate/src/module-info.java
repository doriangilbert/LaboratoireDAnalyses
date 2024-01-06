module LaboratoireDAnalyses_Hibernate {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires org.hibernate.orm.core;
	requires java.persistence;
	
	opens fr.univtours.polytech.laboratoiredanalyses_hibernate.controller to javafx.graphics, javafx.fxml, javafx.base;
	opens fr.univtours.polytech.laboratoiredanalyses_hibernate.model to org.hibernate.orm.core, java.persistence;
}
