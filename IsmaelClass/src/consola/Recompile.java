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
package consola;

import file.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ismael
 */
public final class Recompile {

    private Files file;
    private List<String> filedata=new ArrayList();
    private int openBrackets = 0;

    public Recompile(String filename, Object... filedata) throws Exception {
        this.file = new Files(filename);
        for(Object o : filedata){
            this.filedata.add(o.toString());
        }
        this.filedata.stream().forEach((v) -> {
            if (v.contains("{") | v.contains("[") | v.contains("(")) {
                openBrackets++;
            }
            if (v.contains("}") | v.contains("]") | v.contains(")")) {
                openBrackets--;
            }
        });
        if (openBrackets != 0) {
            throw new Exception("No se han Cerrado todos los parentesis");
        }
    }

    public void compile() throws IOException {
        file.OverWrite("");
        filedata.forEach((String data) -> {
            try {
                file.Write(data+"\n");
            } catch (IOException ex) {
                Salida.err("El archivo no existe u otro proceso lo esta usando");
            }
        });
        Process ppp = Runtime.getRuntime().exec("cmd /C javac "+file.getFile().getAbsolutePath());
        String ss = "";
        try(InputStreamReader e = new InputStreamReader(ppp.getInputStream()); BufferedReader ee = new BufferedReader(e);){
            while((ss=ee.readLine()) != null) {
                Salida.escribir(ss);
            }
        }
    }
    
    public void exec() throws IOException {
        String filename = file.getFile().getAbsolutePath().replace(".java","");
        Process ppp = Runtime.getRuntime().exec("cmd /C java "+filename);
        Object[] ss;
        try(InputStreamReader e = new InputStreamReader(ppp.getInputStream()); 
                BufferedReader ee = new BufferedReader(e);){
                ss=ee.lines().toArray();
                Salida.impresion(ss);
        }
    }

}
