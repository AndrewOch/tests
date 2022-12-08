package com.example.helpers;

import com.example.AppManager;
import com.example.models.MarkData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MarksHelper extends HelperBase {

    public MarksHelper(AppManager app) {
        super(app);
    }

    public void postMark(MarkData mark) {
        driver.findElement(By.id("qa-CREATE_NOTE")).click();
        driver.findElement(By.xpath("//div[@id='ActionMenu']/ul/li[2]/button")).click();
        driver.findElement(By.id("qa-TASKS_MODAL_TITLE")).click();
        driver.findElement(By.id("qa-TASKS_MODAL_TITLE")).clear();
        driver.findElement(By.id("qa-TASKS_MODAL_TITLE")).sendKeys(mark.getText());
        driver.findElement(By.id("qa-TASKS_MODAL_SUBMIT")).click();
    }

    public MarkData getCreatedMarkData() {
        WebElement textContent = driver.findElement(By.xpath("//*[@id=\"499f1cea-6807-7544-6824-3a361e42f778_qa-NOTES_SIDEBAR_NOTE_SNIPPET\"]/span"));
        String text = textContent.getText();
        return new MarkData(text);
    }

    public MarkData selectLastCreatedComplaintData() {
        WebElement textContent = driver.findElement(By.xpath("//*[@id=\"en-note\"]/div/div[1]/div/div/div[1]/div/div"));
        String text = textContent.getAttribute("value");
        return new MarkData(text);
    }

    public MarkData openLastCreatedComplaintData() {
        WebElement textContent = driver.findElement(By.xpath("//*[@id=\"en-note\"]/div/div[1]/div/div/div[1]/div/div"));
        String text = textContent.getAttribute("value");
        return new MarkData(text);
    }
}
