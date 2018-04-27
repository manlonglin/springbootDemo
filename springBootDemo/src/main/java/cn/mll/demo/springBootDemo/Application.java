package cn.mll.demo.springBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author mll
 *
 */
@SpringBootApplication
@RestController  
public class Application {

	 @RequestMapping("/")  
	    public String greeting() { 
		 	System.out.println("进入springBoot方法");
	        return "Hello World!";  
	    }  
	  
	    public static void main(String[] args) {  
	        SpringApplication.run(Application.class, args);  
	    }  
	    
	    
}
