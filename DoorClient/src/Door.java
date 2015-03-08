import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import java.sql.*;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.util.ArrayList;
import java.util.EventObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import java.util.Collections;
import java.util.ArrayList;
import java.awt.event.*;
/**
 * 
 * @author H-M-Z
 *
 */
public class Door extends JFrame 
{

	String newLine = System.getProperty("line.separator");
	public  Container TcontentPane = getContentPane();// in order to the panel in action listener we initialize panel here
	public static boolean exp;
	public static int value=-1;
	public static String satus="Not Provided";
	public static String order="Not Provided";
    
	// IO streams
	public static DataOutputStream toServer;
	public static DataInputStream fromServer;
	public static void main(String[] args) throws IOException 
	{
		String newLine = System.getProperty("line.separator");
		
		Socket socket = new Socket("localhost", 8000);
		//Socket socket = new Socket("192.168.147.102", 8000);
		fromServer = new DataInputStream(socket.getInputStream());
		toServer =new DataOutputStream(socket.getOutputStream());
		int counter=0;

		
		
		while (true)
		{
			
			
		counter++;
		System.out.print("couner is = "+counter+" "+order+" "+satus+newLine);
		order = fromServer.readUTF();
		
		if(order.equals("door"))
		{
			
		satus = fromServer.readUTF();
		value = fromServer.readInt();
		
		Door GUI = new Door();
		//GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.show();
	
		//System.out.print("status is = "+satus+newLine);
		//System.out.print("value is = "+value+newLine);
	
		
		}
		//order="Not Provided";
		//try 
		//{
		//    Thread.sleep(100);                 //1000 milliseconds is one second.
		//} 
		//catch(InterruptedException ex) 
		//{
		//    Thread.currentThread().interrupt();
		//}
		
		//socket.close();
		
		System.out.print("HERE"+newLine);
	  
		}
		
		
	}

	public Door() 
	{
		initComponents();
	}
	
	public void initComponents() 
	{
		
		
			// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
			// Generated using JFormDesigner Evaluation license - Hamza GUPUR
			label1 = new JLabel();
			button3 = new JButton();
			
			//======== frame1 ========
			{
				
			    setMinimumSize(new Dimension(1300, 500));
			    TcontentPane.setLayout(new FormLayout(
					"19dlu, 3*($lcgap, default), $lcgap, 138dlu, 4*($lcgap, default), $lcgap, 41dlu, 3*($lcgap, default), $lcgap, 46dlu, 3*($lcgap, default), $lcgap, 80dlu, $lcgap, 9dlu, $lcgap, 84dlu, 4*($lcgap, default)",
					"default, $lgap, 22dlu, 3*($lgap, default), $lgap, 57dlu, 9*($lgap, default), $lgap, 70dlu"));
			//---- label1 ----
				label1.setText(satus + " --- " + value);
				TcontentPane.add(label1, CC.xywh(3, 5, 44, 1));
				exp=false;
			//---- button3 ----
				button3.setText("Close Windows"); 
				TcontentPane.add(button3, CC.xy(39, 11));
				button3.addActionListener(new ActionListener(){
				  	  public void actionPerformed(ActionEvent ae)
				  	  {
				  		dispose(); // in order to close previous frame
				  	  }
				      });
				
				
				
			}
			
			
			
		}			
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - Hamza GUPUR
		private JLabel label1;
		private JButton button3;
}
  
