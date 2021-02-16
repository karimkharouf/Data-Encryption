/***********************************************************************************************************
 * Program by Yara Hamad, Mahdi Choaib, and Karim Kharouf
 * User logs in (if he has an account) or creates a new account. 
 * User has option to encrypt/decrypt after he has logged in. User can edit his data while decrypted.
 * Admin account has extra features. All users can edit their account info.
 ***********************************************************************************************************/
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Main extends Application 
{
	private static boolean signUpDec;	 	//makes account decision (1 to login, 2 to signup (1 by default))

	private static String currentUser,	//to know the user currently logged in
	currentData;	//to know the data of the current user

	static Button 
	//intro buttons
	Exit1 = new Button("Exit"), 
	SignUp = new Button("Sign Up"),
	LogIn = new Button("Log In"),

	//login/signup buttons
	CancelLogIn = new Button("Cancel"),
	CancelSignUp = new Button("Cancel"),
	ConfirmLogIn = new Button("Confirm"),
	ConfirmSignUp = new Button("Confirm"),

	//user buttons
	Exit2 = new Button("Exit"),
	Logout = new Button("Log Out"),			
	Encrypt = new Button("Encrypt"), 
	Decrypt = new Button("Decrypt"),
	accountOp = new Button("Account Options"), 
	addData = new Button("My Data"),
	saveChanges = new Button("Save Changes"),	//data editing
	Cancel = new Button("Cancel"),				//data editing

	//user account options buttons
	changeUserName = new Button("Change Username"),
	changePass = new Button("Change Password"),
	changePic = new Button("Change Profile Picture"),
	deleteAcc = new Button("Delete Account"),
	deleteData = new Button("Delete Data"),
	BackUpData = new Button("Backup Data"),
	BackAccOp = new Button("Go Back"),

	//admin extra buttons
	adminOptions = new Button("Admin Options"),
	DeleteAll = new Button("Delete All Accounts"),
	DeleteOne = new Button("Delete Other Accounts"),
	viewAcc = new Button("View Other Accounts"),
	BackAdmin = new Button("Go Back"),

	//pic editing
	plus = new Button("+"),
	minus = new Button("-");


	//scene labels and font / images
	Label label1 = new Label("Hello! Welcome to our program");
	Label label2 = new Label();
	Font f1 = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
	Image img;
	ImageView imgView;

	//intro
	VBox center1 = new VBox();

	//login/signup
	Text user,logInText,signUpText;
	static int logInCount;
	TextField userLogIn = new TextField();
	PasswordField passEnter = new PasswordField();
	TextField userSignUp = new TextField();
	StackPane logInGrp = new StackPane(), signUpGrp = new StackPane();

	//users
	VBox leftBox = new VBox(), rightBox = new VBox();
	HBox buttons = new HBox();
	StackPane pane = new StackPane();

	//user account options
	VBox paneAccOp = new VBox();

	//admin options
	VBox paneadminOp = new VBox();

	//editing data
	TextArea dataEdit = new TextArea();
	HBox buttonsEdit = new HBox();
	VBox editgrp = new VBox();

	//changing profile pic
	Group root;
	double xstart,ystart,xend,yend,scale;
	Rectangle rect;
	String newImagePath;
	Slider right,top;
	ImageView newView;
	Image newImg;
	Scene imgChangeScene;

	Scene scene;
	Stage primary;

	public static void main(String[] args)
	{
		currentUser=null;
		signUpDec=false;
		launch(args);
	}

	
	public void accountOpHandle(ActionEvent event)
	{
		if (event.getSource() == accountOp)
		{
			if (currentUser.equals("admin"))
			{
				paneAccOp.getChildren().clear();
				paneAccOp.getChildren().addAll(changePass,changePic,deleteData,BackUpData,BackAccOp); 
			}
			else
			{
				paneAccOp.getChildren().clear();
				paneAccOp.getChildren().addAll(changeUserName,changePass,changePic,deleteAcc,deleteData,BackUpData,BackAccOp);
			}

			scene.setRoot(paneAccOp);
			primary.setTitle("Account Options");
		}

		if (event.getSource() == BackAccOp)
		{
			if (currentUser.equals("admin"))
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons,adminOptions);
				pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
				adminOptions.setTranslateY(-15);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
				imgView.setManaged(false);
				imgView.setLayoutX(200); imgView.setLayoutY(1);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}

			else		
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
				imgView.setManaged(false);
				imgView.setLayoutX(200); imgView.setLayoutY(1);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}

			primary.setTitle("Options");
			primary.setScene(scene);
			return;
		}

		if (event.getSource() == changeUserName)
		{
			if (currentUser.equals("admin"))
			{
				Alert alrr = new Alert(AlertType.ERROR , "Unfortunately, admin account username can't be changed." , ButtonType.OK);
				alrr.showAndWait();
				return;
			}

			TextInputDialog dialog11 = new TextInputDialog();
			dialog11.setHeaderText("Enter your current password:");
			dialog11.setContentText("Current Password");
			dialog11.setTitle("");
			Optional<String> code11 = dialog11.showAndWait();					
			if (code11.isPresent())
			{
				if (code11.get()==null || code11.get().isEmpty())
					return;

				if (!accounts.CHECKING(accounts.CheckingCases.Essential_Files))
				{
					Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
					ERR.showAndWait();
					return;
				}

				DecryptCases.DecryptAccounts();
				if (!code11.get().equals(accounts.PassWordsReader(currentUser)))
				{
					EncryptCases.EncryptAccounts();
					Alert alr = new Alert(AlertType.ERROR , "Password incorrect!" , ButtonType.OK);
					alr.showAndWait();
					return;
				}

				else
				{
					EncryptCases.EncryptAccounts();
					TextInputDialog dialog22 = new TextInputDialog();
					dialog22.setHeaderText("Enter new username:");
					dialog22.setContentText("New Username");
					dialog22.setTitle("");
					Optional<String> code22 = dialog22.showAndWait();
					if (code22.isPresent())
					{
						if (code22.get()==null || code22.get().isEmpty())
							return;

						if (!accounts.CHECKING(accounts.CheckingCases.Essential_Files))
						{
							Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
							ERR.showAndWait();
							return;
						}

						DecryptCases.DecryptAccounts();
						if (accounts.AccountExists(code22.get()))
						{
							EncryptCases.EncryptAccounts();
							Alert alrt = new Alert(AlertType.ERROR , "Username already exists!" , ButtonType.OK);
							alrt.showAndWait();
							return;
						}

						EncryptCases.EncryptAccounts();
						if (code22.get()==null || code22.get().isEmpty() || code22.get().contains(" ") || code22.get().contains("!") || code22.get().contains("@") || code22.get().contains("#") || code22.get().contains("$") || code22.get().contains("%") || code22.get().contains("^")|| code22.get().contains("&") || code22.get().contains("*") || code22.get().contains("(") || code22.get().contains(")") || code22.get().contains("-") || code22.get().contains("=") || code22.get().contains("+") || code22.get().contains(".") || code22.get().contains(",") || code22.get().contains("/") || code22.get().contains("?") || code22.get().contains(":") || code22.get().contains(";") || /*currentUser.contains("\"") ||*/ code22.get().contains("\'") || code22.get().contains("\\") || code22.get().length()>10) //restrictions: username<=10 characters/no spaces/ cant be empty/no !,@,#,$,%,^,&,*,(,),:,:;,,,.,/,-,+,= character			{
						{	
							Alert al = new Alert(AlertType.ERROR , "Invalid username! (No spaces/max. 10 characters/you cannot use \"!,@,#,$,%,^,&,*,(,),:,:;,,,.,/,-,+,=\" characters)",ButtonType.OK);
							al.showAndWait();
							return;
						}

						TextInputDialog dialog33 = new TextInputDialog();
						dialog33.setHeaderText("Confirm new username:");
						dialog33.setContentText("Confirm New UserName");
						dialog33.setTitle("");
						Optional<String> code33 = dialog33.showAndWait();
						if (code33.isPresent())
						{
							if (code33.get()==null || code33.get().isEmpty())
								return;

							if (!code33.get().equals(code22.get()))
							{
								Alert ale = new Alert(AlertType.ERROR , "Passwords do not match!" , ButtonType.OK);
								ale.showAndWait();
								return;
							}

							else
							{
								if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Essential_Files,accounts.CheckingCases.Data_File))
								{
									Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
									ERR.showAndWait();
									return;
								}

								DecryptCases.DecryptAccounts();
								accounts.changeUsername(currentUser, code33.get());
								currentUser = code33.get();
								EncryptCases.EncryptAccounts();
								Alert al = new Alert(AlertType.INFORMATION , "Sucess!" , ButtonType.OK);
								al.showAndWait();
								return;
							}
						}
					}
				}
			}
		}

		if (event.getSource() == changePass)
		{
			TextInputDialog dialog1 = new TextInputDialog();
			dialog1.setHeaderText("Enter your current password:");
			dialog1.setContentText("Current Password");
			dialog1.setTitle("");
			Optional<String> code1 = dialog1.showAndWait();					
			if (code1.isPresent())
			{
				if (code1.get()==null || code1.get().isEmpty())
					return;

				if (!accounts.CHECKING(accounts.CheckingCases.Essential_Files))
				{
					Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
					ERR.showAndWait();
					return;
				}

				DecryptCases.DecryptAccounts();
				if (!code1.get().equals(accounts.PassWordsReader(currentUser)))
				{
					EncryptCases.EncryptAccounts();
					Alert alr = new Alert(AlertType.ERROR , "Password incorrect!" , ButtonType.OK);
					alr.showAndWait();
					return;
				}

				else
				{
					EncryptCases.EncryptAccounts();
					TextInputDialog dialog2 = new TextInputDialog();
					dialog2.setHeaderText("Enter new password:");
					dialog2.setContentText("New Password");
					dialog2.setTitle("");
					Optional<String> code2 = dialog2.showAndWait();
					if (code2.isPresent())
						if (code2.get()==null || code2.get().isEmpty())
							return;

					TextInputDialog dialog3 = new TextInputDialog();
					dialog3.setHeaderText("Confirm new password:");
					dialog3.setContentText("Confirm New Password");
					dialog3.setTitle("");
					Optional<String> code3 = dialog3.showAndWait();
					if (code3.isPresent())
					{
						if (code3.get()==null || code3.get().isEmpty())
							return;

						if (!code3.get().equals(code2.get()))
						{
							Alert ale = new Alert(AlertType.ERROR , "Passwords do not match!" , ButtonType.OK);
							ale.showAndWait();
							return;
						}

						else
						{
							if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Essential_Files, accounts.CheckingCases.Data_File))
							{
								Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
								ERR.showAndWait();
								return;
							}

							DecryptCases.DecryptAccounts();
							accounts.changePass(currentUser, code3.get());
							EncryptCases.EncryptAccounts();
							Alert al = new Alert(AlertType.INFORMATION , "Sucess!" , ButtonType.OK);
							al.showAndWait();
							return;
						}
					}
				}
			}
		}

		if (event.getSource() == deleteAcc)
		{
			if (currentUser.equals("admin"))
			{
				Alert alr = new Alert(AlertType.ERROR , "Cannot delete admin account!" , ButtonType.OK);
				alr.showAndWait();
				return;
			}

			Alert y = new Alert(AlertType.WARNING , "You are about to delete your account, along with all your data. Are you sure?" , ButtonType.YES , ButtonType.NO);
			Optional<ButtonType> result = y.showAndWait();
			if (result.get() == ButtonType.YES)
			{
				TextInputDialog dialogB = new TextInputDialog();
				dialogB.setHeaderText("Enter your password:");
				dialogB.setContentText("Current Password");
				dialogB.setTitle("");
				Optional<String> codeB = dialogB.showAndWait();					
				if (codeB.isPresent())
				{
					if (codeB.get()==null || codeB.get().isEmpty())
						return;

					if (!accounts.CHECKING(accounts.CheckingCases.Essential_Files))
					{
						Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
						ERR.showAndWait();
						return;
					}

					DecryptCases.DecryptAccounts();
					if (!codeB.get().equals(accounts.PassWordsReader(currentUser)))
					{
						EncryptCases.EncryptAccounts();
						Alert alr = new Alert(AlertType.ERROR , "Password incorrect!" , ButtonType.OK);
						alr.showAndWait();
						return;
					}

					EncryptCases.EncryptAccounts();
				}
			}

			else
				return;

			try
			{
				if (!accounts.CHECKING(accounts.CheckingCases.Main_Files))
				{
					Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
					ERR.showAndWait();
					return;
				}

				DecryptCases.DecryptAccounts();
				DecryptCases.DecryptImages();
				File dataFile = new File("Data_" + currentUser + ".txt");
				File outputFile = new File("Output_" + currentUser + ".png");
				if (dataFile.exists())
					dataFile.delete();
				if (outputFile.exists())
					outputFile.delete();

				File accs = new File("accounts.txt");
				File imgs = new File("profImages.txt");
				Scanner accScan = new Scanner(accs) , imgScan = new Scanner(imgs), lineScan;
				ArrayList<String> before = new ArrayList<String>();
				ArrayList<String> after = new ArrayList<String>();
				boolean found = false;

				while (accScan.hasNext())
				{
					String line = accScan.nextLine();
					lineScan = new Scanner(line);
					lineScan.useDelimiter("##");
					if (lineScan.next().equals(currentUser))
					{
						found = true;
						continue;
					}

					if (!found)
						before.add(line);
					else
						after.add(line);
				}

				PrintWriter printFile = new PrintWriter(accs);
				for (int count1=0 ; count1<before.size() ; count1++)
					printFile.println(before.get(count1));

				for (int count2=0 ; count2<after.size() ; count2++)
					printFile.println(after.get(count2));

				printFile.close();
				accScan.close();
				before.clear();
				after.clear();
				found=false;

				while (imgScan.hasNext())
				{
					String line = imgScan.nextLine();
					lineScan = new Scanner(line);
					lineScan.useDelimiter("##");
					if (lineScan.next().equals(currentUser))
					{
						found = true;
						continue;
					}

					if (!found)
						before.add(line);
					else
						after.add(line);
				}

				PrintWriter printFile2 = new PrintWriter(imgs);
				for (int count1=0 ; count1<before.size() ; count1++)
					printFile2.println(before.get(count1));

				for (int count2=0 ; count2<after.size() ; count2++)
					printFile2.println(after.get(count2));

				printFile2.close();
				imgScan.close();

				EncryptCases.EncryptAccounts();
				EncryptCases.EncryptImages();

				currentUser = null;
				primary.setTitle("YMK");
				signUpDec=false;
				scene.setRoot(center1);
			}
			catch (IOException e) {}
		}
	}


	public void changePicHandle()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION , "Save Changes?" , ButtonType.YES , ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES)
		{
			if (rect.getWidth()<40 || rect.getHeight()<40)
			{
				Alert alrt = new Alert(AlertType.ERROR , "View should be 40x40 minimum!" , ButtonType.OK);
				alrt.showAndWait();
				return;
			}
		}
		else
		{
			if (currentUser.equals("admin"))
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons,adminOptions);
				pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
				adminOptions.setTranslateY(-15);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				pane.setAlignment(imgView, Pos.TOP_RIGHT);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}

			else		
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				pane.setAlignment(imgView, Pos.TOP_RIGHT);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}

			primary.setTitle("Options");
			primary.setScene(scene);
			return;
		}

		if (currentUser.equals("admin"))
		{
			pane.getChildren().clear();
			pane.setAlignment(Pos.CENTER);
			pane.getChildren().addAll(label2,imgView,buttons,adminOptions);
			pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
			adminOptions.setTranslateY(-15);
			pane.setAlignment(label2, Pos.TOP_LEFT);
			pane.setAlignment(imgView, Pos.TOP_RIGHT);
			scene.setRoot(pane);
			label2.setText("Current User: " + currentUser);
			label2.setFont(f1);
		}

		else		
		{
			pane.getChildren().clear();
			pane.setAlignment(Pos.CENTER);
			pane.getChildren().addAll(label2,imgView,buttons);
			pane.setAlignment(label2, Pos.TOP_LEFT);
			pane.setAlignment(imgView, Pos.TOP_RIGHT);
			scene.setRoot(pane);
			label2.setText("Current User: " + currentUser);
			label2.setFont(f1);
		}

		if (!accounts.CHECKING(accounts.CheckingCases.Main_Files))
		{
			Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
			ERR.showAndWait();
			return;
		}

		imgView.setImage(newImg);
		imgView.setLayoutX(0); 	imgView.setLayoutY(0);
		imgView.getTransforms().add(new Scale(scale,scale,0,0));
		double width = rect.getWidth()/scale;
		double height = rect.getHeight()/scale;

		double x = rect.getX()-25;
		double y = rect.getY()-25;
		imgView.setViewport(new Rectangle2D(x,y,width,height));

		DecryptCases.DecryptImages();
		accounts.setImage(currentUser, newImagePath, scale, x, y, width, height);
		EncryptCases.EncryptImages();
		primary.setTitle("Options");
		primary.setScene(scene);
		return;
	}


	public void picScene(ActionEvent event)
	{	
		plus.setMinSize(30,30);			minus.setMinSize(30,30);
		plus.setMaxSize(30, 30);		minus.setMaxSize(30,30);
		plus.setOnAction(eventa -> {
			if (scale<4)	
				scale+=0.25;

			else
				scale=4;

			root.getChildren().removeAll(newView,rect);
			newView = new ImageView(newImg);
			newView.setLayoutX(0);		newView.setLayoutY(0);
			newView.getTransforms().add(new Scale(scale,scale,0,0));
			newView.setViewport(new Rectangle2D(0,0,250/(scale),250/(scale)));
			newView.setLayoutX(25); 	newView.setLayoutY(25);
			rect = new Rectangle(0,0,0,0);
			root.getChildren().addAll(rect,newView);

			top.setValue(0); right.setValue(0);

			if (newImg.getWidth()*scale>250)
				top.setMax(newImg.getWidth()*scale-250);	
			else
				top.setMax(0);
			if (newImg.getHeight()*scale>250)
				right.setMax(newImg.getHeight()*scale-250);
			else 
				right.setMax(0);
		}); 

		minus.setOnAction(eventb -> {
			if (scale>0.25)
				scale-=0.25;

			else
				scale=0.25;

			root.getChildren().removeAll(newView,rect);
			newView = new ImageView(newImg);
			newView.setLayoutX(0);	 newView.setLayoutY(0);
			newView.getTransforms().add(new Scale(scale,scale,0,0));
			newView.setViewport(new Rectangle2D(0,0,250/scale,250/scale));
			newView.setLayoutX(25);	 newView.setLayoutY(25);
			rect = new Rectangle(0,0,0,0);
			root.getChildren().addAll(rect,newView);

			top.setValue(0); right.setValue(0);

			if (newImg.getWidth()*scale>250)
				top.setMax(newImg.getWidth()*scale-250);	
			else
				top.setMax(0);
			if (newImg.getHeight()*scale>250)
				right.setMax(newImg.getHeight()*scale-250);
			else 
				right.setMax(0);
		});



		FileChooser fc = new FileChooser();
		ExtensionFilter png = new ExtensionFilter("png", "*.png");
		ExtensionFilter jpg = new ExtensionFilter("jpg", "*.jpg");
		fc.getExtensionFilters().addAll(png,jpg);
		newImagePath="";
		try {newImagePath = fc.showOpenDialog(primary).getAbsolutePath();}
		catch (NullPointerException e) {}
		if (newImagePath==null || newImagePath.isEmpty()) //if user presses cancel/x
			return;

		scale = 1;
		newImg = new Image("file:" + newImagePath);
		newView = new ImageView(newImg);
		right = new Slider();	top = new Slider();
		top.setMin(0); 		right.setMin(0);
		top.setMax(0); 		right.setMax(0);
		newView.setViewport(new Rectangle2D(0,0,250,250));

		if (newImg.getWidth()>250)
			top.setMax(newImg.getWidth()-250);		
		if (newImg.getHeight()>250)
			right.setMax(newImg.getHeight()-250);

		top.setOnMouseDragged(event3 -> {
			root.getChildren().removeAll(newView,rect);
			newView = new ImageView(newImg);
			newView.setLayoutX(0); newView.setLayoutY(0);
			newView.getTransforms().add(new Scale(scale,scale,0,0));
			newView.setViewport(new Rectangle2D(top.getValue()/scale,right.getValue()/scale,250/(scale),250/(scale)));
			newView.setLayoutX(25); newView.setLayoutY(25);
			rect = new Rectangle(0,0,0,0);
			root.getChildren().addAll(rect,newView);
		});

		right.setOnMouseDragged(event4 -> {
			root.getChildren().removeAll(newView,rect);
			newView = new ImageView(newImg);
			newView.setLayoutX(0); newView.setLayoutY(0);
			newView.getTransforms().add(new Scale(scale,scale,0,0));
			newView.setViewport(new Rectangle2D(top.getValue()/scale,right.getValue()/scale,250/(scale),250/(scale)));
			newView.setLayoutX(25); newView.setLayoutY(25);
			rect = new Rectangle(0,0,0,0);
			root.getChildren().addAll(rect,newView);
		});

		root = new Group();
		root.getChildren().addAll(newView,top,right,plus,minus);

		right.setRotate(90);
		newView.setLayoutX(25); 	newView.setLayoutY(25);
		right.setMinWidth(250); right.setMaxWidth(250);
		top.setMinWidth(250); top.setMaxWidth(250);
		top.setLayoutX(25); 	top.setLayoutY(10);
		right.setLayoutX(160);  right.setLayoutY(145);

		plus.setLayoutX(150);	 plus.setLayoutY(275); 		plus.setTranslateX(-15-25);
		minus.setLayoutX(150);	 minus.setLayoutY(275); 	minus.setTranslateX(-15+25);

		rect = new Rectangle(0,0,0,0);

		imgChangeScene = new Scene(root,300,300);

		imgChangeScene.setOnMousePressed(event1 -> {
			if (event1.getX()>=25 && event1.getY()>=25 && event1.getX()<=(newImg.getWidth()*scale+25>275? 275 : newImg.getWidth()*scale+25) && event1.getY()<=(newImg.getHeight()*scale+25<275? newImg.getHeight()*scale+25 : 275))
			{
				root.getChildren().removeAll(rect);
				xstart = event1.getX();
				ystart = event1.getY();
				rect = new Rectangle(xstart,ystart,0,0);
				rect.setStroke(Color.RED);
				rect.setStrokeWidth(3);
				rect.setFill(Color.TRANSPARENT);
				root.getChildren().addAll(rect);
			}
		});

		imgChangeScene.setOnMouseDragged(event2 -> {
			if (!(event2.getX()>=25 && event2.getY()>=25 && event2.getX()<=275 && event2.getY()<=275) || event2.getX()>=newImg.getWidth()*scale+25 || event2.getY()>=newImg.getHeight()*scale+25)
				return;

			xend = event2.getX();
			yend = event2.getY();

			if (xend<25)
				xend=25;
			if (xend>275)
				xend=275;
			if (yend<25)
				yend=25;
			if (yend>275)
				yend=275;

			root.getChildren().remove(rect);
			rect = new Rectangle((xstart<xend? xstart : (xstart-xend>100? xstart-100 : xend)),(ystart<yend? ystart : (ystart-yend>100? ystart-100 : yend)),Math.abs(xstart-xend),Math.abs(ystart-yend));
			rect.setStroke(Color.GREEN);
			rect.setStrokeWidth(3);
			rect.setFill(Color.TRANSPARENT);
			root.getChildren().add(rect);

			if (rect.getWidth()<40 || rect.getHeight()<40)
				rect.setStroke(Color.RED);
			else
				rect.setStroke(Color.LIGHTGREEN);

			if (rect.getWidth() > 100)
				rect.setWidth(100);
			if (rect.getHeight() > 100)
				rect.setHeight(100);

		});

		imgChangeScene.setOnKeyPressed(key -> {
			switch (key.getCode())
			{
			case H:
				Alert alert = new Alert(AlertType.INFORMATION , "Use + and - buttons to zoom in and out. Use your mouse to select which part of your image you want as your profile picture (maximum 100x100). Press H for help. Press ENTER to confirm selection." , ButtonType.OK);
				alert.showAndWait();
				break;
			case ENTER:
				changePicHandle();
				break;
			default: break;
			}
		});

		primary.setScene(imgChangeScene);
		primary.resizableProperty().setValue(false);
		primary.show();

		Alert a = new Alert(AlertType.INFORMATION , "Use + and - buttons to zoom in and out. Use your mouse to select which part of your image you want as your profile picture (maximum 100x100). Press H for help. Press ENTER to confirm selection." , ButtonType.OK);
		a.showAndWait();
	}


	public void exitHandle(ActionEvent event)
	{
		if (event.getSource() == Exit1) 
			System.exit(0);


		if (event.getSource() == Exit2)
		{
			if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File, accounts.CheckingCases.Essential_Files))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing." , ButtonType.OK);
				ERR.showAndWait();
				System.exit(0);
				return;
			}

			else
			{
				DecryptCases.DecryptAccounts();
				String status = accounts.getStatus(currentUser);
				EncryptCases.EncryptAccounts();
				if (status.equals("Enc"))
					System.exit(0);

				else
				{
					if (!accounts.emptyCheck(currentUser))
					{
						Alert alert = new Alert(AlertType.WARNING, "Your data is currently decrypted. Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.YES)
							System.exit(0);

						else 
							return;
					}

					else
						System.exit(0);

				}
			}
		}
	}


	public void logoutHandle(ActionEvent event)
	{
		if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File, accounts.CheckingCases.Essential_Files))
		{
			Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
			ERR.showAndWait();
			scene.setRoot(center1);
			return;
		}

		else
		{	
			DecryptCases.DecryptAccounts();
			String status = accounts.getStatus(currentUser);
			EncryptCases.EncryptAccounts();
			if (status.equals("Enc"))
			{
				currentUser = null;
				primary.setTitle("YMK");
				signUpDec=false;
				scene.setRoot(center1);
			}

			else
			{
				if (!accounts.emptyCheck(currentUser))
				{
					Alert alert = new Alert(AlertType.WARNING, "Your data is currently decrypted. Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.YES)
					{
						currentUser = null;
						primary.setTitle("YMK");
						signUpDec=false;
						scene.setRoot(center1);
					}

					else 
						return;
				}

				else
				{
					currentUser = null;
					primary.setTitle("YMK");
					signUpDec=false;
					scene.setRoot(center1);
				}
			}
		}
	}


	public void adminOpHandle(ActionEvent event)
	{
		if (event.getSource() == adminOptions)
		{
			primary.setTitle("Admin Options");
			scene.setRoot(paneadminOp);
		}

		if (event.getSource() == BackAdmin)
		{
			pane.getChildren().clear();
			pane.setAlignment(Pos.CENTER);
			pane.getChildren().addAll(label2,imgView,buttons,adminOptions);
			pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
			adminOptions.setTranslateY(-15);
			pane.setAlignment(label2, Pos.TOP_LEFT);
			buttons.setTranslateY(15);
			imgView.setManaged(false);
			imgView.setLayoutX(200); imgView.setLayoutY(1);
			scene.setRoot(pane);
			label2.setText("Current User: " + currentUser);
			label2.setFont(f1);
			primary.setTitle("Options");
			return;			
		}
	}


	public void EncrDecrHandle(ActionEvent event)
	{
		if (event.getSource() == Encrypt)
		{
			if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File, accounts.CheckingCases.Essential_Files))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}

			if (accounts.emptyCheck(currentUser))
			{
				Alert b = new Alert(AlertType.ERROR , "No data to encrypt!" , ButtonType.OK);
				b.showAndWait();
				return;
			}

			DecryptCases.DecryptAccounts();
			String status = accounts.getStatus(currentUser);
			EncryptCases.EncryptAccounts();
			if (status.equals("Enc"))
			{
				Alert b = new Alert(AlertType.ERROR , "Data already encrypted!" , ButtonType.OK);
				b.showAndWait();
				return;
			}

			long encryptionCode = encrypt.EncryptionCodeGenerator();
			int encryptcase = (int)(encryptionCode % 3); 
			switch(encryptcase)
			{
			case 0:					
				EncryptCases.EncryptCase0(currentUser);
				break;
			case 1:					
				EncryptCases.EncryptCase1(currentUser);
				break;
			case 2:				
				EncryptCases.EncryptCase2(currentUser);
				break;
			}

			DecryptCases.DecryptAccounts();
			accounts.setStatus(currentUser,encryptionCode);
			EncryptCases.EncryptAccounts();
			System.out.println("Encryption successful");
			Alert a = new Alert(AlertType.INFORMATION , "Encryption successful. Your encryption code is: " + encryptionCode + " make sure to save your code somewhere. You could lose your data without it." , ButtonType.OK);
			a.showAndWait();
		}

		if (event.getSource() == Decrypt)
		{
			if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File, accounts.CheckingCases.Essential_Files))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}

			if (accounts.emptyCheck(currentUser))
			{
				Alert b = new Alert(AlertType.ERROR , "No data to decrypt!" , ButtonType.OK);
				b.showAndWait();
				return;
			}

			DecryptCases.DecryptAccounts();
			String status = accounts.getStatus(currentUser);
			EncryptCases.EncryptAccounts();
			if (status.equals("Dec"))
			{
				Alert b = new Alert(AlertType.ERROR , "Data already decrypted!" , ButtonType.OK);
				b.showAndWait();
				return;
			}

			if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File, accounts.CheckingCases.Essential_Files, accounts.CheckingCases.Output_File))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}
			
			boolean check = true;
			long decryptionCode = 0;
			while (check)
			{
				try
				{
					TextInputDialog dialog = new TextInputDialog();
					dialog.setHeaderText("Please enter encryption code provided to you:");
					dialog.setTitle("");
					Optional<String> code = dialog.showAndWait();					
					if (code.isPresent())
					{
						if (code.get()==null || code.get().isEmpty())
							return;
						decryptionCode = Long.parseLong(code.get());
						check = false;
					}
				}
				catch(Exception NumberFormatException)
				{
					Alert err = new Alert(AlertType.ERROR , "Please insert code in numbers!" , ButtonType.OK);
					err.showAndWait();
					check=true;
				}
			}

			if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File, accounts.CheckingCases.Essential_Files, accounts.CheckingCases.Output_File))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}

			DecryptCases.DecryptAccounts();
			if (!(decryptionCode == accounts.getEncryptCode(currentUser)))
			{
				EncryptCases.EncryptAccounts();
				Alert b = new Alert(AlertType.ERROR , "Wrong encryption code!!!" , ButtonType.OK);
				b.showAndWait();
				return;
			}

			int decryptcase = (int)(decryptionCode % 3); 
			switch(decryptcase)
			{
			case 0:
				DecryptCases.DecryptCase0(currentUser);
				break;
			case 1:
				DecryptCases.DecryptCase1(currentUser);
				break;
			case 2:
				DecryptCases.DecryptCase2(currentUser);
				break;
			}

			accounts.setStatus(currentUser,-1);
			EncryptCases.EncryptAccounts();
			System.out.println("Decryption successful");
			Alert a = new Alert(AlertType.INFORMATION , "Decryption successful" , ButtonType.OK);
			a.showAndWait();
		}
	}


	public void dataEditHandle(ActionEvent event)
	{
		//Add data button
		if (event.getSource() == addData)
		{
			if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File, accounts.CheckingCases.Essential_Files))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}

			DecryptCases.DecryptAccounts();
			if (accounts.getStatus(currentUser).equals("Enc"))
			{
				EncryptCases.EncryptAccounts();
				Alert a = new Alert(AlertType.ERROR , "Data is encrypted. Decrypt to view your data!" , ButtonType.OK);
				a.showAndWait();
				return;
			}
			EncryptCases.EncryptAccounts();
			
			try 
			{
				File userFile = new File("Data_" + currentUser + ".txt");
				Scanner fileScan = new Scanner(userFile);
				String before="";
				while (fileScan.hasNext())
					before = before + fileScan.nextLine() + "\n";	

				currentData = before;

				dataEdit.setText(currentData);
				scene.setRoot(editgrp);
			}
			catch (IOException e) {}
		}

		//cancel button
		if (event.getSource() == Cancel)
		{
			if (currentUser.equals("admin"))
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons,adminOptions);
				pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
				adminOptions.setTranslateY(-15);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
				imgView.setManaged(false);
				imgView.setLayoutX(200); imgView.setLayoutY(1);
				imgView.setTranslateX(10);
				imgView.setTranslateY(-10);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}

			else
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
				imgView.setManaged(false);
				imgView.setLayoutX(200); imgView.setLayoutY(1);
				imgView.setTranslateX(10);
				imgView.setTranslateY(-10);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}
		}

		if (event.getSource() == saveChanges)
		{
			String data = dataEdit.getText();
			if (currentUser.equals("admin"))
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons,adminOptions);
				pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
				adminOptions.setTranslateY(-15);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
				imgView.setManaged(false);
				imgView.setLayoutX(200); imgView.setLayoutY(1);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}

			else
			{
				pane.getChildren().clear();
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(label2,imgView,buttons);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
				imgView.setManaged(false);
				imgView.setLayoutX(200); imgView.setLayoutY(1);
				scene.setRoot(pane);
				label2.setText("Current User: " + currentUser);
				label2.setFont(f1);
			}

			if (!accounts.CHECKING(currentUser, accounts.CheckingCases.Data_File))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}

			try 
			{
				File datafile = new File("Data_" + currentUser + ".txt");
				PrintWriter printFile = new PrintWriter(datafile);
				printFile.print(data);
				printFile.close();
			}
			catch (IOException e) {}
		}
	}


	public void deleteHandle(ActionEvent event)
	{	
		if (event.getSource() == DeleteAll)
		{
			Alert alert = new Alert(AlertType.WARNING, "You are about to delete all other accounts. Are you sure?", ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES)
			{/*continue to deleting stage*/}
			else 		
				return;

			if (!accounts.CHECKING(accounts.CheckingCases.Main_Files))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}
			
			TextInputDialog dialog11 = new TextInputDialog();
			dialog11.setHeaderText("Enter your password:");
			dialog11.setContentText("Password");
			dialog11.setTitle("");
			Optional<String> code11 = dialog11.showAndWait();					
			if (code11.isPresent())
			{
				if (code11.get()==null || code11.get().isEmpty())
					return;
			
				DecryptCases.DecryptAccounts();
				if (!code11.get().equals(accounts.PassWordsReader(currentUser)))
				{
					EncryptCases.EncryptAccounts();
					Alert ERR = new Alert(AlertType.ERROR , "Incorrect password!" , ButtonType.OK);
					ERR.showAndWait();
					return;
				}
			}

			
			//deleting accounts
			try 
			{
				//DecryptCases.DecryptAccounts();
				DecryptCases.DecryptImages();
				File acc = new File("accounts.txt");
				File imgs = new File("profImages.txt");
				Scanner accScan = new Scanner(acc);
				Scanner imgScan = new Scanner(imgs);
				accScan.nextLine();	//skip 1st line
				imgScan.nextLine(); //skip 1st line
				String adminInfo = accScan.nextLine();	//get admin account info
				String adminImg = imgScan.nextLine(); //get admin image with its details

				ArrayList<String> AccList = new ArrayList<String>();

				while ( accScan.hasNext() )
				{
					String line = accScan.nextLine();
					Scanner lineScan = new Scanner(line);
					lineScan.useDelimiter("##");
					AccList.add(lineScan.next());
				}

				ArrayList<File> FileList = new ArrayList<File>();
				for (int num=0 ; num<AccList.size() ; num++)
					FileList.add(new File("Data_" + AccList.get(num) + ".txt"));

				for (int count2=0 ; count2<FileList.size() ; count2++)
					FileList.get(count2).delete();	//deleting data files 

				ArrayList<File> OutputList = new ArrayList<File>();
				for (int num=0 ; num<AccList.size() ; num++)
					OutputList.add(new File("Output_" + AccList.get(num) + ".png"));

				for (int count2=0 ; count2<OutputList.size() ; count2++)
					OutputList.get(count2).delete();	//deleting image encryption files

				PrintWriter printAcc = new PrintWriter(acc);
				PrintWriter printImages = new PrintWriter(imgs);
				printAcc.println("UserName##Password##Enc-Dec status##Enc-Dec code:\n" + adminInfo);		
				printImages.println("Profile Images: (User##image_path##startx##starty##width##height)\n" + adminImg);
				printAcc.close();
				printImages.close();
				accScan.close();
				imgScan.close();
				EncryptCases.EncryptAccounts();
				EncryptCases.EncryptImages();

				Alert alert2 = new Alert(AlertType.INFORMATION, "Success!", ButtonType.OK);
				alert2.showAndWait();
				return;
			}
			catch (IOException e) {}
		}

		if (event.getSource() == DeleteOne)
		{

		}
	}


	public void viewAccsHandle(ActionEvent event)
	{
		if (!accounts.CHECKING(accounts.CheckingCases.Essential_Files))
		{
			Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
			ERR.showAndWait();
			return;
		}

		try
		{
			DecryptCases.DecryptAccounts();
			File accs = new File("accounts.txt");
			Scanner scan = new Scanner(accs);
			scan.nextLine();	//skip 1st line
			String accounts = "Accounts:\n";
			while (scan.hasNext())
			{
				String line = scan.nextLine();
				Scanner lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				accounts = accounts + lineScan.next() + "\n";
			}

			scan.close();
			EncryptCases.EncryptAccounts();
			Alert alert = new Alert(null, accounts , ButtonType.OK);
			alert.showAndWait();
		}
		catch (IOException e) {}
	}


	public void handle(ActionEvent event)
	{	
		if (event.getSource() == CancelLogIn || event.getSource() == CancelSignUp)
		{
			userLogIn.setText("");
			userSignUp.setText("");
			passEnter.setText("");
			scene.setRoot(center1);
			primary.setTitle("YMK");
			signUpDec=false;
		}

		//login button
		if (event.getSource() == LogIn)
		{
			logInCount = 0;
			scene.setRoot(logInGrp);
			primary.setTitle("LogIn");
			user.setText("Enter username:      \t\t\t\t\t\t   (" + (5-logInCount) + " tries left)");
		}

		//confirm login button
		if (event.getSource() == ConfirmLogIn)
		{
			currentUser = userLogIn.getText();
			if (currentUser==null || currentUser.isEmpty())	//if user presses confirm with no username, do nothing
			{
				passEnter.setText("");
				return;
			}

			if (!accounts.CHECKING(accounts.CheckingCases.Main_Files))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}

			logInCount++;
			user.setText("Enter username:      \t\t\t\t\t\t   (" + (5-logInCount) + (logInCount==4? " try left)" : " tries left)"));

			DecryptCases.DecryptAccounts();
			DecryptCases.DecryptImages();
			boolean exists = accounts.AccountExists(currentUser);

			if(exists)
			{
				String passAttempt = passEnter.getText();
				String password = accounts.PassWordsReader(currentUser);

				if (password.equals(passAttempt) && !passAttempt.isEmpty() && passAttempt!=null) //if login is successful, go to next stage in program
				{
					String imgPath = accounts.getImage(currentUser);
					File test = new File(imgPath);
					if (test.exists()) 
					{
						double scale = accounts.getImageScale(currentUser);
						double x = accounts.getImageX(currentUser);
						double y = accounts.getImageY(currentUser);
						double width = accounts.getImageWidth(currentUser);
						double height = accounts.getImageHeight(currentUser);
						img = new Image("file:" + imgPath);
						imgView = new ImageView(img);
						imgView.setManaged(false);
						imgView.setLayoutX(0); imgView.setLayoutY(0);
						imgView.getTransforms().add(new Scale(scale,scale,0,0));
						imgView.setViewport(new Rectangle2D(x,y,width,height));
					}

					else  //did not find user image
					{
						if (accounts.CHECKING(accounts.CheckingCases.Default_Img))
						{
							imgPath = "noprofile.png";
							accounts.setImage(currentUser, imgPath, 1, 0, 0, 100, 98);
							img = new Image("file:" + imgPath);
							imgView = new ImageView(img);
						}
						else
						{
							//
						}
					}

					if (currentUser.equals("admin"))
					{
						pane.getChildren().clear();
						pane.setAlignment(Pos.CENTER);
						if (accounts.CHECKING(accounts.CheckingCases.Default_Img) || test.exists())
						{	
							pane.getChildren().addAll(label2,imgView,buttons,adminOptions);
							pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
							adminOptions.setTranslateY(-15);
							pane.setAlignment(label2, Pos.TOP_LEFT);
							buttons.setTranslateY(15);
							imgView.setManaged(false);
							imgView.setLayoutX(200); imgView.setLayoutY(1);
						}
						else
						{
							pane.getChildren().addAll(label2,buttons,adminOptions);
							pane.setAlignment(adminOptions,Pos.BOTTOM_CENTER);
							adminOptions.setTranslateY(-15);
							pane.setAlignment(label2, Pos.TOP_LEFT);
							buttons.setTranslateY(15);
						}
						scene.setRoot(pane);
						label2.setText("Current User: " + currentUser);
						label2.setFont(f1);
					}

					else		
					{
						pane.getChildren().clear();
						pane.setAlignment(Pos.CENTER);
						if (accounts.CHECKING(accounts.CheckingCases.Default_Img) || test.exists())
						{	
							pane.getChildren().addAll(label2,imgView,buttons);
							pane.setAlignment(label2, Pos.TOP_LEFT);
							buttons.setTranslateY(15);
							imgView.setManaged(false);
							imgView.setLayoutX(200); imgView.setLayoutY(1);
						}
						else
						{
							pane.getChildren().addAll(label2,buttons);
							pane.setAlignment(label2, Pos.TOP_LEFT);
							buttons.setTranslateY(15);
						}
						scene.setRoot(pane);
						label2.setText("Current User: " + currentUser);
						label2.setFont(f1);
					}

					userLogIn.setText("");
					passEnter.setText("");
					primary.setTitle("Options");
					primary.setScene(scene);
					EncryptCases.EncryptAccounts();
					EncryptCases.EncryptImages();
					return;
				}

				else
				{
					EncryptCases.EncryptAccounts();
					EncryptCases.EncryptImages();
					Alert alert = new Alert(AlertType.ERROR , "Wrong Credentials!" , ButtonType.OK);
					alert.showAndWait();
				}
			}

			else
			{
				EncryptCases.EncryptAccounts();
				EncryptCases.EncryptImages();
				Alert alert = new Alert(AlertType.ERROR , "Wrong Credentials!" , ButtonType.OK);
				alert.showAndWait();
			}

			userLogIn.setText("");
			passEnter.setText("");

			if (logInCount==5) //if 5th time username is invalid, go to signup
				signUpDec=true;
		}

		//signup button
		if (event.getSource() == SignUp)
		{
			userSignUp.setText("");
			signUpText.setText("Sign Up:");
			signUpText.setFont(f1);
			scene.setRoot(signUpGrp);
			primary.setTitle("Sign Up");
		}

		//5 login attempts
		if (signUpDec)
		{
			userSignUp.setText("");
			signUpText.setText("5 failed attempts...\nPlease create a new account:");
			signUpText.setFont(f1);
			scene.setRoot(signUpGrp);
			primary.setTitle("Sign Up");
		}

		//confirm sign up button
		if (event.getSource() == ConfirmSignUp)
		{
			currentUser=userSignUp.getText();
			if (currentUser==null || currentUser.isEmpty() || currentUser.contains(" ") || currentUser.contains("!") || currentUser.contains("@") || currentUser.contains("#") || currentUser.contains("$") || currentUser.contains("%") || currentUser.contains("^")|| currentUser.contains("&") || currentUser.contains("*") || currentUser.contains("(") || currentUser.contains(")") || currentUser.contains("-") || currentUser.contains("=") || currentUser.contains("+") || currentUser.contains(".") || currentUser.contains(",") || currentUser.contains("/") || currentUser.contains("?") || currentUser.contains(":") || currentUser.contains(";") || /*currentUser.contains("\"") ||*/ currentUser.contains("\'") || currentUser.contains("\\") || currentUser.length()>10) //restrictions: username<=10 characters/no spaces/ cant be empty/no !,@,#,$,%,^,&,*,(,),:,:;,,,.,/,-,+,= character			{
			{	
				Alert al = new Alert(AlertType.ERROR , "Invalid username! (No spaces/max. 10 characters/you cannot use \"!,@,#,$,%,^,&,*,(,),:,:;,,,.,/,-,+,=\" characters)",ButtonType.OK);
				al.showAndWait();
				userSignUp.setText("");
				return;
			}

			if (!accounts.CHECKING(accounts.CheckingCases.Essential_Files))
			{
				Alert ERR = new Alert(AlertType.ERROR , "It appears an essential file is missing. Try again later." , ButtonType.OK);
				ERR.showAndWait();
				return;
			}

			DecryptCases.DecryptAccounts();
			DecryptCases.DecryptImages();
			boolean exists = accounts.AccountExists(currentUser);

			if (!exists)
				;
			else
			{
				EncryptCases.EncryptAccounts();
				EncryptCases.EncryptImages();
				Alert ale = new Alert(AlertType.ERROR , "Username is already taken, please enter different username.",ButtonType.OK);
				ale.showAndWait();
				userSignUp.setText("");
				return;
			}

			accounts newAcc = new accounts(currentUser);
			EncryptCases.EncryptAccounts();
			EncryptCases.EncryptImages();
			Alert alert = new Alert(AlertType.INFORMATION, "Your credentials are:\nUsername: " + currentUser + "\nPassword: " + accounts.Pass , ButtonType.OK);
			alert.showAndWait();

			pane.getChildren().clear();
			pane.setAlignment(Pos.CENTER);
			if (accounts.CHECKING(accounts.CheckingCases.Default_Img))
			{
				img = new Image("file:noprofile.png");
				imgView = new ImageView(img);
				pane.getChildren().addAll(label2,imgView,buttons);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
				imgView.setManaged(false);
				imgView.setLayoutX(200); imgView.setLayoutY(1);
			}
			else
			{
				pane.getChildren().addAll(label2,buttons);
				pane.setAlignment(label2, Pos.TOP_LEFT);
				buttons.setTranslateY(15);
			}

			label2.setText("Current User: " + currentUser);
			label2.setFont(f1);
			primary.setTitle("Options");
			scene.setRoot(pane);
		}
	}


	public void start(Stage primaryStage)
	{	//intro buttons
		Exit1.setOnAction(this::exitHandle); 				Exit1.setPrefSize(100, 30);					
		SignUp.setOnAction(this::handle);					SignUp.setPrefSize(100, 30);
		LogIn.setOnAction(this::handle);					LogIn.setPrefSize(100, 30);

		//login/signup buttons
		CancelLogIn.setOnAction(this::handle); 				CancelLogIn.setPrefSize(100, 30);
		ConfirmLogIn.setOnAction(this::handle);				ConfirmLogIn.setPrefSize(100, 30);
		CancelSignUp.setOnAction(this::handle); 			CancelSignUp.setPrefSize(100, 30);
		ConfirmSignUp.setOnAction(this::handle); 			ConfirmSignUp.setPrefSize(100, 30);

		//user buttons
		Exit2.setOnAction(this::exitHandle);				Exit2.setPrefSize(100, 30);
		Logout.setOnAction(this::logoutHandle);				Logout.setPrefSize(100, 30);
		Encrypt.setOnAction(this::EncrDecrHandle);			Encrypt.setPrefSize(100, 30);			
		Decrypt.setOnAction(this::EncrDecrHandle);			Decrypt.setPrefSize(100, 30);
		addData.setOnAction(this::dataEditHandle);			addData.setPrefSize(100, 30);				addData.setTooltip(new Tooltip("View/edit your data"));
		accountOp.setOnAction(this::accountOpHandle);		accountOp.setPrefSize(110, 30);				accountOp.setTooltip(new Tooltip("Edit your account info"));
		saveChanges.setOnAction(this::dataEditHandle); 		saveChanges.setPrefSize(120, 30);
		Cancel.setOnAction(this::dataEditHandle); 			Cancel.setPrefSize(120, 30);

		//user account options buttons
		//changePic.setOnAction(this::picScene);			
		changePic.setPrefSize(140, 25);
		changeUserName.setOnAction(this::accountOpHandle); 	changeUserName.setPrefSize(140, 25);
		changePass.setOnAction(this::accountOpHandle); 		changePass.setPrefSize(140, 25);
		deleteAcc.setOnAction(this::accountOpHandle); 		deleteAcc.setPrefSize(140, 25);
		deleteData.setOnAction(this::accountOpHandle);		deleteData.setPrefSize(140, 25);
		BackUpData.setOnAction(this::accountOpHandle); 		BackUpData.setPrefSize(140, 25);
		BackAccOp.setOnAction(this::accountOpHandle);		BackAccOp.setPrefSize(100, 25);

		//admin buttons
		adminOptions.setOnAction(this::adminOpHandle);		adminOptions.setPrefSize(110, 30);			adminOptions.setTooltip(new Tooltip("Extra administrator options"));
		DeleteAll.setOnAction(this::deleteHandle);			DeleteAll.setPrefSize(150, 30);				DeleteAll.setTooltip(new Tooltip("Delete all other accounts"));
		//DeleteOne.setOnAction(this::deleteHandle);			
		DeleteOne.setPrefSize(150, 30); 			DeleteOne.setTooltip(new Tooltip("Choose which accounts to delete"));
		viewAcc.setOnAction(this::viewAccsHandle);			viewAcc.setPrefSize(150, 30);				viewAcc.setTooltip(new Tooltip("View all registered account usernames"));
		BackAdmin.setOnAction(this::adminOpHandle);			BackAdmin.setPrefSize(100, 30);

		primary = primaryStage;
		label1.setFont(new Font("Dekko",20));

		//intro	//////////////////////////////////////////////////////////////////////////////////////
		center1.setAlignment(Pos.CENTER);
		center1.setSpacing(25);
		center1.getChildren().addAll(label1,LogIn,SignUp,Exit1);

		//login //////////////////////////////////////////////////////////////////////////////////////
		user = new Text();
		logInText = new Text("Log In:");
		logInText.setFont(f1);
		Text pass = new Text("Enter password:");
		userLogIn.setPromptText("Your username");
		passEnter.setPromptText("Your password");
		userLogIn.setTranslateY(-50);
		passEnter.setTranslateY(50);
		logInGrp.setAlignment(CancelLogIn,Pos.BOTTOM_RIGHT);
		logInGrp.setAlignment(ConfirmLogIn, Pos.BOTTOM_RIGHT);
		logInGrp.setAlignment(user,Pos.TOP_LEFT);
		logInGrp.setAlignment(pass,Pos.CENTER_LEFT);
		logInGrp.setAlignment(logInText,Pos.TOP_CENTER);
		user.setTranslateY(75);
		pass.setTranslateY(29);
		CancelLogIn.setTranslateX(-10);
		CancelLogIn.setTranslateY(-10);
		ConfirmLogIn.setTranslateX(-120);
		ConfirmLogIn.setTranslateY(-10);
		logInGrp.getChildren().addAll(logInText,user,userLogIn,pass,passEnter,ConfirmLogIn,CancelLogIn);

		//signup ////////////////////////////////////////////////////////////////////////////////////
		Text newUser = new Text("Enter a username:\t(10 characters max)");
		signUpText = new Text("Sign Up:");
		userSignUp.setPromptText("Your new username");
		signUpGrp.setAlignment(CancelSignUp, Pos.BOTTOM_RIGHT);
		signUpGrp.setAlignment(ConfirmSignUp, Pos.BOTTOM_RIGHT);
		signUpGrp.setAlignment(userSignUp , Pos.CENTER);
		signUpGrp.setAlignment(newUser , Pos.CENTER_LEFT);
		signUpGrp.setAlignment(signUpText , Pos.TOP_CENTER);
		newUser.setTranslateY(-20);
		CancelSignUp.setTranslateX(-10);
		CancelSignUp.setTranslateY(-10);
		ConfirmSignUp.setTranslateX(-120);
		ConfirmSignUp.setTranslateY(-10);
		signUpGrp.getChildren().addAll(signUpText,newUser,userSignUp,ConfirmSignUp,CancelSignUp);

		//logged in///////////////////////////////////////////////////////////////////////////////////////
		leftBox.setAlignment(Pos.CENTER);
		leftBox.setSpacing(30);
		leftBox.getChildren().addAll(Encrypt,Decrypt,addData);

		rightBox.setAlignment(Pos.CENTER);
		rightBox.setSpacing(30);
		rightBox.getChildren().addAll(accountOp,Logout,Exit2);

		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(80);
		buttons.getChildren().addAll(leftBox,rightBox);

		//user account options////////////////////////////////////////////////////////////////////////////////
		paneAccOp.setSpacing(18);
		paneAccOp.setAlignment(Pos.CENTER);

		//admin options /////////////////////////////////////////////////////////////////////////////////////////
		paneadminOp.setAlignment(Pos.CENTER);
		paneadminOp.setSpacing(35);
		paneadminOp.getChildren().addAll(viewAcc,DeleteOne,DeleteAll,BackAdmin);

		//editing data ////////////////////////////////////////////////////////////////////////////////////
		buttonsEdit.setAlignment(Pos.BOTTOM_CENTER);
		buttonsEdit.setSpacing(30);
		buttonsEdit.getChildren().addAll(saveChanges,Cancel);

		dataEdit.setPrefSize(300, 240);

		editgrp.setAlignment(Pos.CENTER);
		editgrp.getChildren().addAll(dataEdit,buttonsEdit);
		buttonsEdit.setAlignment(Pos.BOTTOM_CENTER);
		buttonsEdit.setTranslateY(10);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////

		scene = new Scene(center1,300,300);
		primary.setTitle("YMK");
		primary.resizableProperty().setValue(false);
		primary.setScene(scene);
		primary.show();
		Alert main = new Alert(AlertType.WARNING , "Warning, program is built to still minimally function if any of the essential files are deleted/renamed/relocated/altered in any other way. DO NOT alter files for proper program functionality." , ButtonType.OK);
		main.showAndWait();
	}

}