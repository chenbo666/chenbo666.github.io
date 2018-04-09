package bob;

 import java.io.*;
 import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 import java.awt.*;
 import java.awt.image.*;
 import javax.imageio.ImageIO;
 @WebServlet("/CheckServlet")
 public class CheckServlet extends HttpServlet
 {
 	private static int WIDTH = 60; //妤犲矁鐦夐惍浣告禈閻楀洤顔旀惔锟�
 	private static int HEIGHT = 20; //妤犲矁鐦夐惍浣告禈閻楀洭鐝惔锟�
 public void doGet(HttpServletRequest request,HttpServletResponse response) 
 			throws ServletException,IOException{		
 		HttpSession session = request.getSession();
 		response.setContentType("image/jpeg");
 		ServletOutputStream sos = response.getOutputStream();
 		//鐠佸墽鐤嗗ù蹇氼潔閸ｃ劋绗夌憰浣虹处鐎涙ɑ顒濋崶鍓у
 		response.setHeader("Pragma","No-cache");
 		response.setHeader("Cache-Control","no-cache");
 		response.setDateHeader("Expires", 0);
 		//閸掓稑缂撻崘鍛摠閸ユ崘钖勯獮鎯板箯瀵版鍙鹃崶鎯ц埌娑撳﹣绗呴弬锟�
 		BufferedImage image = 
 			new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
 		Graphics g = image.getGraphics();
 		//娴溠呮晸闂呭繑婧�閻ㄥ嫯顓荤拠浣虹垳
 		char [] rands = generateCheckCode();
 		//娴溠呮晸閸ユ儳鍎�
 		drawBackground(g);
 		drawRands(g,rands);
 		//缂佹挻娼崶鎯у剼閻ㄥ嫮绮崚鎯扮箖缁嬪绱濈�瑰本鍨氶崶鎯у剼
 		g.dispose();
 		//鐏忓棗娴橀崓蹇氱翻閸戝搫鍩岀�广垺鍩涚粩锟�
 		ByteArrayOutputStream bos = new ByteArrayOutputStream();
 		ImageIO.write(image, "JPEG", bos);
 		byte [] buf = bos.toByteArray();
 		response.setContentLength(buf.length);
 		//娑撳娼伴惃鍕嚔閸欍儰绡冮崣顖氬晸閹存劧绱癰os.writeTo(sos);
 		sos.write(buf);
 		bos.close();
 		sos.close();
 		//鐏忓棗缍嬮崜宥夌崣鐠囦胶鐖滅�涙ê鍙嗛崚鐧漞ssion娑擄拷
 		session.setAttribute("check_code",new String(rands));
 		//閻╁瓨甯存担璺ㄦ暏娑撳娼伴惃鍕敩閻礁鐨㈤張澶愭６妫版﹫绱漇ession鐎电钖勮箛鍛淬�忛崷銊﹀絹娴溿倕鎼锋惔鏂垮閼惧嘲绶�
 	//request.getSession().setAttribute("check_code",new String(rands));
 	}
        //閻㈢喐鍨氭稉锟芥稉锟�4鐎涙顑侀惃鍕崣鐠囦胶鐖�
 	private char [] generateCheckCode()
 	{
 		//鐎规矮绠熸宀冪槈閻胶娈戠�涙顑佺悰锟�
 		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
 		char [] rands = new char[4];
 		for(int i=0; i<4; i++)
 		{
 			int rand = (int)(Math.random() * 36);
 			rands[i] = chars.charAt(rand);
 		}
 		return rands;
 	}
 	private void drawRands(Graphics g , char [] rands)
 	{
 		g.setColor(Color.BLACK);
 		g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));
 		//閸︺劋绗夐崥宀�娈戞妯哄娑撳﹨绶崙娲崣鐠囦胶鐖滈惃鍕槨娑擃亜鐡х粭锟�		
 		g.drawString("" + rands[0],1,17);
 		g.drawString("" + rands[1],16,15);
 		g.drawString("" + rands[2],31,18);
 		g.drawString("" + rands[3],46,16);
 		System.out.println(rands);
 	}
 	private void drawBackground(Graphics g)
 	{
  		//閻㈡槒鍎楅弲锟�
 		g.setColor(new Color(0xDCDCDC));
 		g.fillRect(0, 0, WIDTH, HEIGHT);
 		//闂呭繑婧�娴溠呮晸120娑擃亜鍏遍幍鎵仯
 		for(int i=0; i<120; i++)
 		{
 			int x = (int)(Math.random() * WIDTH);
 			int y = (int)(Math.random() * HEIGHT);
 			int red = (int)(Math.random() * 255);
 			int green = (int)(Math.random() * 255);
 			int blue = (int)(Math.random() * 255);
 			g.setColor(new Color(red,green,blue));		
 			g.drawOval(x,y,1,0);
 		}
 	}
 }
