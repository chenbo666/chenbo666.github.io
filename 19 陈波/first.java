package secondhomework;

import java.util.Scanner;

//1������һ��ѧ����Student�������ж���4����Ա����id��age��last_name���գ���first_name�����������������������������պ������ٶ���һ������getName���ظ��˵�ȫ����
//�������д���Student��Ķ��󣬸�������ֶθ�ֵ��ͬʱ���÷�������ȫ�������
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
