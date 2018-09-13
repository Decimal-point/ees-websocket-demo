package com.lin.eesdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * eventSource实例
 */
@RequestMapping("test")
@RestController
public class EesTestController {
    @RequestMapping("testController")
    public void testController(HttpServletResponse response) throws IOException {
        int count = 1;
        while (true){
            count ++;
            response.addHeader("Access-Control-Allow-Origin","*");
            response.setContentType("text/event-stream");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("data:"+new Date().getTime()+"\n\n");
            writer.println("event:load");
            writer.println("id:"+count);
            writer.println();
            writer.flush();
            writer.close();
            if (count == 50){
                break;
            }
        }

    }
}
