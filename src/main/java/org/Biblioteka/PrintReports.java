package org.Biblioteka;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class PrintReports extends  JFrame {

    public void showReportOfAllWorkers(Connection conn) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/allWorkers.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(1200, 600);
        this.setVisible(true);
        this.setTitle("Izvjestaj");
        System.out.print("Done!");

    }

    public void showReportOfReader(Connection connection,Reader reader) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/reportOfReader.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        parameters.put("ReaderId",reader.getId());
        parameters.put("ReaderName",reader.toString());
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(1200, 600);
        this.setVisible(true);
        this.setTitle("Izvjestaj");
        System.out.print("Done!");



    }
    public void showReportOfAllReaders(Connection connection) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/allReaders.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(1200, 600);
        this.setVisible(true);
        this.setTitle("Izvjestaj");
        System.out.print("Done!");
    }
    public void showReportOfAllBooks(Connection connection) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/allBooks.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(1200, 600);
        this.setVisible(true);
        this.setTitle("Izvjestaj");
        System.out.print("Done!");
    }




}
