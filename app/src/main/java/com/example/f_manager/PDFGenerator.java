package com.example.f_manager;

import android.content.Context;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFGenerator {


    public static File generateReport(String totalSales,String grossProfit,String netProfit,String productionYield, String foodConversionRatio, String mortalityRate, String averageDailyGain, String waterConsumption, String breakEvenPoint, Context context) {
        File pdfFile = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);


            document.add(new Paragraph("Weekly Report"));
            document.add(new Paragraph("Total Sales: " + totalSales));
            document.add(new Paragraph("Gross Profit: " + grossProfit));
            document.add(new Paragraph("Net Profit: " + netProfit));
            document.add(new Paragraph("Production Yield: " + productionYield));
            document.add(new Paragraph("Food Conversion Ratio: " + foodConversionRatio));
            document.add(new Paragraph("Mortality Rate: " + mortalityRate));
            document.add(new Paragraph("Average Daily Gain: " + averageDailyGain));
            document.add(new Paragraph("Water Consumption: " + waterConsumption));
            document.add(new Paragraph("Break Even Point: " + breakEvenPoint));

            document.close();

            pdfFile = new File(context.getExternalFilesDir(null), "WeeklyReport.pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
            byteArrayOutputStream.writeTo(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pdfFile;
    }
}
