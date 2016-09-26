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
public class Server implements INet{
    private int port=6565;
    private ServerSocket serversocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    public Server(int port)
    {
        this.port=(port>1023 ? port : 6565);
        this.socket=null;
        this.in=null;
        this.out=null;
        this.serversocket=null;
    }
    
    public boolean Initialize()
    {
        try 
        {
            this.serversocket=new ServerSocket(port);
            socket = this.serversocket.accept();
            Open();
            return true;
        } 
        catch (IOException ex) 
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
        serversocket.close();
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
