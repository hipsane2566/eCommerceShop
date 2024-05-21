package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	static String file = ".\\testData\\Data.xlsx";
	static XLUtil utils = new XLUtil(file);

	public Object[][] getNewUserSignupData() throws IOException {
		int totalRows = utils.getRowCount("TC002");
		int totalCols = utils.getCellCount("TC002", 1);

		String userdata[][] = new String[totalRows - 1][totalCols - 1];
		for (int i = 1; i < totalRows; i++) {
			for (int j = 1; j < totalCols; j++) {
				userdata[i - 1][j - 1] = utils.getCellData("TC002", i, j);
			}
		}
		return userdata;
	}

	public Object[][] getAccInfoData() throws IOException {
		int totalRows = utils.getRowCount("TC001");
		int totalCols = utils.getCellCount("TC001", 1);

		String infodata[][] = new String[totalRows][totalCols];
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				infodata[i - 1][j] = utils.getCellData("TC001", i, j);
			}
		}
		return infodata;
	}

	@DataProvider(name = "AccountCreationData")
	public Object[][] getCompleteSignupdata() throws IOException {
		int totallength = getNewUserSignupData()[0].length + getAccInfoData()[0].length;
		String[][] testData = new String[1][totallength];
		int len1 = getNewUserSignupData()[0].length;
		int len2 = getAccInfoData()[0].length;

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < len1; j++) {
				testData[i][j] = (String) getNewUserSignupData()[i][j];
			}
		}

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < len2; j++) {
				testData[i][j + len1] = (String) getAccInfoData()[i][j];
			}
		}

		return testData;
	}

	@DataProvider(name = "LoginTestData")
	public Object[][] getLoginData() throws IOException {

		int totalRows = utils.getRowCount("TC002");
		int totalCols = utils.getCellCount("TC002", 1);

		String Data[][] = new String[totalRows - 1][totalCols - 1];
		for (int i = 2; i <= totalRows; i++) {
			for (int j = 1; j < totalCols; j++) {
				Data[i - 1 - 1][j - 1] = utils.getCellData("TC002", i, j);
			}
		}
		return Data;
	}

	@DataProvider(name ="InvalidLoginData")
	public Object[][] getInvalidLoginData() throws IOException{

		int totalRows = utils.getRowCount("TC003");
		int totalCols = utils.getCellCount("TC003", 1);

		String  data[][] = new String[totalRows][totalCols-2]; //rows 2-1=1, col 5-2=3
		for (int i = 1; i <=totalRows; i++) {
			for (int j = 2; j < totalCols; j++) {
				data[i - 1][j - 2] = utils.getCellData("TC003", i, j);
			}
		}
		return data;
	}
}
