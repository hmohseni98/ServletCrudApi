package com.example.hw17servlet;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p> Now that you're an <b>HTML5 ninja</b>, it's time for you to show us what you can do. Have" +
                "dsdssdfsdf sdggfgdfgk k k  jj kj huhfuhuui hu hi  uu hu hu hi huhkjhjhkhjkjkkj h jhkj hjhjk hjh jk kj" +
                "dfgfgf s fs f dsf dsf sd fds f df er  s rs a r as ras r sa d sad as d as d  dsds  dsds ad sd s dsad s" +
                "dasdsd sad s das ds dsa da ds ada ds ds d   d ad d d sds das ea rw ew qq e e qe  ew qe qe q qq  e a e " +
                "fsdfsdf sd d ad d q  a  a a a  A ASDSAF DSF DSF S aaf sf ds ffg df gh hg fh gf h f h gf h gf h gfh gf hf</p>");
        out.println("<b>I will be picking my favourite ones and showcasing them in a future video in the course.</b>");
        out.println("<p>Good luck!</p>");
        out.println("</body></html>");
    }
}