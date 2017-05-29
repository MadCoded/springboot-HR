package com.example.demo.helper;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public  class PreviousPage {
    static public Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}
