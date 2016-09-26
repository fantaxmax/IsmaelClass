/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;
import java.io.*;
import java.net.*;
/**
 *
 * @author Ismael
 */
public class Client implements INet {
    private String IP="127.0.0.1";
    private int port=6565;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    public Client(String IP,int port)
    {
        this.IP=IP;
        this.port=(port>1023 ? port : 6565);
        this.socket=null;
        this.in=null;
        this.out=null;
    }
    
    public boolean Initialize()
    {
        try
        {
            this.socket=new Socket(IP,port);
            Open();
            return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }
    
    public void Open()
    {
        try
        {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            return;
        }
        catch(IOException e)
        {
            return;
        }
    }
    
    public void Close() throws IOException
    {
        in.close();
        out.close();
        socket.close();
    }
    
    public String Read()
    {
        if(socket==null||in==null)
            return "Error@Connection:cannot connect";
        try
        {
            return in.readUTF();
        }
        catch(IOException e)
        {
            return "Error@Connection:cannot read";
        }
    }
    
    public boolean Write(Object object) throws IOException
    {
        if(socket==null||out==null)
            throw new IOException("Error@Connection:cannot connect");
        try
        {
            out.writeUTF(object.toString());
            return true;
        }
        catch(IOException e)
        {
            throw new IOException("Error@Connection:cannot write");
        }
    }
}
