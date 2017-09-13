package application;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.javafx.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

class LBG3
{
	int x;       // x
	int y;       // y
	int Or;      // ��(initial)
	int Og;      // ��(initial)
	int Ob;      // ��(initial)
	int Nr;      // ��(new)
	int Ng;      // ��(new)
	int Nb;      // ��(new)
	int group;   // �s

	LBG3(int x, int y,int r,int g,int b,int group)
	{
		this.x = x;
		this.y = y;
		this.Or = r;
		this.Og = g;
		this.Ob = b;
		this.Nr = r;
		this.Ng = g;
		this.Nb = b;
		this.group = group;

	}

}

class LBG4
{

	int Or;
	int Og;
	int Ob;
	int x;
	int y;

	LBG4(int r,int g,int b,int x,int y)	{

		this.Or = r;
		this.Og = g;
		this.Ob = b;
		this.x= x;
		this.y= y;
	}

}

class LBG7
{
	int color;
	int num;
	int app;
	int wa;
	LBG7(int color,int num,int app,int wa)
	{
		this.color=color;
		this.num=num;
		this.app=app;
		this.wa=wa;
	}

}

class LBG8
{
	int Or;      // ��(�}�l��)
	int Og;      // ��(�}�l��)
	int Ob;      // ��(�}�l��)
	int num;
	int app;
	int group;

	LBG8(int r,int g,int b,int num,int app,int group)
	{
		this.Or = r;
		this.Og = g;
		this.Ob = b;
		this.num=num;
		this.app=app;
		this.group =group;

	}

}

class LBG9
{

	int num;
	int app;
	int color;
	LBG9(int num,int app,int color)
	{

		this.num=num;
		this.app=app;
		this.color=color;
	}

}





public class Main extends Application {
	//control
	int count=0;int p=0;int j=0;
	int[] centerpoint;
	int[] centerpoint2;
	//som initial parameter
	double R=0.5;
	double R_RATE=0.95;
	double R_MIN=0.1;
	double ETA=1;
	double ETA_RATE=0.95;
	double ETA_MIN=0.01;
	//CHARTset
	String ORed = "OldRed";String LRed = "NewRed";
    String OGreen = "OldGreen";String LGreen = "NewGreen";
    String OBlue = "OldBlue";String LBlue = "NewBlue";
    int[] ctotal = new int[3];int[] ctotal2 = new int[3];
	//Array
	ArrayList<LBG7> data5 = new ArrayList<LBG7>(); //counting color
	ArrayList<LBG8> data8 = new ArrayList<LBG8>(); //���.randomcodebook
	ArrayList<LBG9> data9 = new ArrayList<LBG9>(); //�L�o�᪺array
	ArrayList<LBG3> data = new ArrayList<LBG3>(); //initial image information
	ArrayList<LBG4> center = new ArrayList<LBG4>(); //save codebook
	//���
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> bc =new BarChart<String,Number>(xAxis,yAxis);
    float newpalette[][];
	//�ŧi����
	Label on=new Label();
	Label tw=new Label();
	Label th=new Label();
	Label fo=new Label();
	Label fi=new Label();
	Label si=new Label();
	Label se=new Label();
	Label ei=new Label();
	Label ni=new Label();
	Label t1=new Label();
	Label t2=new Label();
	TextField codeBook = new TextField("");
	TextField threShold = new TextField("");
	Button read;
	Button load;
	Button start,test;
	Button BscreenShot;
	Button abtn,abtn2;
	ScrollPane sp1;
	ImageView jsp;
	ImageView jsp2;
	BufferedImage selectImage,selectImage2;
	Image image,image2;
	AnchorPane root;
	public void start(Stage primaryStage) {

			root = new AnchorPane();
			Scene scene = new Scene(root,1170,700);
			//�ŧi�U����W��
			jsp= new ImageView();
			jsp2=new ImageView();
			on=new Label("Codebook�G");
			tw=new Label("run time�G");
			th=new Label("�`����(pixel)�G");
			fo=new Label("����ɶ�(ms)�G");
			si=new Label("PSNR��(DB)�G");
			se=new Label("MSE�ȡG");
			ei=new Label("�C��ƶq�G");
			ni=new Label("���榸�ơG");
			t1=new Label("��l�Ϲ��G");
			t2=new Label("���Y�Ϲ��G");
			read = new Button("Select");
			load = new Button("Load");
			start = new Button("Start");
			BscreenShot = new Button("Save");
			test = new Button("Analysis");
			abtn = new Button("Pixel");
			abtn2 = new Button("Color");
			//setid
			jsp.setId("jsp");
			root.setId("root");
			load.setId("btn1");
			start.setId("btn1");
			BscreenShot.setId("btn1");
			read.setId("btn1");
			test.setId("btn1");

			//Label�j�p�P��m
			on.setPrefSize(180, 30);AnchorPane.setTopAnchor(on, 20.0);AnchorPane.setLeftAnchor(on, 170.0);
			tw.setPrefSize(180, 30);AnchorPane.setTopAnchor(tw, 60.0);AnchorPane.setLeftAnchor(tw, 170.0);
			th.setPrefSize(180, 30);AnchorPane.setTopAnchor(th, 20.0);AnchorPane.setLeftAnchor(th, 520.0);
			ei.setPrefSize(180, 30);AnchorPane.setTopAnchor(ei, 60.0);AnchorPane.setLeftAnchor(ei, 520.0);
			si.setPrefSize(180, 30);AnchorPane.setTopAnchor(si, 20.0);AnchorPane.setLeftAnchor(si, 680.0);
			se.setPrefSize(180, 30);AnchorPane.setTopAnchor(se, 60.0);AnchorPane.setLeftAnchor(se, 680.0);
			fo.setPrefSize(180, 30);AnchorPane.setTopAnchor(fo, 20.0);AnchorPane.setLeftAnchor(fo, 850.0);
			ni.setPrefSize(180, 30);AnchorPane.setTopAnchor(ni, 60.0);AnchorPane.setLeftAnchor(ni, 850.0);
			t1.setPrefSize(180, 30);AnchorPane.setTopAnchor(t1, 110.0);AnchorPane.setLeftAnchor(t1, 30.0);
			t2.setPrefSize(180, 30);AnchorPane.setTopAnchor(t2, 110.0);AnchorPane.setLeftAnchor(t2, 611.0);
			//TEXTFILD
			codeBook.setPrefSize(70, 30);AnchorPane.setTopAnchor(codeBook, 20.0);AnchorPane.setLeftAnchor(codeBook, 247.0);
			threShold.setPrefSize(70, 30);AnchorPane.setTopAnchor(threShold, 60.0);AnchorPane.setLeftAnchor(threShold, 247.0);
			//btn�j�p�P��m
			read.setPrefSize(120, 30);AnchorPane.setTopAnchor(read, 20.0);AnchorPane.setLeftAnchor(read, 20.0);
			load.setPrefSize(120, 30);AnchorPane.setTopAnchor(load, 60.0);AnchorPane.setLeftAnchor(load, 20.0);
			start.setPrefSize(120, 30);AnchorPane.setTopAnchor(start, 20.0);AnchorPane.setLeftAnchor(start, 350.0);
			BscreenShot.setPrefSize(120, 30);AnchorPane.setTopAnchor(BscreenShot, 60.0);AnchorPane.setLeftAnchor(BscreenShot, 350.0);
			test.setPrefSize(120, 30);AnchorPane.setTopAnchor(test, 38.0);AnchorPane.setLeftAnchor(test, 1015.0);
			//VIEW
			jsp.setFitHeight(512);jsp.setFitWidth(512);AnchorPane.setTopAnchor(jsp, 140.0);AnchorPane.setLeftAnchor(jsp, 30.0);
			jsp2.setFitHeight(512);jsp2.setFitWidth(512);AnchorPane.setTopAnchor(jsp2, 140.0);AnchorPane.setLeftAnchor(jsp2, 611.0);
			//add
			root.getChildren().addAll(on,tw,fo,si,se,ei,ni,th,read,load,start,BscreenShot,jsp,jsp2,t1,t2,codeBook,threShold,test);
			//���
			primaryStage.setTitle("Image compression");
			primaryStage.setScene(scene);
			primaryStage.setMaxHeight(700);primaryStage.setMaxWidth(1170);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			primaryStage.show();

			//analysis
			test.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				int w=0;
	            Stage stage2 = new Stage();
	            stage2.setTitle("Analysis");
	            bc.setAnimated(true);

	            AnchorPane root2 = new AnchorPane();

	            bc.setPrefSize(740, 500);AnchorPane.setTopAnchor(bc, 80.0);AnchorPane.setLeftAnchor(bc, 30.0);
	            abtn.setPrefSize(350, 30);AnchorPane.setTopAnchor(abtn, 20.0);AnchorPane.setLeftAnchor(abtn, 30.0);
	            abtn2.setPrefSize(350, 30);AnchorPane.setTopAnchor(abtn2, 20.0);AnchorPane.setLeftAnchor(abtn2, 420.0);
	            abtn.setId("btn2");
	            abtn2.setId("btn2");
	            root2.getChildren().addAll(abtn,abtn2,bc);
	            root2.setId("root2");
	            APPCOLOR(1,data8,data,newpalette);
	            updateChart(1,ctotal,ctotal2);

	            abtn.setOnAction(new EventHandler<ActionEvent>(){
	                @Override
	                public void handle(ActionEvent arg0) {
	                	int w=1;
	                	APPCOLOR(w,data8,data,newpalette);
	     	            System.out.println(ctotal[0]+" "+ctotal[1]+" "+ctotal[2]);
	     	            System.out.println(ctotal2[0]+" "+ctotal2[1]+" "+ctotal2[2]);
	                    updateChart(w,ctotal,ctotal2);
	                    for(int x=0;x<ctotal.length;x++){
	    	    			ctotal[x]=0;
	    	    			ctotal2[x]=0;
	    	    		}
	                }

	            });

	            abtn2.setOnAction(new EventHandler<ActionEvent>(){
	                @Override
	                public void handle(ActionEvent arg0) {
	                	APPCOLOR(w,data8,data,newpalette);
	     	            System.out.println(ctotal[0]+" "+ctotal[1]+" "+ctotal[2]);
	     	            System.out.println(ctotal2[0]+" "+ctotal2[1]+" "+ctotal2[2]);
	                    updateChart(w,ctotal,ctotal2);
	                    for(int x=0;x<ctotal.length;x++){
	    	    			ctotal[x]=0;
	    	    			ctotal2[x]=0;
	    	    		}
	                }

	            });

	            for(int x=0;x<ctotal.length;x++){
	    			ctotal[x]=0;
	    			ctotal2[x]=0;
	    		}

	            Scene scene2  = new Scene(root2,800,650);
	            stage2.setMaxHeight(650);stage2.setMaxWidth(800);
	            stage2.setScene(scene2);
	            scene2.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
	            stage2.show();

	            // �������������ð_��
	            // ((Node) (event.getSource())).getScene().getWindow().hide();

	            }
	        });


			BscreenShot.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			    public void handle(ActionEvent e) {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Save Image");
					FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
					fileChooser.getExtensionFilters().addAll(extFilterpng);
					File file = fileChooser.showSaveDialog(null);
					 if (file != null) {
			                try {
			                	ImageIO.write(SwingFXUtils.fromFXImage(jsp2.getImage(),null), "png", file);
			                } catch (IOException ex) {
			                    System.out.println(ex.getMessage());
			                }
			            }
			    }


			});

			//read
			read.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			    public void handle(ActionEvent e) {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Select image");
					FileChooser.ExtensionFilter extFilterbmp = new FileChooser.ExtensionFilter("bmp files (*.bmp)", "*.bmp");
					FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
					FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
					fileChooser.getExtensionFilters().addAll(extFilterbmp, extFilterpng, extFilterjpg);
					File file = fileChooser.showOpenDialog(null);
					try {
						selectImage = ImageIO.read(file);
						selectImage2 = ImageIO.read(file);
		                image = SwingFXUtils.toFXImage(selectImage, null);
		        		image2 = SwingFXUtils.toFXImage(selectImage2, null);
		                jsp.setImage(image);
		            } catch (IOException ex) {
		            	System.out.println("Ū������!\n����:"+ex.toString());
		            }

			    }
			});


			load.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			    public void handle(ActionEvent e) {
					//�}�l�ɶ�
					long starttime = System.currentTimeMillis();

					int hight = selectImage.getHeight();
					int width = selectImage.getWidth();

					int xx=0;
					for(int x=0;x<width;x++)
					{
						for(int y=0;y<hight;y++)
						{
							int rgb=selectImage.getRGB(x,y);       // ���o�����IRGB��
							int r=(rgb&0x00ff0000)>>16;            // ���o���⪺���
							int g=(rgb&0x0000ff00)>>8;             // ���o�����
							int b=rgb&0x000000ff;                  // ���o�Ŧ���
							data.add(new LBG3(x,y,r,g,b,0));      // ��Ƽg�J
							int c=(r*256*256)+g*256+b;  //�C��p��
							data5.add(new LBG7(c,xx,0,0)); //�C��p��}�C

							xx++;
						}
					}

					th.setText("�`����(pixel)�G"+hight*width);
					//�C��Ƨ�
					Collections.sort(data5,new Comparator<LBG7>() {

						@Override
						public int compare(LBG7 o1, LBG7 o2) {
							// TODO Auto-generated method stub
							return o2.color-o1.color;
						}
					 });

					int number=0;
					//�p���C��ۦP
					for (int i=0;i<data.size();i++){
						if(i>0){
							if(data5.get(i).color!=data5.get(i-1).color){
								data5.get(i-1).app=number+1;
								number=0;
							}else{
								number++;
							}
						}
					}
					if(data5.get(data.size()-1).color!=data5.get(data.size()-2).color){
						data5.get(data.size()-1).app=1;
						number=0;
					}
					//�C��L�o
					for(int i=0;i<data.size();i++){
						if(data5.get(i).app!=0){
						data9.add(new LBG9(data5.get(i).num,data5.get(i).app,data5.get(i).color));
						}
					}

					ei.setText("�C��ƶq�G"+data9.size());

					//data8�������C��
					System.out.println(data9.size());
					for(int i=0;i<data9.size();i++){
						data8.add(new LBG8(data.get(data9.get(i).num).Or,data.get(data9.get(i).num).Og,data.get(data9.get(i).num).Ob,data9.get(i).num,data9.get(i).app,0));
					}

					int codebook = Integer.parseInt(codeBook.getText());          // �s��
					centerpoint = new int[codebook];
					plan(centerpoint,data9);

					long endtime = System.currentTimeMillis();
					long finish = ((endtime-starttime)/1000);
					System.out.println((endtime-starttime+"ms"));


			    }
			});

	start.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			    public void handle(ActionEvent e) {
					int k=0,l=0;
					int codebook = Integer.parseInt(codeBook.getText());
					newpalette = new float[codebook][4];
			        float NEWMSE=0;

			        long ktime = System.currentTimeMillis();

			        //som
			        clustering();

			        //final codebook
			        for(int i=0;i<codebook;i++){
						newpalette[i][0]=(int)center.get(i).Or;
						newpalette[i][1]=(int)center.get(i).Og;
						newpalette[i][2]=(int)center.get(i).Ob;
			        }

			        //error codebook
			        for(int a=0;a<codebook;a++)
					{
						if(newpalette[a][0]==0 && newpalette[a][1]==0 && newpalette[a][2]==0){
							int x=(int)(Math.random()*data8.size());
							newpalette[a][0]=data8.get(x).Or;
							newpalette[a][1]=data8.get(x).Og;
							newpalette[a][2]=data8.get(x).Ob;
						}
					}

					resetChoose(data,newpalette);

					NEWMSE = 0;
					for(int i =0;i<data.size();i++){   //MSE= (Xr-Zr)^2 + (Xg-Zg)^2 + (Xb-Zb)^2 / p
						float rr=(float) (Math.pow(data.get(i).Or-data.get(i).Nr, 2));
			            float gg=(float) (Math.pow(data.get(i).Og-data.get(i).Ng, 2));
			            float bb=(float) (Math.pow(data.get(i).Ob-data.get(i).Nb, 2));
			            NEWMSE +=(rr+gg+bb);
			        }
					NEWMSE=NEWMSE/data.size();
					 double num = Double.parseDouble(threShold.getText());
					 float result = (float)Math.log10(((255*255*3)/NEWMSE))*10;
				        System.out.println("psnr:"+result);
				        si.setText("PSNR�ȡG"+String.valueOf(result)+"DB");
				        se.setText("MSE�ȡG"+NEWMSE);
				        ni.setText("���榸�ơG"+num+" ��");

					long ktime2 = System.currentTimeMillis();
					long kfinish2 = ((ktime2-ktime));

					fo.setText("����ɶ�(ms)�G"+kfinish2+"ms");
			    }
			});

	}

	//display newimage
	public void resetChoose(ArrayList<LBG3> data,float [][]newpalette)
	{
		int codebook = Integer.parseInt(codeBook.getText());

		for(int b=0;b<newpalette.length;b++){
			 for(int x=0;x<newpalette.length;x++){
				if(b!=x){
					if(newpalette[b][0]==newpalette[x][0] && newpalette[b][1]==newpalette[x][1] && newpalette[b][2]==newpalette[x][2])
					{
						System.out.println("error");

					}
				}
			 }
		 }

		for(int a=0;a<data.size();a++)
		{
			float minVaule = (float) 999999999999999.0;

			for(int b=0;b<codebook;b++)
			{
				float rr = (data.get(a).Or-newpalette[b][0])*(data.get(a).Or-newpalette[b][0]);
				float gg = (data.get(a).Og-newpalette[b][1])*(data.get(a).Og-newpalette[b][1]);
				float bb = (data.get(a).Ob-newpalette[b][2])*(data.get(a).Ob-newpalette[b][2]);
				float calculator = (float) Math.sqrt(rr+gg+bb);
				if(calculator<minVaule)
				{
					minVaule = calculator;
					data.get(a).group = b;
					data.get(a).Nr =(int) (newpalette[b][0]);
					data.get(a).Ng =(int) (newpalette[b][1]);
					data.get(a).Nb =(int) (newpalette[b][2]);
				}
			}
		}

		int hight = selectImage2.getHeight();
		int width = selectImage2.getWidth();

		for(int x=0;x<width;x++)
		{
			for(int y=0;y<hight;y++)
			{
				int point = (x*hight)+y;
				int r=(int)(Math.round((data.get(point).Nr)<<16));
				int g=(int)(Math.round((data.get(point).Ng)<<8));
				int b=(int)(Math.round(data.get(point).Nb));
				int rgb = r+g+b;
				selectImage2.setRGB(x, y, rgb);
			}
		}
		image2 = SwingFXUtils.toFXImage(selectImage2, null);
		jsp2.setImage(image2);

	}

	//initial codebook
	public void plan(int[] centerpoint,ArrayList<LBG9> data9)
	{
		 int codebook = Integer.parseInt(codeBook.getText());

		 for(int b=0;b<codebook;b++){

				 centerpoint[b]=(int)(Math.random()*data8.size());
				 centerpoint[b]=data8.get(centerpoint[b]).num;

					if(b>0){
						for(int x=0;x<b;x++){
							if(centerpoint[b]==centerpoint[x]){
							b--;
							}
						}
					}
		 }
		 int x=0,y=0;
		 int ss=(int) Math.sqrt(codebook);

		 for(int a11=0;a11<codebook;a11++)
		 {
			 center.add(new LBG4(data.get(centerpoint[a11]).Or,data.get(centerpoint[a11]).Og,data.get(centerpoint[a11]).Ob,x,y));
			 System.out.println(center.get(a11).Or+" "+center.get(a11).Og+" "+center.get(a11).Ob);
			 y++;
			 if(y==ss){
				 x++;
				 y=0;
			 }
		 }
	}

	//���Ϊ��C��p��
	public void APPCOLOR(int w,ArrayList<LBG8> data8,ArrayList<LBG3> data,float [][]newpalette)
	{
		int codebook = Integer.parseInt(codeBook.getText());
		int[][] COLOR = new int[3][3];
		int win=0;
		COLOR[0][0]=255;COLOR[0][1]=0;COLOR[0][2]=0;
		COLOR[1][0]=0;COLOR[1][1]=255;COLOR[1][2]=0;
		COLOR[2][0]=0;COLOR[2][1]=0;COLOR[2][2]=255;

		if(w==1){
		for(int a=0;a<data8.size();a++)
		{
			float minVaule = (float) 999999999999999.0;

			for(int b=0;b<COLOR.length;b++)
			{
				//RED
				float rr = (data8.get(a).Or-COLOR[b][0])*(data8.get(a).Or-COLOR[b][0]);
				float gg = (data8.get(a).Og-COLOR[b][1])*(data8.get(a).Og-COLOR[b][1]);
				float bb = (data8.get(a).Ob-COLOR[b][2])*(data8.get(a).Ob-COLOR[b][2]);
				float calculator = (float) Math.sqrt(rr+gg+bb);
				if(calculator<minVaule)
				{
					minVaule = calculator;
					win = b;
				}
			}
			ctotal[win]=ctotal[win]+data8.get(a).app;
		}

		for(int a=0;a<data.size();a++)
		{
			float minVaule = (float) 999999999999999.0;

			for(int b=0;b<COLOR.length;b++)
			{
				//RED
				float rr = (data.get(a).Nr-COLOR[b][0])*(data.get(a).Nr-COLOR[b][0]);
				float gg = (data.get(a).Ng-COLOR[b][1])*(data.get(a).Ng-COLOR[b][1]);
				float bb = (data.get(a).Nb-COLOR[b][2])*(data.get(a).Nb-COLOR[b][2]);
				float calculator = (float) Math.sqrt(rr+gg+bb);
				if(calculator<minVaule)
				{
					minVaule = calculator;
					win = b;
				}
			}
			ctotal2[win]+=1;
			}
		}else{
			for(int a=0;a<data8.size();a++)
			{
				float minVaule = (float) 999999999999999.0;

				for(int b=0;b<COLOR.length;b++)
				{

					float rr = (data8.get(a).Or-COLOR[b][0])*(data8.get(a).Or-COLOR[b][0]);
					float gg = (data8.get(a).Og-COLOR[b][1])*(data8.get(a).Og-COLOR[b][1]);
					float bb = (data8.get(a).Ob-COLOR[b][2])*(data8.get(a).Ob-COLOR[b][2]);
					float calculator = (float) Math.sqrt(rr+gg+bb);
					if(calculator<minVaule)
					{
						minVaule = calculator;
						win = b;
					}
				}
				ctotal[win]+=1;
			}

			for(int a=0;a<newpalette.length;a++)
			{
				float minVaule = (float) 999999999999999.0;

				for(int b=0;b<COLOR.length;b++)
				{

					float rr = (newpalette[a][0]-COLOR[b][0])*(newpalette[a][0]-COLOR[b][0]);
					float gg = (newpalette[a][1]-COLOR[b][1])*(newpalette[a][1]-COLOR[b][1]);
					float bb = (newpalette[a][2]-COLOR[b][2])*(newpalette[a][2]-COLOR[b][2]);
					float calculator = (float) Math.sqrt(rr+gg+bb);
					if(calculator<minVaule)
					{
						minVaule = calculator;
						win = b;
					}
				}
				ctotal2[win]+=1;
			}
		}

	}

	//�Ϫ��s
	private void updateChart(int w,int[] ctotal,int[] ctotal2){
		String op;
		if(w==1){
		op="Pixel";
		}else{
		op="Color";
		}
		bc.getData().clear();
		bc.setBarGap(3);
		bc.setCategoryGap(20);
		bc.setTitle(op+" Analysis");
        xAxis.setLabel(op);
        yAxis.setLabel("Value");
        bc.getData().clear();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Red");
        series1.getData().add(new XYChart.Data(ORed, ctotal[0]));
        series1.getData().add(new XYChart.Data(LRed, ctotal2[0]));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Green");
        series2.getData().add(new XYChart.Data(OGreen, ctotal[1]));
        series2.getData().add(new XYChart.Data(LGreen, ctotal2[1]));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Blue");
        series3.getData().add(new XYChart.Data(OBlue, ctotal[2]));
        series3.getData().add(new XYChart.Data(LBlue, ctotal2[2]));

        bc.getData().addAll(series1, series2, series3);
    }

	//���s
	public void clustering()
	{
		int k=0,l=0,wr=0;
		int codebook = Integer.parseInt(codeBook.getText());
		float [][]newpalette = new float[codebook][5];


        for(int i=0;i<codebook;i++){
			newpalette[i][0]=center.get(i).Or;
			newpalette[i][1]=center.get(i).Og;
			newpalette[i][2]=center.get(i).Ob;
			newpalette[i][3]=center.get(i).x;
			newpalette[i][4]=center.get(i).y;
		}

		long ktime = System.currentTimeMillis();//
		double num = Double.parseDouble(threShold.getText());
		do{
			l++;
		for(int a=0;a<data.size();a++)
		{
			float minVaule = (float) 999999999999999.0;

			for(int b=0;b<codebook;b++)
			{

				float rr = (data.get(a).Or-newpalette[b][0])*(data.get(a).Or-newpalette[b][0]);
				float gg = (data.get(a).Og-newpalette[b][1])*(data.get(a).Og-newpalette[b][1]);
				float bb = (data.get(a).Ob-newpalette[b][2])*(data.get(a).Ob-newpalette[b][2]);
				float calculator = (float) Math.sqrt(rr+gg+bb);
				if(calculator<minVaule)
				{
					minVaule = calculator;
					data.get(a).group = b;
					wr=b;
				}
			}

			for(int c=0;c<codebook;c++){

				float q=(float) ((Math.pow(newpalette[wr][3]-newpalette[c][3],2))+(Math.pow(newpalette[wr][4]-newpalette[c][4],2)));
				float R_from_win = (float) Math.sqrt(q);
				double Factor;
				if(R_from_win==Double.POSITIVE_INFINITY||R_from_win==Double.NEGATIVE_INFINITY)
				{
					 Factor=0;
				}else{
					 Factor = Math.exp(-(R_from_win/R));
				}

				newpalette[c][0]=newpalette[c][0]+(int)((ETA*(data.get(a).Or-newpalette[c][0])*Factor));
			    newpalette[c][1]=newpalette[c][1]+(int)((ETA*(data.get(a).Og-newpalette[c][1])*Factor));
			    newpalette[c][2]=newpalette[c][2]+(int)((ETA*(data.get(a).Ob-newpalette[c][2])*Factor));
			}
		}


		R=R*0.975;
		if(R<0.1){R=0.01;}
		ETA=ETA*0.975;
		if(ETA<0.01){ETA=0.01;}

		if(l==num){k=1; break;}
		System.out.println(l);

	}while(k==0);

		for(int i=0;i<codebook;i++){
			center.get(i).Or=(int) newpalette[i][0];
			center.get(i).Og=(int) newpalette[i][1];
			center.get(i).Ob=(int) newpalette[i][2];
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
