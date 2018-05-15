
package control;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

import model.Queue;
import view.View;
import model.*;

public class Simulate extends Thread {
	private ArrayList<Queue> coada;
    private JButton[] butonClient;
    private int var;
    private ArrayList<Integer> timpDeAsteptare;
    private int TS=0;
    private int TA=0;
    public static int nrClienti=0;
  
    
    public  Simulate() 
    {
    	this.timpDeAsteptare = new ArrayList<Integer>();
    	this.coada = new ArrayList<Queue>(View.nrCozi);
    	for(int j=1;j<=View.nrCozi;j++)
    	{
    		timpDeAsteptare.add(j);
    	}
    	int numar=View.nrCozi;
       for(int i=0;i<numar;i++)
       {
          coada.add(i,new Queue(View.v,i));
          coada.get(i).start();
       }
       
    }
    
    //coada de lungime minima
    public synchronized int coadaLungimeMin() throws InterruptedException
    { 
    	int numar = View.nrCozi;
         int id=0;
         int min=coada.get(0).dimensiuneCoada();
        for(int i=0;i<numar;i++)
        {
              if(min>coada.get(i).dimensiuneCoada())
            {
                min=coada.get(i).dimensiuneCoada();
                id=i;
            }
           
       }
        notifyAll(); 
        return id;
    }
    
    public double timpMediuDeAsteptare()
    {
    	int numar = View.nrCozi;
    	for(int i=0;i<numar;i++)
    	{
    		TA+=coada.get(i).getTimpAsteptare();
    	}
    	return TA/nrClienti;
    }
    
    
    public synchronized int lungimeMaxima(int id) throws InterruptedException
    {
    	int coada_maxima = coada.get(0).dimensiuneCoada();
    	for(int i=0;i<View.nrCozi;i++)
    	{
       if(coada.get(i).dimensiuneCoada() > coada_maxima)
       {
           coada_maxima=coada.get(id).dimensiuneCoada();
       		}
    	}
		return coada_maxima;
    }
    
     
         public void run() {
   try{
	   
         int q=0, nr_case=View.nrCozi;
         int x;
         
         int bound=10;
       View.standuri=new JButton[nr_case];
       
       for (int casa=0;casa<View.nrCozi;casa++)
       { 
           View.standuri[casa]=new JButton(); 
           View.standuri[casa].setVisible(true);
           View.standuri[casa].setBounds(335,bound, 45, 45);
           View.standuri[casa].setBackground(Color.DARK_GRAY); 
           View.container.add(View.standuri[casa]);
           View.container.validate();
           View.f.repaint(); 
           View.f.validate();
           bound+=65;
       }
   
     int y,secunda;
     this.butonClient=new JButton[View.c.getIntervalSim()];
   int coada_minima=1;
   //int nrClienti=0;
   int[] coada_clienti=new int[View.nrCozi];
   for(int i=0;i<nr_case;i++)
   {
	   coada_clienti[i]=0;
   }
    
        for( secunda=1;secunda<=View.c.getIntervalSim();secunda++)
        { 
            sleep(1000);
          
            for(int i=0;i<nr_case;i++)
            {
               
                for(int j=coada_clienti[i];j>=0;j--)
                {
                    if(secunda==coada.get(i).getTimpPlecare(j))         
            {
                
                coada.get(i).stergeClient(secunda,coada.get(i).getIdClientPlecare(j));
            }
                }
            }
           if(secunda==1)
            {
             x=View.client.timpSosireRandom();
         
             y = View.client.timpServireRandom();
           
             var=x+y;
             q=q+x;
             TS+=y;
             coada.get(coada_minima).setTimpAsteptare(y);
            }
         
            if(secunda==q)
            { 
               nrClienti=nrClienti+1;
               coada_minima=coadaLungimeMin();
               x=View.client.timpSosireRandom();
               y=View.client.timpServireRandom();
               TS+=y;
               this.butonClient[secunda-1]=new JButton();
               this.butonClient[secunda-1].setBackground(Color.red);
               coada_clienti[coada_minima]=coada_clienti[coada_minima]+1;
               coada.get(coada_minima).adaugaClient(this.butonClient[secunda-1], secunda);
               this.butonClient[secunda-1].setText(Integer.toString(nrClienti ));
               if(coada.get(coada_minima).dimensiuneCoada()>=lungimeMaxima(coada_minima))
               {
                   coada.get(coada_minima).setOraVarf(secunda);
               }
               coada.get(coada_minima).setTimpSosire(coada_clienti[coada_minima],q);
               if(coada.get(coada_minima).getTimpSosire(coada_clienti[coada_minima])>=coada.get(coada_minima).getTimpPlecare(coada_clienti[coada_minima]-1))
               {
            	   
                   var=q+y;
                   coada.get(coada_minima).setTimpPlecare(var,coada_clienti[coada_minima],nrClienti);
                   timpDeAsteptare.set(coada_minima,timpDeAsteptare.get(coada_minima)+var-q);
                   coada.get(coada_minima).setTimpAsteptare(timpDeAsteptare.get(coada_minima));
                   this.butonClient[secunda-1].setText(Integer.toString(nrClienti ));
               }
               else
               {    
                   timpDeAsteptare.set(coada_minima, timpDeAsteptare.get(coada_minima)+var-q+y);
                   var=coada.get(coada_minima).getTimpPlecare(coada_clienti[coada_minima]-1)+y;
                   coada.get(coada_minima).setTimpPlecare(var,coada_clienti[coada_minima],nrClienti);
                   coada.get(coada_minima).setTimpAsteptare(timpDeAsteptare.get(coada_minima));
                   this.butonClient[secunda-1].setText(Integer.toString(nrClienti ));
               }
              q=q+x;
            } 
        }
        sleep(1000);
        for(int i=0;i<nr_case;i++)
        {
        	try{
           View.log.append("Peak time for queue"+(i+1)+" is at the second "+(coada.get(i).getOraVarf())+"\n");
        	}catch(ArithmeticException e){    		
        	}
        	}
        View.tTimpMediuAsteptare.setText(Double.toString(timpMediuDeAsteptare()));
        View.tTimpMediuServire.setText(Double.toString(TS/nrClienti));
        } catch(InterruptedException e){
        }  
         }
         
}
