package hc.test.web;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class ConferenceApplicationTests {

	@Test
	void contextLoads() {

	}



		public static void main(String[] args) {

			String s1 = "Programming";
			String s2 = new String("Programming");
			String s3 = "Program";
			String s4 = "ming";
			String s5 = "Program" + "ming";
			String s6 = s3 + s4;
			System.out.println(s1 == s2);
			System.out.println(s1 == s5);
			System.out.println(s1 == s6);
			System.out.println(s1 == s6.intern());
			System.out.println(s2 == s2.intern());


		}

}
