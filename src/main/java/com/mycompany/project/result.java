package com.mycompany.project;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@WebServlet("/Generated_Quize/Quize")
public class result extends HttpServlet{
  
    public void doPost(ServletRequest req,ServletResponse res)throws IOException{
          Configuration con = new Configuration();
    	       con.configure();
               SessionFactory sf = con.buildSessionFactory();
               Session sess = sf.openSession();
               Transaction tr = sess.beginTransaction();
               int result =0;
               for(int i=0;i<5;i++){
                  String par = "que"+Integer.toString(i+1)+"_ans";
                  String ans = req.getParameter(par);
                  question q = sess.get(question.class,(100+i+1));
                  if(ans != null && q.getCrr()==1 && ans.equals("option 1")){
                      result++;
                  }
                  else if(ans != null && q.getCrr()==2 && ans.equals("option 2")){
                      result++;
                  }
                  else if(ans != null && q.getCrr()==3 && ans.equals("option 3")){
                      result++;
                  }
                  else if(ans != null ){
                      result++;
                  }
               }
               PrintWriter out = res.getWriter();
               out.print("<h1>Result  = "+Integer.toString(result)+"<h1>");
    }  
    
}
