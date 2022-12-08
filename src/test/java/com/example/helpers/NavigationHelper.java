package com.example.helpers;

import com.example.AppManager;

public class NavigationHelper extends HelperBase {

    public String baseUrl;

    public NavigationHelper(AppManager app) {
        super(app);
        baseUrl = app.baseUrl;
    }

    public void openHomePage() {
        driver.get("https://evernote.com/intl/ru");
    }

    public void openMarksPage() {
        driver.get("https://www.evernote.com/client/web?_sourcePage=_4OPYsTa1VziMUD9T65RG_YvRLZ-1eYO3fqfqRu0fynRL_1nukNa4gH1t86pc1SP&__fp=NC9twOukRSM3yWPvuidLz-TPR6I9Jhx8&hpts=1668423781262&showSwitchService=true&usernameImmutable=false&login=&login=%D0%92%D0%BE%D0%B9%D1%82%D0%B8&login=true&hptsh=jxSsuaHhPvcyBT4UEvjQ3%2BUAeJY%3D#?an=true&");
    }
}
