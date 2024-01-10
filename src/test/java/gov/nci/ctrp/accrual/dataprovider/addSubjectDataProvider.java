package gov.nci.ctrp.accrual.dataprovider;

import org.testng.annotations.DataProvider;

public class addSubjectDataProvider {

    @DataProvider(name="accural-type-subject")
    public static Object[][] getAccuralTypeAddSubject(){
        return new Object[][]{
                {"Subject","SDC","10/1994","Male","Asian","Not Reported","United States","73001","12/12/2022","Cancer","Lung"}
        };
    }

    @DataProvider(name="accural-type-partial-subject")
    public static Object[][] getAccuralTypeAddPartialSubject(){
        return new Object[][]{
                {"SDC","10/1994","Male","Asian","Not Reported","Afghanistan","73001","12/12/2022","Cancer","Lung"}
        };
    }

    @DataProvider(name="disease-code")
    public static Object[][] getDiseaseCodeAddSubject(){
        return new Object[][] {
                {"ICD-O-3","8253/2","C34"}
        };
    }
}
