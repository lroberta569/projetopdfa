package com.larissa.arphoenix.projetopdfa.services;

import com.larissa.arphoenix.projetopdfa.models.AlunoModel;
import com.larissa.arphoenix.projetopdfa.repositories.AlunoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import java.io.*;
import net.sf.jasperreports.export.type.PdfaConformanceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.util.HashMap;
import java.util.List;



@Service
public class RelatorioService {

    @Autowired
    AlunoRepository alunoRepository;

    public void setDefaultPdfFontEmbedded(){
        JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.font.name", "net/sf/jasperreports/fonts/dejavu/DejaVuSans.ttf");
        jasperReportsContext.setProperty("net.jasperreports.default.pdf.embedded", "true");
    }
    public void relatorioAlunos() throws Throwable {
        JasperPrint jasperPrint = fillReport();
        exportPdfA(jasperPrint);
    }
    private void exportPdfA(JasperPrint jasperPrint) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("D:/RelatorioAlunos.pdf"));
        exporter.setConfiguration(this.getPdfAExporterConfiguration());
        exporter.exportReport();
    }

    private SimplePdfExporterConfiguration getPdfAExporterConfiguration(){
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.isTagged();
        configuration.setPdfaConformance(PdfaConformanceEnum.PDFA_1A);
        configuration.setIccProfilePath("ICC/sRGB_IEC61966-2-1_black_scaled.icc");
        configuration.setMetadataAuthor("LARISSA ROBERTA");
        configuration.setMetadataTitle("RELATÓRIO DE ALUNOS");
        setDefaultPdfFontEmbedded();
        return configuration;

    }

    private JasperPrint fillReport() throws FileNotFoundException, JRException {
        List<AlunoModel> listaAlunos = alunoRepository.findAll();
        File file = ResourceUtils.getFile("src/main/resources/relatorios/RelatorioAlunos.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaAlunos);
        return JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
    }



}
