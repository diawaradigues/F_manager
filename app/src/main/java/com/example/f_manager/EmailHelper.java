package com.example.f_manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;

import java.io.File;

public class EmailHelper {

    public static void sendEmailWithPDF(Context context, File pdfFile, String recipientEmail) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weekly Report");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please find the weekly report attached.");

        Uri pdfUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", pdfFile);
        emailIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}

