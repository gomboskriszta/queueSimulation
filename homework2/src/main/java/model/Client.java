
package model;

import java.util.Random;


public class Client {
    private int timpMinimSosire;
    private int timpMaximSosire;
    private int timpMinimServire;
    private int timpMaximServire;
    public int idClient;
  
    public Client(int timpMaximSosire,int timpMinimSosire,int timpMinimServire,int timpMaximServire)
    {
        this.timpMaximSosire=timpMaximSosire;
        this.timpMinimSosire=timpMinimSosire;
        this.timpMaximServire=timpMaximServire;
        this.timpMinimServire=timpMinimServire;
        //this.idClient = id;
    }
   
   
    public int timpSosireRandom()
    {
        int x;
        Random r=new Random();
        x=r.nextInt(timpMaximSosire-timpMinimSosire+1)+timpMinimSosire;
        return x;
    }
    
    public int timpServireRandom()
    {
        int x;
        Random r=new Random();
        x=r.nextInt(timpMaximServire-timpMinimServire+1)+timpMinimServire;
        return x;
    }
    public int getId() {
    	
    	return idClient;
    }
  
}
