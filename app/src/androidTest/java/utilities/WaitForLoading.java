package utilities;

import io.qameta.allure.kotlin.Allure;

public class WaitForLoading {
    public void waitForLoading() {
        Allure.step("Подождать загрузку");
        long endTime = (System.currentTimeMillis() + 8000);
        while (System.currentTimeMillis() < endTime) ;
    }

}
