package com.larissa.arphoenix.projetopdfa.services;

import com.larissa.arphoenix.projetopdfa.models.AlunoModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import java.io.*;

import net.sf.jasperreports.export.type.PdfaConformanceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.HashMap;
import java.util.List;


@Service
public class RelatorioService {

    public String exportarRelatorio(List<AlunoModel> listaAlunos) throws IOException, JRException {
        File file = ResourceUtils.getFile("src/main/resources/relatorios/RelatorioAlunos.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaAlunos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream("RelatorioAlunos.pdf")));
        exporter.exportReport();

        return "Relatório de alunos gerado com sucesso!";
    }

}
