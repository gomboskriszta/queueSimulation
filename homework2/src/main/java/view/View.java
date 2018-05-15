package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import control.Simulate;
import model.Client;
import model.Queue;


public class View{
    public static Queue c;
    public static Client client;
    public static JFrame f;
    private JButton btnSimulare;
    public static JPanel container;
    public static JButton[] standuri;
    public static int nrCozi;
    public static int x,y,v,x2,y2;
    public static JTextArea log;
    private JScrollPane jScrollPane1;
    private JTextField tF1, tF10,tF12,tF2,tF3,tF4,tF6,tF8;
    private JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    public static JTextField tTimpMediuAsteptare,tTimpMediuServire;
    
    
    
    public View(){
    f=new JFrame("Simulation of queues");
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setPreferredSize(new Dimension(1200,700));
    f.setResizable(true);
    
    btnSimulare=new JButton("Simulate");
    container=new JPanel();
    
    log=new JTextArea();
    jScrollPane1=new JScrollPane(log);
    tF1=new JTextField();
    tF10=new JTextField();
    tF12=new JTextField();
    tF2=new JTextField();
    tF3=new JTextField();
    tF4=new JTextField();
    tF6=new JTextField();
    tF8=new JTextField();
    tTimpMediuAsteptare = new JTextField();
    tTimpMediuServire = new JTextField();
    l1 = new JLabel("Minimum time of arrival");
    l2 = new JLabel("Maximum time of arrival");
    l3 = new JLabel("Minimum time of sevring");
    l4 = new JLabel("Maximum time of serving");
    l5 = new JLabel("Number of queues");
    l6 = new JLabel("TIme of simulation");
    l7 = new JLabel("Average time of waiting");
    l8 = new JLabel("Average time for serving");
    
    
    container.setLayout(null);
    f.add(container);
        tF2.setEditable(false);
        l1.setBounds(15,5,300,25);
        tF1.setBounds(15,40,200,25);
        tF3.setEditable(false);
        l2.setBounds(15,75,300,25);
        tF4.setBounds(15,110,200,25);
        l3.setBounds(15,145,300,25);
        tF6.setBounds(15,180,200,25);
        l4.setBounds(15,215,300,25);
        tF8.setBounds(15,250,200,25); 
        l5.setBounds(15,285,300,25);
        tF10.setBounds(15,320,200,25);
        l6.setBounds(15,355,300,25);
        tF12.setBounds(15,380,200,25);
        l7.setBounds(15, 500, 300, 25);
        tTimpMediuAsteptare.setBounds(15, 530, 100, 25);
        l8.setBounds(15, 560, 300, 25);
        tTimpMediuServire.setBounds(15, 590, 100, 25);
        btnSimulare.setBounds(15,435,210,35);
        
        
        btnSimulare.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			int z;
    		      x=Integer.parseInt(tF1.getText());
    		      y=Integer.parseInt(tF4.getText());
    		      x2=Integer.parseInt(tF6.getText());
    		      y2=Integer.parseInt(tF8.getText());
    		      z=Integer.parseInt(tF10.getText());
    		      v=Integer.parseInt(tF12.getText());
    		      nrCozi=z;
    		      client=new Client(y,x,x2,y2);
    		      c=new Queue(v,0);
    		     Simulate d=new Simulate();
    		      d.start();
    		
    		}	    	    
    	});
        
        
        
        
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        log.setBounds(352,352,710,312);
        jScrollPane1.setBounds(350,350,700,300);
        jScrollPane1.setVisible(true);
        log.setEditable(false);
        log.setColumns(20);
        log.setRows(5);
        container.add(tF1);
        container.add(l1);
        container.add(l2);
        container.add(tF4);
        container.add(l3);
        container.add(tF6);
        container.add(l4);
        container.add(tF8);
        container.add(l5);
        container.add(tF10);
        container.add(l6);
        container.add(tF12);
        container.add(btnSimulare);
        container.add(jScrollPane1);
        container.add(l7);
        container.add(tTimpMediuAsteptare);
        container.add(l8);
        container.add(tTimpMediuServire);
        
    f.pack();
    f.repaint();
    f.validate();
    f.setVisible(true);
    
}   
  
   
}
