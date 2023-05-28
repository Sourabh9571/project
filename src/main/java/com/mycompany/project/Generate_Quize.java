package com.mycompany.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.servlet.annotation.WebServlet;
import org.hibernate.cfg.Configuration;

@WebServlet("/Generated_Quize")
public class Generate_Quize extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
            Configuration con = new Configuration();
    	        con.configure("hibernate1.cfg.xml");
                SessionFactory sf = con.buildSessionFactory();
                Session sess = sf.openSession();
                res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                boolean flag = true;
                question[] que_array = new question[5];
                for(int i=0;i<1;i++){
                    question q = sess.get(question.class, (100+1+i));
                    if(q==null){
                        flag = false;
                        break;
                    }
                    que_array[i] = q;
                }
                
                if(flag == false){
                    out.print("<h3>please go to main page for creating Quize<h3><br>"
                            + "<a href=\"http://localhost:8080/project\">click here for creat quize<a>");
                }
                else{
                out.print("<h1>Welcome to your quize<h1>"
                        + "<form action=\"Quize\" method=\"post\">\n" +
                          "<form>");
                for(int i=0;i<5;i++){
                    question q = que_array[i];
                    List<answer> options = q.getOpt_list();
                    out.print("<label for=\"question_1\" >Question "+Integer.toString(i+1)+" : "+q.getQue_text()+"</label><br>");
                    for(int k=0;k<4;k++){
                        answer op = options.get(k);
                        out.print("<label for=\"question_1\" > Option "+Integer.toString(k+1)+".  "+op.getAns()+"</label><br>");
                    }
                    out.print(" <label for=\"Correct_Option\">Option 1</label>\n" +
"             <input type=\"radio\" name=\"que"+Integer.toString(i+1)+"_ans\"  value=\"Option 1\">\n" +
"              <label for=\"Correct_Option\">Option 2</label>\n" +
"              <input type=\"radio\" name=\"que"+Integer.toString(i+1)+"_ans\"  value=\"Option 2\">\n" +
"              <label for=\"Correct_Option\">Option 3</label>\n" +
"                <input type=\"radio\" name=\"que"+Integer.toString(i+1)+"_ans\"  value=\"Option 3\">\n" +
"             <label for=\"Correct_Option\">Option 4</label>\n" +
"             <input type=\"radio\" name=\"que"+Integer.toString(i+1)+"_ans\"  value=\"Option 4\"><br>\n" +
"            ");
                }
                out.print(" <input type=\"submit\" value=\"Submit\">\n" +
"             <input type=\"reset\" value=\"Reset\">\n" +
"         </form>");
                sess.close();
                sf.close();
                }
    }
}
