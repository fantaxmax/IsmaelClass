/*
 * Copyright (C) 2015 Ismael
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package file;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author Ismael
 */
public class Files {
    private final File file;
    
    public Files(String file)
    {
        this.file = new File(file);
    }

    public File getFile() {
        return file;
    }
    
    
    
    public Object[] Read() throws FileNotFoundException,IOException
    {
        ArrayList<String> ret=new ArrayList<>();
        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr))
        {
            while(true){
                String add=br.readLine();
                if(add==null)
                    break;
                ret.add(add);
            }
            return ret.toArray();
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public void Write(Object data) throws IOException
    {
        try(FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.append(data.toString());
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public void OverWrite(Object data) throws IOException
    {
        try(FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(data.toString());
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}
