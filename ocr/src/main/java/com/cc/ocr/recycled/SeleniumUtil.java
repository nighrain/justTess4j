package com.cc.ocr.recycled;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumUtil {
//
//    private static final ChromeOptions chromeOptions = new ChromeOptions();
//
//    static {
//        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
////        Map<String, Object> chromePrefs = CollUtil.newHashMap();
//        Map<String, Object> chromePrefs = new HashMap<>();
//        //禁止弹窗
//        chromePrefs.put("profile.default_content_settings.popups", 0);
//        //下载地址
////        chromePrefs.put("download.default_directory", "C://xx//");
//        //禁止图片加载
////        chromePrefs.put("profile.managed_default_content_settings.images", 2);
//        //userAgent=ie11
//        String userAgentIE11="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36";
//        chromePrefs.put("profile.general_useragent_override", userAgentIE11);
//
//        HashMap<String, Object> mobileEmulation = new HashMap<String, Object>();
//        //用iPhone X 屏幕启动
////        mobileEmulation.put("deviceName","iPhone X");
//
//        chromeOptions.setExperimentalOption("prefs",chromePrefs);
//        chromeOptions.setExperimentalOption("mobileEmulation",mobileEmulation);
//        /***********************************以下设置启动参数******************************************/
//        //消除安全校验
//        chromeOptions.addArguments("--allow-running-insecure-content");
//        //启动最大化，防止失去焦点
//        chromeOptions.addArguments("--start-maximized");
//        //关闭gpu图片渲染
//        chromeOptions.addArguments("--disable-gpu");
//    }
//
//    /**
//     * 获取浏览器对象直接操作
//     * @return
//     */
//    public static WebDriver build(ChromeOptions chromeOptions) {
//        WebDriver dr =  new ChromeDriver(chromeOptions);
//        dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        return dr;
//    }
//
//    /**
//     * 获取浏览器对象直接操作
//     * @return
//     */
//    public static WebDriver build() {
//        return SeleniumUtil.build(chromeOptions);
//    }
//
//    public static final String SOCIAL_INSURANCE_URL = "自定义需要模拟的网址";
//
//    /**
//     * 查询页面元素并设置值
//     * @param driver
//     * @param szsbEnum
//     * @param var1
//     */
//    public static void findAndSave(WebDriver driver,SzsbEnum szsbEnum,CharSequence... var1){
//        WebElement element ;
//        if ((element = findElement(driver,szsbEnum))!= null) element.sendKeys(var1);
//    }
//
//    /**
//     * 搜索页面元素并且点击
//     * @param driver
//     * @param szsbEnum
//     */
//    public static void findAndClick(WebDriver driver,SzsbEnum szsbEnum){
//        findElement(driver,szsbEnum).click();
//    }
//
//    /**
//     * 搜索页面元素并且点击
//     * @param driver
//     * @param szsbEnum
//     */
//    public static void findAndClick(WebDriver driver,SzsbEnum szsbEnum,ThreadLocal<Map<SeleniumUtil.SzsbEnum,Integer>> threadLocal){
//        Integer integer = threadLocal.get().get(szsbEnum);
//        if (integer == null){
//            findElement(driver,szsbEnum).click();
//        }else {
//            findElement(driver,szsbEnum,integer).click();
//        }
//    }
//
//    /**
//     * 搜索页面元素并且点击
//     * @param driver
//     * @param szsbEnum
//     */
//    public static void findAndClick(WebDriver driver,SzsbEnum szsbEnum,int wait){
//        findElement(driver,szsbEnum,wait).click();
//    }
//
//    /**
//     * 查询单个页面元素
//     * @param driver
//     * @param szsbEnum
//     * @param wait 等待因子
//     * @return
//     */
//    public static WebElement findElement(WebDriver driver,SzsbEnum szsbEnum,int wait){
//        WebElement element = null;
//        int a = 0;
//        By[] bys = szsbEnum.getBy();
//        for (By by : bys) {
//            if (a > bys.length*3) break;
//            //判断元素是否可用
//            WebElement element1 = null;
//            try {
//                element1 = driver.findElement(by);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (element1!=null && element1.isDisplayed() && element1.isEnabled()){
//                element = element1;
//            }else {
//                a++;
//                try {
//                    Thread.sleep(wait*1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
////        if (element == null) throw new SelemiumNotFoundException("Selenium cannot find element : " + szsbEnum.by,szsbEnum);
//        if (element == null) throw new RuntimeException("Selenium cannot find element : " + szsbEnum.by+"\n"+szsbEnum.name());
//        return element;
//    }
//
//    /**
//     * 查询单个页面元素
//     * @param driver
//     * @param szsbEnum
//     * @return
//     */
//    public static WebElement findElement(WebDriver driver,SzsbEnum szsbEnum){
//        return findElement(driver,szsbEnum,1);
//    }
//
//    /**
//     * 查询页面多个页面元素
//     * @param driver
//     * @param szsbEnum
//     * @return
//     */
//    public static List<WebElement> findElements(WebDriver driver, SzsbEnum szsbEnum){
//        return driver.findElements(szsbEnum.getBy()[0]);
//    }
//
//    public enum SzsbEnum{
//        /* login */
//        USER_NAME_INPUT_SELECT(By.id("userNameInput")),
//        LOGIN_YZM_ID(By.id("yzm")),
//        USER_PWD_INPUT_SELECT(By.id("userPwdInput")),
//        INFO_ALERT(By.className("dw-res-wnd-header-closebtn-ext")),
////        INFO_ALERT(By.id("dw_button_f3e0d7a3_a373_4df1_b1ae_25ee3adad2d3")),
//        LOGIN_BUTTON(By.xpath("//div[@id='f_cont']/div[1]/div[1]/div[1]/form/div[6]/div[1]")),
//
//
//        INDEX_CBDJGL(By.id("returnYwsb('F02000')")),
//        INDEX_RYPLCB(By.id("grcbdjgl-p2")),
//        INDEX_RYPLTJ(By.id("grcbdjgl-p4")),
//
//        INDEX_STOP_DRBPWJ(By.xpath("//div[contains(string(),'导入报盘文件')]")),
//        INDEX_STOP_DRBPWJ_NEXT(By.name("btn_goNext")),
//
//        INDEX_FILEUPLOAD(By.name("fileupload")),
////        INDEX_FORM(By.id("__dw_sform_form_1b997ea4_2600_4c95_bd35_59883bc7514a_form")),
//        INDEX_FILEUPLOAD_BTN(By.name("btnUpload")),
//
//        LOGIN_VALIDATA_INPUT(By.id("validatecode1"));
//        private By[] by;
//        SzsbEnum(By... by){
//            this.by = by;
//        }
//        public By[] getBy(){
//            return this.by;
//        }
//    }
}