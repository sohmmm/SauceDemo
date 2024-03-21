package com.shared.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.IExecutionListener;

public class AllureListener implements IExecutionListener {

	public void onExecutionStart() {
		String delOldResults = "del /Q allure-results";
		runCommand(delOldResults);
	}

	public void onExecutionFinish() {
		generateAllureReport();
	}

	private void generateAllureReport() {
		try {
			Date date = new Date();
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(date);
			String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(date);

			String generateReportCmd = String
					.format("allure generate allure-results -o allure-reports/%s/temp --single-file", currentDate);
			String renameReport = String.format(
					"move allure-reports\\%s\\temp\\index.html allure-reports\\%s\\report_%s.html", currentDate,
					currentDate, timeStamp);

			runCommand(generateReportCmd);
			runCommand(renameReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int runCommand(String command) {
		int exitCode = 0;

		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
			builder.redirectErrorStream(true);
			Process process = builder.start();

			exitCode = process.waitFor();
			if (exitCode != 0) {
				System.out.println("Error generating report");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exitCode;
	}
}