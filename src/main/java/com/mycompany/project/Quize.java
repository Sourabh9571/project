package com.mycompany.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class Quize extends HttpServlet{
      @Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException {
    	   question[] que_array = new question[5];
    	   PrintWriter out = res.getWriter();
    	   boolean flag = true;
    	   boolean flag2 = true;
    	   res.setContentType("text/html");
    	   String par =  null;
           for(int i=0;i<5;i++) {
    		   par = "question"+Integer.toString(i+1);
    		   String que = req.getParameter(par);
    		   if(que == null||que.length()==0) {
                   flag = false;
    			   break;
    		   }
    		   else {
                           question q = new question();
    			   q.setId(100+i+1);
    			   q.setQue_text(que);
                           List<answer> que_opt = new ArrayList<answer>();
    			   for(int p=0;p<4;p++) {
    				String opt_par = "que"+Integer.toString(i+1)+"_option_"+Integer.toString(p+1);
    			        String opt_ans = req.getParameter(opt_par);
    			        if( opt_ans == null||opt_ans.length() == 0){
    			    	   System.out.print("here1");
                                    out.print("<h2>please fill all details1"+opt_par+"<h2>");
    			    	   flag2 = false;
    			    	   break; }
    			        else {
    			    	   answer opt = new answer();
    			    	   opt.setAns(opt_ans);
    			    	   opt.setId(1+1000+p);
                                   que_opt.add(opt);
                                } }
    			    q.setOpt_list(que_opt);
                            String par_crr = "que"+Integer.toString(i+1)+"_ans";
                            String crr = req.getParameter(par_crr);
                            if(crr == null || crr.length()==0){
                                out.print("<h2>please fill all details1<h2>");
                                break;
                            }
                            if(crr.equals("Option 1")){
                                q.setCrr(1);
                            }
                            else if(crr.equals("Option 2")){
                                q.setCrr(2);
                            }
                            else if(crr.equals("Option 2")){
                                q.setCrr(3);
                            }
                            else {
                                q.setCrr(4);
                            }
                            que_array[i] = q;
    		   }
    		   if(flag2==false) {
    			   break;
    		   }
    	   }
    	   if(flag == false) {
               System.out.print("here2");
    		   out.print("<h2>please fill all details2"+par+"<h2>");
    	   }
    	   else if(flag&&flag2) {
    	       Configuration con = new Configuration();
    	       con.configure();
               SessionFactory sf = con.buildSessionFactory();
               Session sess = sf.openSession();
               Transaction tr = sess.beginTransaction();
               sess.save(que_array[0]);
               sess.save(que_array[1]);
               
              sess.save(que_array[2]);
               sess.save(que_array[3]);
               sess.save(que_array[4]);
               tr.commit();
               
               sess.close();
               sf.close();
               System.out.print("here3");
               out.print("<h1>Create Succesfull<h1><br><br><br>"
                       + "<h3>click link for generated quize<h3><br>"
                       + "<a href=\"http://localhost:8080/project/Generated_Quize\">clickhere</a>");
    	   }
      }
}