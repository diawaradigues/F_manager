package com.example.f_manager;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.File;
import java.util.List;

public class WeeklyReportWorker extends Worker {
    private static List<myNonFinancialData> nonFinancialDataList;
    private static List<FinancialDataList> financialDataLists;
    private String recipientEmail,strTotalSales,strGrossProfit ,strNetProfit,
    strProductionYield ,strFoodConversionRatio ,strMortalityRate ,strAverageDailyGain,
            strWaterConsumption ,strBreakEvenPoint;
    private Double breakEvenPoint=0.0,productionYield=0.0,
            foodConversionRatio=0.0,mortalityRate=0.0,averageDailyGain=0.0,waterConsumption = 0.0;
    private Float totalSales=0.0f,grossProfit=0.0f,netProfit=0.0f;
    private static MyDAO DAO;

    public WeeklyReportWorker(Context context, WorkerParameters params) {
        super(context, params);
    }
    @NonNull
    @Override
    public Result doWork() {
        // Fetch your metrics from the database or other source
        DAO = new MyDAO(this.getApplicationContext());
        DAO.open();
        nonFinancialDataList = DAO.getNonFinancialData();
        financialDataLists = DAO.getFinancialData();
        recipientEmail = DAO.getEmail().toString();
        myNonFinancialData data = new myNonFinancialData(productionYield,foodConversionRatio,mortalityRate,
                averageDailyGain,waterConsumption,breakEvenPoint) ;
        FinancialDataList dataFinancial = new FinancialDataList(totalSales,grossProfit,netProfit);

         strTotalSales = String.valueOf(dataFinancial.getTotalSales());
         strGrossProfit = String.valueOf(dataFinancial.getGrossProfit());
         strNetProfit = String.valueOf(dataFinancial.getNetProfit());
         strProductionYield = String.valueOf(data.getProductionYield());
         strFoodConversionRatio = String.valueOf(data.getFoodConversionRatio());
         strMortalityRate = String.valueOf(data.getMortalityRate());
         strAverageDailyGain = String.valueOf(data.getAverageDailyGain());
         strWaterConsumption = String.valueOf(data.getWaterConsumption());
         strBreakEvenPoint = String.valueOf(data.getBreakEvenPoint());

        // Generate PDF
        File pdfFile = PDFGenerator.generateReport(strTotalSales,strGrossProfit,strNetProfit,strProductionYield
                , strFoodConversionRatio, strMortalityRate, strAverageDailyGain, strWaterConsumption, strBreakEvenPoint
                , getApplicationContext());

        // Send Email
        EmailHelper emailHelper = new EmailHelper();
        emailHelper.sendEmailWithPDF(getApplicationContext(), pdfFile, recipientEmail);

        return Result.success();
    }
}

