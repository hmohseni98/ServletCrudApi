package com.example.hw17servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "tempServlet", value = "/temp-servlet")
public class TempServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var jsonString = new String(req.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        var objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(jsonString, Person.class);
        try (var session = SessionFactorySingleton.getInstance().openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(person);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Person person;
            try (var session = SessionFactorySingleton.getInstance().openSession()) {
                person = session.get(Person.class,Integer.valueOf(id));
            }
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println(person);
        } else {
            List<Person> persons = new ArrayList<>();
            try (var session = SessionFactorySingleton.getInstance().openSession()) {
                String hql = "From Person";
                var query = session.createQuery(hql, Person.class);
                query.getResultStream().forEach(persons::add);
            }
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            persons.forEach(out::println);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var jsonString = new String(req.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        JSONObject object = new JSONObject(jsonString);
        Integer id = object.getInt("id");
        try (var session = SessionFactorySingleton.getInstance().openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(session.get(Person.class, id));
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var jsonString = new String(req.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        var objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(jsonString, Person.class);
        try (var session = SessionFactorySingleton.getInstance().openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(person);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }
}
