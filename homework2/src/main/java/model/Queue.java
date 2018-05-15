
package model;
import control.*;
import java.util.ArrayList;

import javax.swing.JButton;

import view.View;


public class Queue extends Thread{ 
    private int timpDeSimulare;
    private int idCoada;
    private  ArrayList<Integer> timpPlecare;
    private ArrayList<Integer> timpSosire;
   
    private ArrayList<Integer> idClientPlecare;
    private int oraVarf;
    private int timpAsteptare;
    private   ArrayList<JButton> butoane;
    
    
    public Queue(int timp,int id){
        this.timpDeSimulare=timp;
        this.idCoada=id;
        this.timpAsteptare=0;
        this.oraVarf=0;
        this.butoane=new ArrayList<JButton>();
        this.timpPlecare=new ArrayList<Integer>();
        this.timpSosire=new ArrayList<Integer>();
        this.idClientPlecare=new ArrayList<Integer>();;
        for(int i=0;i<timp*10;i++)
        {
            this.timpPlecare.add(i);
            this.idClientPlecare.add(i);
        }
        for(int i=0;i<timp;i++)
        {
            this.timpSosire.add(i);
        }
       // clientsId.add(0);
       
    }
   
   
    
    int index = 0;
    public synchronized void adaugaClient(JButton b,int timp) throws InterruptedException
    { index++;
        //clientsId.add(index);
        int x=440,j=1;
        //b.setText(Integer.toString(index));
        this.butoane.add(b);
        
      //  this.butoane.addElement(b);
        int i=0;
        int y=0;
        int m=0;
        for(m=0;m<View.nrCozi;m++)
        {
        if(m==this.idCoada){
            i=0;
        while(i<this.butoane.size())
       {
             this.butoane.get(i).setBounds(x,14+y,90,25);
            View.container.add(this.butoane.get(i));
            View.f.pack();
            View.f.repaint();
            View.f.validate();
            x=x+90;
            i++;
       }  
}      y=y+65;
        }
         View.f.pack();
            View.f.repaint();
            View.f.validate();
            int timpS = timp - timpSosire.get(butoane.size() - 1);
            View.log.append("The  "+ Simulate.nrClienti +"th client entered the queue "+(this.idCoada+1)+" at second " +timp+ " \n");
        j=j+1;
        sleep(10);
       notifyAll();
    }
   
    public synchronized int dimensiuneCoada() throws InterruptedException
    {
        int lungime=0;
       if(this.butoane.isEmpty())
       {
        lungime=0;
       }
       else
       {
          lungime=this.butoane.size();
       }
        notifyAll();
        return lungime;  
       
    }
   
    public synchronized void stergeClient(int timp,int client) throws InterruptedException
    {
    
       int i;
       int x=440;
        int y=0;
          for(int m=0;m<View.nrCozi;m++)
        {
          if( m==this.idCoada){

            View.container.remove(this.butoane.get(0));
            this.butoane.remove(0);
            View.f.pack();
            View.f.repaint();
            View.f.validate();
            i=0;
            while(i<this.butoane.size())
            {
            this.butoane.get(i).setBounds(x,15+y,90,30);
            View.container.add(this.butoane.get(i));
            View.f.pack();
            View.f.repaint();
            View.f.validate();
            x=x+90;
            i++;
            }
   
   
          }y+=65;
        }
           View.f.pack();
            View.f.repaint();
            View.f.validate();
            int timpS = timpPlecare.get(client) - timpSosire.get(client);
        View.log.append("The "+this.idClientPlecare.get(timp)+ "th client left the queue "+(this.idCoada+1)+ " at second "+timp +" serving time: " + timpS +"\n");
        sleep(10);
        notifyAll();
    }
    
    public int getTimpAsteptare()
    {
        return this.timpAsteptare;
    }
    
    
    public void setTimpAsteptare(int x)
    {
        this.timpAsteptare=x;
    }
    
   
    
    public int getOraVarf()
    {
        return this.oraVarf;
    }
   
    public void setOraVarf(int time)
    {
        this.oraVarf=time;
    }
   
    public int getTimpSosire(int q)
    {
        return this.timpSosire.get(q);
    }
   
    public void setTimpSosire(int client,int time)
    {
        this.timpSosire.set(client,time);
    }
   
    
    public int getTimpPlecare(int client)
    {
        return this.timpPlecare.get(client);
    }
    
    public void setTimpPlecare(int l,int client,int idPlecare)
    {
        this.idClientPlecare.set(l,idPlecare);
        this.timpPlecare.set(client,l);
    }
  
    public int getIdClientPlecare(int client)
    {
        return this.idClientPlecare.get(client);
    }
  
    
    public int getIntervalSim(){
        return this.timpDeSimulare;
    }
   
  
    }

