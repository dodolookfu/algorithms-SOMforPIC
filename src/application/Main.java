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

public class Main extends Application {
	//control
	int count=0;int p=0;int j=0;

	//som initial parameter
	double R=0.5;
	double R_RATE=0.95;
	double R_MIN=0.1;
	double ETA=1;
	double ETA_RATE=0.95;
	double ETA_MIN=0.01;
	//CHARTset
	String ORed = "OldRed";
	String LRed = "NewRed";
	String OGreen = "OldGreen";
	String LGreen = "NewGreen";
	String OBlue = "OldBlue";
	String LBlue = "NewBlue";
	int[] ctotal = new int[3];
	int[] ctotal2 = new int[3];
	//Array
	ArrayList<RGB_app> colorAry = new ArrayList<RGB_app>(); //counting color
	ArrayList<NoRepeat_color> noRepeatColor = new ArrayList<NoRepeat_color>(); //表格.randomcodebook
	ArrayList<Color_info> noRepeatAry = new ArrayList<Color_info>(); //過濾後的array
	ArrayList<RGB_group> data = new ArrayList<RGB_group>(); //initial image information
	ArrayList<Codebook> center = new ArrayList<Codebook>(); //save codebook

	//表格
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> bc =new BarChart<String,Number>(xAxis,yAxis);
    float newpalette[][];

    //宣告物件
  	Label label_codebook,label_param,label_pixel,label_time,label_color=new Label();
  	Label label_psnr,label_mse,label_counter,label_img1,label_img2=new Label();
  	TextField codeBook = new TextField("");
  	TextField threShold = new TextField("");
  	Button read,load,start,chartBtn,BscreenShot,abtn,abtn2;
  	ScrollPane sp1;
  	ImageView ori_image,com_image;
  	BufferedImage selectImage,selectImage2;
  	Image image,image2;
  	AnchorPane root;

  	public void start(Stage primaryStage) {
  		root = new AnchorPane();
  		Scene scene = new Scene(root,1170,700);
  		//宣告各物件名稱
  		ori_image= new ImageView();
  		com_image=new ImageView();
  		label_codebook=new Label("Codebook：");
  		label_param=new Label("ε：");
  		label_pixel=new Label("總像素(pixel)：");
  		label_time=new Label("執行時間(ms)：");
  		label_psnr=new Label("PSNR值(DB)：");
  		label_mse=new Label("MSE值：");
  		label_color=new Label("顏色數量：");
  		label_counter=new Label("執行次數：");
  		label_img1=new Label("原始圖像：");
  		label_img2=new Label("壓縮圖像：");
  		read = new Button("Select");
  		load = new Button("Load");
  		start = new Button("Start");
  		BscreenShot = new Button("Save");
  		chartBtn = new Button("Analysis");
  		abtn = new Button("Pixel");
  		abtn2 = new Button("Color");
  		//setid
  		ori_image.setId("jsp");
  		root.setId("root");
  		load.setId("btn1");
  		start.setId("btn1");
  		BscreenShot.setId("btn1");
  		read.setId("btn1");
  		chartBtn.setId("btn1");

  		//Label大小與位置
  		label_codebook.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_codebook, 20.0);AnchorPane.setLeftAnchor(label_codebook, 170.0);
  		label_param.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_param, 60.0);AnchorPane.setLeftAnchor(label_param, 170.0);
  		label_pixel.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_pixel, 20.0);AnchorPane.setLeftAnchor(label_pixel, 520.0);
  		label_color.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_color, 60.0);AnchorPane.setLeftAnchor(label_color, 520.0);
  		label_psnr.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_psnr, 20.0);AnchorPane.setLeftAnchor(label_psnr, 680.0);
  		label_mse.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_mse, 60.0);AnchorPane.setLeftAnchor(label_mse, 680.0);
  		label_time.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_time, 20.0);AnchorPane.setLeftAnchor(label_time, 850.0);
  		label_counter.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_counter, 60.0);AnchorPane.setLeftAnchor(label_counter, 850.0);
  		label_img1.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_img1, 110.0);AnchorPane.setLeftAnchor(label_img1, 30.0);
  		label_img2.setPrefSize(180, 30);AnchorPane.setTopAnchor(label_img2, 110.0);AnchorPane.setLeftAnchor(label_img2, 611.0);
  		//TEXTFILD
  		codeBook.setPrefSize(70, 30);AnchorPane.setTopAnchor(codeBook, 20.0);AnchorPane.setLeftAnchor(codeBook, 247.0);
  		threShold.setPrefSize(70, 30);AnchorPane.setTopAnchor(threShold, 60.0);AnchorPane.setLeftAnchor(threShold, 247.0);
  		//btn大小與位置
  		read.setPrefSize(120, 30);AnchorPane.setTopAnchor(read, 20.0);AnchorPane.setLeftAnchor(read, 20.0);
  		load.setPrefSize(120, 30);AnchorPane.setTopAnchor(load, 60.0);AnchorPane.setLeftAnchor(load, 20.0);
  		start.setPrefSize(120, 30);AnchorPane.setTopAnchor(start, 20.0);AnchorPane.setLeftAnchor(start, 350.0);
  		BscreenShot.setPrefSize(120, 30);AnchorPane.setTopAnchor(BscreenShot, 60.0);AnchorPane.setLeftAnchor(BscreenShot, 350.0);
  		chartBtn.setPrefSize(120, 30);AnchorPane.setTopAnchor(chartBtn, 38.0);AnchorPane.setLeftAnchor(chartBtn, 1015.0);
  		//VIEW
  		ori_image.setFitHeight(512);ori_image.setFitWidth(512);AnchorPane.setTopAnchor(ori_image, 140.0);AnchorPane.setLeftAnchor(ori_image, 30.0);
  		com_image.setFitHeight(512);com_image.setFitWidth(512);AnchorPane.setTopAnchor(com_image, 140.0);AnchorPane.setLeftAnchor(com_image, 611.0);
  		//add
  		root.getChildren().addAll(label_codebook,label_param,label_pixel,label_time,label_color,label_psnr,label_mse,label_counter,label_img1,label_img2,read,load,start,BscreenShot,ori_image,com_image,codeBook,threShold,chartBtn);
  		//顯示
  		primaryStage.setTitle("Image compression");
  		primaryStage.setScene(scene);
  		primaryStage.setMaxHeight(700);primaryStage.setMaxWidth(1170);
  		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
  		primaryStage.show();

  		//analysis
  		chartBtn.setOnAction(new EventHandler<ActionEvent>() {
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
  	        APPCOLOR(1,noRepeatColor,data,newpalette);
  	        updateChart(1,ctotal,ctotal2);

  	        abtn.setOnAction(new EventHandler<ActionEvent>(){
  	            @Override
  	            public void handle(ActionEvent arg0) {
  		            int w=1;
  		            APPCOLOR(w,noRepeatColor,data,newpalette);
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
  	                APPCOLOR(w,noRepeatColor,data,newpalette);
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

  	        // 把原先的視窗隱藏起來
  	        // ((Node) (event.getSource())).getScene().getWindow().hide();

  	        }
  	    });

  		//儲存影像
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
  				         ImageIO.write(SwingFXUtils.fromFXImage(com_image.getImage(),null), "png", file);
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
  			        ori_image.setImage(image);
  			    } catch (IOException ex) {
  			        System.out.println("讀取失敗!\n說明:"+ex.toString());
  			    }
  			}
  		});


  		load.setOnAction(new EventHandler<ActionEvent>() {
  			@Override
  			public void handle(ActionEvent e) {
  				//開始時間
  				long starttime = System.currentTimeMillis();
  				int hight = selectImage.getHeight();
  				int width = selectImage.getWidth();
  				int count=0;
  				for(int x=0;x<width;x++){
  					for(int y=0;y<hight;y++){
  						int rgb=selectImage.getRGB(x,y);       // 取得像數點RGB值
  						int r=(rgb&0x00ff0000)>>16;            // 取得紅色的資料
  						int g=(rgb&0x0000ff00)>>8;             // 取得綠色資料
  						int b=rgb&0x000000ff;                  // 取得藍色資料
  						data.add(new RGB_group(x,y,r,g,b,0));      // 資料寫入
  						int color=(r*256*256)+g*256+b;  //顏色計算
  						colorAry.add(new RGB_app(color,count,0,0)); //顏色計算陣列
  						count++;
  					}
  				}

  				label_pixel.setText("總像素(pixel)："+hight*width);
  				//顏色排序
  				Collections.sort(colorAry,new Comparator<RGB_app>() {
  					public int compare(RGB_app o1, RGB_app o2) {
  						return o2.color-o1.color;
  					}
  				});

  				int number=0;
  				//計算顏色相同
  				for (int i=0;i<data.size();i++){
  					if(i>0){
  						if(colorAry.get(i).color!=colorAry.get(i-1).color){
  							colorAry.get(i-1).app=number+1;
  							number=0;
  						}else{
  							number++;
  						}
  					}
  				}
  				if(colorAry.get(data.size()-1).color!=colorAry.get(data.size()-2).color){
  					colorAry.get(data.size()-1).app=1;
  					number=0;
  				}
  				//顏色過濾
  				for(int i=0;i<data.size();i++){
  					if(colorAry.get(i).app!=0){
  						noRepeatAry.add(new Color_info(colorAry.get(i).num,colorAry.get(i).app,colorAry.get(i).color));
  					}
  				}

  				label_color.setText("顏色數量："+noRepeatAry.size());

  				//noRepeatColor不重複顏色
  				System.out.println(noRepeatAry.size());
  				for(int i=0;i<noRepeatAry.size();i++){
  					noRepeatColor.add(new NoRepeat_color(data.get(noRepeatAry.get(i).num).Or,data.get(noRepeatAry.get(i).num).Og,data.get(noRepeatAry.get(i).num).Ob,noRepeatAry.get(i).num,noRepeatAry.get(i).app,0));
  				}

  				int codebook = Integer.parseInt(codeBook.getText()); // 群數
  				int[] centerpoint = new int[codebook];
  				initial_seed(centerpoint,noRepeatAry);

  				long endtime = System.currentTimeMillis();

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
			    for(int a=0;a<codebook;a++){
					if(newpalette[a][0]==0 && newpalette[a][1]==0 && newpalette[a][2]==0){
						int x=(int)(Math.random()*noRepeatColor.size());
						newpalette[a][0]=noRepeatColor.get(x).Or;
						newpalette[a][1]=noRepeatColor.get(x).Og;
						newpalette[a][2]=noRepeatColor.get(x).Ob;
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
				label_psnr.setText("PSNR值："+String.valueOf(result)+"DB");
				label_mse.setText("MSE值："+NEWMSE);
				label_counter.setText("執行次數："+num+" 次");

				long ktime2 = System.currentTimeMillis();
				long kfinish2 = ((ktime2-ktime));

				label_time.setText("執行時間(ms)："+kfinish2+"ms");
			}
		});
	}

	//display newimage
	public void resetChoose(ArrayList<RGB_group> data,float [][]newpalette)
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
		com_image.setImage(image2);

	}

	//initial codebook
	public void initial_seed(int[] centerpoint,ArrayList<Color_info> noRepeatAry)
	{
		 int codebook = Integer.parseInt(codeBook.getText());
		 for(int b=0;b<codebook;b++){
			centerpoint[b]=(int)(Math.random()*noRepeatColor.size());
			centerpoint[b]=noRepeatColor.get(centerpoint[b]).num;
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
		 for(int i=0;i<codebook;i++)
		 {
			 center.add(new Codebook(data.get(centerpoint[i]).Or,data.get(centerpoint[i]).Og,data.get(centerpoint[i]).Ob,x,y));
			 System.out.println(center.get(i).Or+" "+center.get(i).Og+" "+center.get(i).Ob);
			 y++;
			 if(y==ss){
				 x++;
				 y=0;
			 }
		 }
	}

	//表格用的顏色計算
	public void APPCOLOR(int w,ArrayList<NoRepeat_color> noRepeatColor,ArrayList<RGB_group> data,float [][]newpalette)
	{
		int codebook = Integer.parseInt(codeBook.getText());
		int[][] COLOR = new int[3][3];
		int win=0;
		COLOR[0][0]=255;COLOR[0][1]=0;COLOR[0][2]=0;
		COLOR[1][0]=0;COLOR[1][1]=255;COLOR[1][2]=0;
		COLOR[2][0]=0;COLOR[2][1]=0;COLOR[2][2]=255;

		if(w==1){
		for(int a=0;a<noRepeatColor.size();a++){
			float minVaule = (float) 999999999999999.0;

			for(int b=0;b<COLOR.length;b++)
			{
				//RED
				float rr = (noRepeatColor.get(a).Or-COLOR[b][0])*(noRepeatColor.get(a).Or-COLOR[b][0]);
				float gg = (noRepeatColor.get(a).Og-COLOR[b][1])*(noRepeatColor.get(a).Og-COLOR[b][1]);
				float bb = (noRepeatColor.get(a).Ob-COLOR[b][2])*(noRepeatColor.get(a).Ob-COLOR[b][2]);
				float calculator = (float) Math.sqrt(rr+gg+bb);
				if(calculator<minVaule)
				{
					minVaule = calculator;
					win = b;
				}
			}
			ctotal[win]=ctotal[win]+noRepeatColor.get(a).app;
		}

		for(int a=0;a<data.size();a++){
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
			for(int a=0;a<noRepeatColor.size();a++)
			{
				float minVaule = (float) 999999999999999.0;

				for(int b=0;b<COLOR.length;b++)
				{

					float rr = (noRepeatColor.get(a).Or-COLOR[b][0])*(noRepeatColor.get(a).Or-COLOR[b][0]);
					float gg = (noRepeatColor.get(a).Og-COLOR[b][1])*(noRepeatColor.get(a).Og-COLOR[b][1]);
					float bb = (noRepeatColor.get(a).Ob-COLOR[b][2])*(noRepeatColor.get(a).Ob-COLOR[b][2]);
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

	//圖表更新
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

	//分群
	public void clustering(){
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
			if(ETA<0.01) ETA=0.01;
			if(l==num) break;
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
