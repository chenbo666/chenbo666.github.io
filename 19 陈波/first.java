package secondhomework;

import java.util.Scanner;

//1、定义一个学生类Student，在类中定义4个成员变量id，age，last_name（姓），first_name（名），定义两个方法用于设置姓和名。再定义一个方法getName返回该人的全名，
//在主类中创建Student类的对象，给上面的字段赋值，同时调用方法，把全名输出。
class student{
	double id;
	double age;
	String last_name;
	String first_name;
	String getname(String a,String b){
		return a+b;
	}
}
public class first {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       student s1=new student();
       Scanner sc=new Scanner(System.in);
       Scanner sc2=new Scanner(System.in);
       String a=sc.nextLine();
       String b=sc2.nextLine();
       String c=s1.getname(a, b);
       System.out.println(c);
	}

}
